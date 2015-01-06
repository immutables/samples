package org.immutables.samples.json.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import static com.google.common.base.Preconditions.*;

public class Io {
  public static Reader readerFor(String input) {
    return asReader(CharBuffer.wrap(input.toCharArray()));
  }

  static Reader asReader(final CharBuffer readable) {
    checkNotNull(readable);
    return new Reader() {
      @Override
      public int read(char[] cbuf, int off, int len) throws IOException {
        return read(CharBuffer.wrap(cbuf, off, len));
      }

      @Override
      public int read(CharBuffer target) throws IOException {
        return readable.read(target);
      }

      @Override
      public void close() throws IOException {
        if (readable instanceof Closeable) {
          ((Closeable) readable).close();
        }
      }
    };
  }
}
