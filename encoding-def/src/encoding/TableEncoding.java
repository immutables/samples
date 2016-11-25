package encoding;

import com.google.common.collect.ImmutableTable;
import org.immutables.encode.Encoding;

@Encoding
class TableEncoding {
  @Encoding.Impl
  private ImmutableTable<String, String, String> value;
}

/*
<R, C, V> {
@Encoding.Impl
private final ImmutableTable<R, C, V> value = ImmutableTable.of();
}
*/