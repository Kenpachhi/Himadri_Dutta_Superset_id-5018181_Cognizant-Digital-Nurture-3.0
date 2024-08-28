package com.bookstoreapi.config;

import com.bookstoreapi.model.Book;
import com.bookstoreapi.serializer.BookSerializer;
import com.bookstoreapi.deserializer.BookDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Book.class, new BookSerializer());
        module.addDeserializer(Book.class, new BookDeserializer());
        mapper.registerModule(module);
        return mapper;
    }
}
