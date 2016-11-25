package encoding.use;

import encoding.TableEncoding;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.io.Serializable;
import org.immutables.encode.Encoding;

@Encoding
class TableEncoding2<R, C, V> {
  @Encoding.Impl
  private final ImmutableTable<R, C, V> value = ImmutableTable.of();

  @Encoding.Expose
  Table<R, C, V> accessor() {
    return value;
  }

  @Override
  public String toString() {
    String actuallyInvolvedToString = value.toString() + SILLY_CONSTANT;
    return actuallyInvolvedToString;
  }

  @Override
  public int hashCode() {
    return value.hashCode() + 1;
  }

  boolean equals(TableEncoding<R, C, V> other) {
    return value.equals(other.value);
  }

  static <T extends Object & Serializable> T helper(T param) throws java.lang.Exception, java.lang.Error {
    return param;
  }

  @Encoding.Of
  static <R, C, V> ImmutableTable<R, C, V> init(Table<? extends R, ? extends C, ? extends V> table) {
    return ImmutableTable.copyOf(table);
  }

  @Encoding.Copy
  @Encoding.Naming("with*Put")
  public ImmutableTable<R, C, V> withPut(R row, C column, V value) {
    return ImmutableTable.<R, C, V>builder()
        .put(row, column, value)
        .build();
  }

  @Encoding.Naming("*CellSet")
  public ImmutableSet<Table.Cell<R, C, V>> cellSet() {
    return value.cellSet();
  }

  @Encoding.Builder
  static class Builder<R, C, V> {
    private static final Object DILLY_CONSTANT = "{D}";
    private final ImmutableTable.Builder<R, C, V> builder = ImmutableTable.<R, C, V>builder();

    @Encoding.Naming("put*")
    @Encoding.Init
    void put(R row, C column, V value) {
      builder.put(row, column, value);
    }

    @Encoding.Naming("putAll*")
    @Encoding.Init
    @Encoding.Copy
    void putAll(Table<R, C, V> table) {
      builder.putAll(table);
      DILLY_CONSTANT.toString();// just reference constant
    }

    @Encoding.Build
    ImmutableTable<R, C, V> build() {
      return builder.build();
    }
  }
}
