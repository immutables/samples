package org.immutables.samples.json.pojo;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

public class PojoDocument {

  public List<Item> items = ImmutableList.of();

  public static class Item {
    public int id;

    public String name;

    @Nullable
    public String description;

    public List<Evaluation> evaluation = ImmutableList.of();

    public int foo;

    public boolean bar;

    public Optional<Integer> tid = Optional.absent();

    public Optional<String> gname = Optional.absent();

    @Nullable
    public String bdescription;

    public List<Evaluation> nevaluation = ImmutableList.of();

    public Optional<Integer> hfoo = Optional.absent();

    public boolean ybar;

    public Set<Item> recitems = ImmutableSet.of();
  }

  public static class Evaluation {

    public String comment;

    public Stars stars = Stars.NONE;

    public enum Stars {
      NONE, ONE, TWO, THREE, FOUR, FIVE
    }
  }
}
