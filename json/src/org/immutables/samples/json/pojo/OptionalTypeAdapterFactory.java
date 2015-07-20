package org.immutables.samples.json.pojo;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public class OptionalTypeAdapterFactory implements TypeAdapterFactory {
  @Override
  @SuppressWarnings("unchecked")
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    if (!Optional.class.isAssignableFrom(type.getRawType())) {
      return null;
    }
    TypeToken<Object> tt = (TypeToken<Object>) TypeToken.get(
        ((ParameterizedType) type.getType()).getActualTypeArguments()[0]);

    return (TypeAdapter<T>) new OptionalTypeAdapter(gson.getAdapter(tt));
  }

  private static class OptionalTypeAdapter extends TypeAdapter<Optional<?>> {
    private final TypeAdapter<Object> adapter;

    private OptionalTypeAdapter(TypeAdapter<Object> adapter) {
      this.adapter = adapter;
    }

    @Override
    public void write(JsonWriter out, Optional<?> value) throws IOException {
      if (value.isPresent()) {
        adapter.write(out, value.get());
      } else {
        out.nullValue();
      }
    }

    @Override
    public Optional<?> read(JsonReader in) throws IOException {
      if (JsonToken.NULL == in.peek()) {
        return Optional.absent();
      }
      return Optional.of(adapter.read(in));
    }
  }
}
