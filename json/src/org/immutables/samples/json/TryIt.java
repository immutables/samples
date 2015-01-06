package org.immutables.samples.json;

import java.io.IOException;

public class TryIt {
  public static void main(String... args) throws IOException {

    int elements;
    String string = new StringBuilder("sdsdsds {")
        // .append((bv = ", ")).append("11")
        // .append(i++ > 0 ? ", " : "").append("222")
        .append("}")
        .toString();

    System.out.println(string);
//    JsonReader reader = new JsonReader(new StringReader("{\"bo\":true }"));
//
//    reader.beginObject();
//    reader.nextName();
//    try {
//      reader.nextInt();
//    } catch (Exception ex) {
//      System.out.println(ex.toString());
//    }
//    boolean nextBoolean = reader.nextBoolean();
//    System.out.println(nextBoolean);
  }
}
