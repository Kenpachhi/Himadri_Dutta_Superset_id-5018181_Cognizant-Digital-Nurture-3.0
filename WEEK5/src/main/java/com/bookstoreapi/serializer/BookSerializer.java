package com.bookstoreapi.serializer;

import com.bookstoreapi.model.Book;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BookSerializer extends JsonSerializer<Book> {
    @Override
    public void serialize(Book book, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", book.getId());
        gen.writeStringField("title", book.getTitle());
        gen.writeStringField("author", book.getAuthor());
        gen.writeNumberField("price", book.getPrice());
        gen.writeStringField("isbn", book.getIsbn());
        gen.writeEndObject();
    }
}
