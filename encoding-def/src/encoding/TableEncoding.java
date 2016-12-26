package encoding;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import org.immutables.encode.Encoding;

@Encoding
class TableEncoding<R, C, V> {
  @Encoding.Impl
  private ImmutableTable<R, C, V> value;

  @Encoding.Expose
  ImmutableTable<R, C, V> getImmutableTable() {
    return value;
  }

  @Encoding.Expose
  Table<R, C, V> getTable() {
    return value;
  }

  @Encoding.Of
  static <R, C, V> ImmutableTable<R, C, V> init(Table<? extends R, ? extends C, ? extends V> table) {
    return ImmutableTable.copyOf(table);
  }

  @Encoding.Builder
  static class Builder<R, C, V> {
    private ImmutableTable<R, C, V> buildValue = ImmutableTable.of();

    @Encoding.Init
    @Encoding.Copy
    public void set(Table<? extends R, ? extends C, ? extends V> table) {
      this.buildValue = ImmutableTable.copyOf(table);
    }

    @Encoding.Build
    ImmutableTable<R, C, V> build() {
      return buildValue;
    }
  }
}
