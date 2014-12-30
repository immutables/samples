package org.immutables.samples.json.autojackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

@AutoValue
public abstract class AutoDocument {
  @JsonProperty
  public abstract List<Item> items();

  @JsonCreator
  public static AutoDocument create(
      @JsonProperty("items") List<Item> items) {
    return new AutoValue_AutoDocument(ImmutableList.copyOf(items));
  }

  @AutoValue
  public static abstract class Item {

    @JsonProperty
    public abstract int id();

    @JsonProperty
    public abstract String name();

    @JsonProperty
    @Nullable
    public abstract String description();

    @JsonProperty
    public abstract List<Evaluation> evaluation();

    @JsonProperty
    public abstract int foo();

    @JsonProperty
    public abstract boolean bar();

    @JsonProperty
    public abstract Optional<Integer> tid();

    @JsonProperty
    public abstract Optional<String> gname();

    @JsonProperty
    @Nullable
    public abstract String bdescription();

    @JsonProperty
    public abstract List<Evaluation> nevaluation();

    @JsonProperty
    public abstract Optional<Integer> hfoo();

    @JsonProperty
    public abstract boolean ybar();

    @JsonProperty
    public abstract Set<Item> recitems();

    @JsonCreator
    public static Item create(
        @JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("description") @Nullable String description,
        @JsonProperty("evaluation") List<Evaluation> evaluation,
        @JsonProperty("foo") int foo,
        @JsonProperty("bar") boolean bar,
        @JsonProperty("tid") Optional<Integer> tid,
        @JsonProperty("gname") Optional<String> gname,
        @JsonProperty("bdescription") @Nullable String bdescription,
        @JsonProperty("nevaluation") List<Evaluation> nevaluation,
        @JsonProperty("hfoo") Optional<Integer> hfoo,
        @JsonProperty("ybar") boolean ybar,
        @JsonProperty("recitems") List<Item> recitems) {
      return new AutoValue_AutoDocument_Item(
          id,
          name,
          description,
          ImmutableList.copyOf(evaluation),
          foo,
          bar,
          tid,
          gname,
          bdescription,
          ImmutableList.copyOf(nevaluation),
          hfoo,
          ybar,
          recitems != null ? ImmutableSet.copyOf(recitems) : ImmutableSet.of());
    }
  }

  @AutoValue
  public static abstract class Evaluation {

    @JsonProperty
    @Nullable
    public abstract String comment();

    @JsonProperty
    public abstract Stars stars();

    public enum Stars {
      NONE, ONE, TWO, THREE, FOUR, FIVE
    }

    @JsonCreator
    public static Evaluation create(
        @JsonProperty("comment") String comment,
        // @JsonProperty("stars") int stars) {
        @JsonProperty("stars") @Nullable Stars stars) {
      return new AutoValue_AutoDocument_Evaluation(
          comment,
          // stars);
          stars == null ? Stars.NONE : stars);
    }
  }
}
