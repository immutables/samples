package org.immutables.samples.json;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.immutables.common.marshal.Marshaling;
import org.immutables.samples.json.immutables.ImDocument.Evaluation;
import org.immutables.samples.json.immutables.ImDocument.Evaluation.Stars;
import org.immutables.samples.json.immutables.ImDocument.Item;
import org.immutables.samples.json.immutables.ImmutableEvaluation;
import org.immutables.samples.json.immutables.ImmutableImDocument;
import org.immutables.samples.json.immutables.ImmutableItem;

/**
 * It's easier to generate input with Immutables )
 */
public class JsonFile {

  private static final String[] LOREM_IPSUM =
      {
          "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam ",
          "voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem ",
          "sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis "};

  public static void main(String... args) throws IOException {
    ImmutableImDocument.Builder builder = ImmutableImDocument.builder();

    for (int i = 0; i < 40; i++) {
      builder.addItems(createItem(i, true));
    }

    String json = Marshaling.toJson(builder.build());

    Files.write(json, new File("./sample.json"), StandardCharsets.UTF_8);
  }

  private static Item createItem(int iteration, boolean recurse) {
    ImmutableItem.Builder builder = ImmutableItem.builder();

    builder
        .id(iteration + 1000)
        .name(textFor(iteration % 15, 10))
        .description(textFor(iteration, 40))
        .foo(iteration * 31 * 17)
        .bar(iteration % 2 == 0)
        .tid(iteration + 528)
        .gname(textFor(iteration % 15, 10))
        .bdescription(textFor(iteration, 40))
        .hfoo(iteration * 13)
        .ybar(iteration % 3 == 0);

    for (int i = 1; i < (2 + iteration % 10); i++) {
      builder.addEvaluation(createEvaluation(i, iteration));
    }

    for (int i = 1; i < (2 + iteration % 5); i++) {
      builder.addNevaluation(createEvaluation(i, iteration));
    }

    if (recurse) {
      for (int i = 1; i < (2 + iteration % 3); i++) {
        builder.addRecitems(createItem(iteration + 2, false));
      }
    }

    return builder.build();
  }

  private static Evaluation createEvaluation(int i, int j) {
    Stars[] stars = Stars.values();
    return ImmutableEvaluation.builder()
        // .stars((i * j) % stars.length)
        // .stars(stars[(i * j) % stars.length])
        .comment(textFor(i * j, 30))
        .build();
  }

  private static String textFor(int iteration, int pad) {
    String lorem = LOREM_IPSUM[iteration % LOREM_IPSUM.length];
    return lorem.substring(0, pad + iteration % (lorem.length() - pad));
  }
}
