package org.immutables.samples.json.pojo;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import java.lang.annotation.Annotation;
import java.util.Set;
import com.squareup.moshi.JsonAdapter;
import com.google.common.base.Optional;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OptionalJsonAdapterFactory implements JsonAdapter.Factory {

  @Override
  public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
    if (type instanceof ParameterizedType && Optional.class == ((ParameterizedType) type).getRawType()) {
      return new OptionalAdapter(moshi, ((ParameterizedType) type).getActualTypeArguments()[0]);
    }
    return null;
  }

  private static class OptionalAdapter extends JsonAdapter<Optional<?>> {
    private JsonAdapter<Object> adapter;
    private final Moshi moshi;
    private final Type type;

    public OptionalAdapter(Moshi moshi, Type type) {
      this.moshi = moshi;
      this.type = type;
    }

    @Override
    public void toJson(JsonWriter out, Optional<?> value) throws IOException {
      if (value.isPresent()) {
        if (adapter == null) {
          adapter = moshi.adapter(type);
        }
        adapter.toJson(out, value.get());
      } else {
        out.nullValue();
      }
    }

    @Override
    public Optional<?> fromJson(JsonReader in) throws IOException {
      if (JsonReader.Token.NULL == in.peek()) {
        return Optional.absent();
      }
      if (adapter == null) {
        adapter = moshi.adapter(type);
      }
      return Optional.of(adapter.fromJson(in));
    }
  }
}
