package org.immutables.samples.json.immutables;

import org.immutables.moshi.Json;
import com.google.common.base.Optional;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Value.Enclosing
@Gson.TypeAdapters
@Json.Adapters
public interface Gocument {

  List<Item> items();

  @Value.Immutable
  public interface Item {
    int id();

    @Json.Named("name")
    String name();

    @Nullable
    String description();

    List<Evaluation> evaluation();

    int foo();

    boolean bar();

    Optional<Integer> tid();

    Optional<String> gname();

    @Nullable
    String bdescription();

    List<Evaluation> nevaluation();

    Optional<Integer> hfoo();

    boolean ybar();

    Set<Item> recitems();
  }

  @Value.Immutable
  public static abstract class Evaluation {

    public abstract String comment();

    @Value.Default
    public Stars stars() {
      return Stars.NONE;
    }

    public enum Stars {
      NONE, ONE, TWO, THREE, FOUR, FIVE
    }
  }
}
