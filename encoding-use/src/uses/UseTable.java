package uses;

import com.google.common.collect.ImmutableTable;
import org.immutables.value.Value;

@Value.Immutable
interface UseTable {
  ImmutableTable<String, String, String> values();
}