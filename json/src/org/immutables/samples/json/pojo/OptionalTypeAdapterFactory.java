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
import java.lang.reflect.Type;

public class OptionalTypeAdapterFactory implements TypeAdapterFactory {
  @Override
  @SuppressWarnings("unchecked")
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    if (!Optional.class.isAssignableFrom(type.getRawType())) {
      return null;
    }
    return (TypeAdapter<T>) new OptionalTypeAdapter(gson, type);
  }

  @SuppressWarnings("unchecked")
  private static class OptionalTypeAdapter extends TypeAdapter<Optional> {
    private final Gson currentGson;
    private final TypeToken tt;
    private final Type valueType;

    private OptionalTypeAdapter(Gson currentGson, TypeToken tt) {
      this.currentGson = currentGson;
      this.tt = tt;
      this.valueType = ((ParameterizedType) tt.getType()).getActualTypeArguments()[0];
    }

    @Override
    public void write(JsonWriter out, Optional value) throws IOException {
      if (value.isPresent()) {
        currentGson.toJson(value.get(), valueType, out);
      } else {
        out.nullValue();
      }
    }

    @Override
    public Optional read(JsonReader in) throws IOException {
      if (JsonToken.NULL == in.peek()) {
        return Optional.absent();
      }
      return Optional.of(currentGson.fromJson(in, valueType));
    }

  }
}
