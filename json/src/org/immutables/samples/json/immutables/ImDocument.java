package org.immutables.samples.json.immutables;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Optional;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableImDocument.class)
@JsonDeserialize(as = ImmutableImDocument.class)
//@Json.Marshaled
public interface ImDocument {

  List<Item> items();

  @Value.Immutable
//  @Json.Marshaled
  public interface Item {
    int id();

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
//  @Json.Marshaled
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
