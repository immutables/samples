package org.immutables.samples.json;

import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.immutables.common.marshal.Marshaler;
import org.immutables.common.marshal.Marshaling;
import org.immutables.samples.json.autojackson.AutoDocument;
import org.immutables.samples.json.immutables.ImDocument;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class JsonBenchmarks {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private byte[] json;

  @Setup
  public void setup() throws IOException {
    json = Resources.toByteArray(JsonBenchmarks.class.getResource("sample.json"));
    objectMapper.registerModule(new GuavaModule());
  }

  @Benchmark
  public byte[] autojackson() throws IOException {
    AutoDocument document = objectMapper.readValue(json, AutoDocument.class);
    return objectMapper.writeValueAsBytes(document);
  }

  // Marshaling.toJson/fromJson is not used due to pretty printing enabled by default
  @SuppressWarnings("resource")
  @Benchmark
  public byte[] immutables() throws IOException {
    Marshaler<ImDocument> marshaler = Marshaling.marshalerFor(ImDocument.class);

    JsonParser parser = objectMapper.getFactory().createParser(json);
    ImDocument document = marshaler.unmarshalInstance(parser);

    ByteArrayBuilder builder = new ByteArrayBuilder();
    JsonGenerator generator = objectMapper.getFactory().createGenerator(builder);
    marshaler.marshalInstance(generator, document);
    generator.close();

    return builder.toByteArray();
  }
}
