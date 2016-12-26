package uses;

import com.google.common.collect.Table;
import com.google.common.collect.ImmutableTable;
import org.immutables.value.Value;

@Value.Immutable
interface UseTable<V> {
  ImmutableTable<String, String, V> values();

  Table<Integer, Integer, V> intValues();
}
