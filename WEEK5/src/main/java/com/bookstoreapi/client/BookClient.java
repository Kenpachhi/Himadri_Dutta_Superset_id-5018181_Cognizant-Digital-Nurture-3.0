package com.bookstoreapi.client;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bookstoreapi.dto.BookDTO;

import reactor.core.publisher.Mono;

@Service
public class BookClient {

    private final WebClient webClient;

    public BookClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<BookDTO> getBook(Long id) {
        return this.webClient.get()
                .uri("/books/{id}", id)
                .retrieve()
                .bodyToMono(EntityModel.class)
                .map(entityModel -> {
                    return (BookDTO) entityModel.getContent();
                });
    }

    public Mono<BookDTO> createBook(BookDTO bookDTO) {
        return this.webClient.post()
                .uri("/books")
                .bodyValue(bookDTO)
                .retrieve()
                .bodyToMono(EntityModel.class)
                .map(entityModel -> {
                    return (BookDTO) entityModel.getContent();
                });
    }

    public Mono<BookDTO> updateBook(Long id, BookDTO bookDTO) {
        return this.webClient.put()
                .uri("/books/{id}", id)
                .bodyValue(bookDTO)
                .retrieve()
                .bodyToMono(EntityModel.class)
                .map(entityModel -> {
                    return (BookDTO) entityModel.getContent();
                });
    }

    public Mono<Void> deleteBook(Long id) {
        return this.webClient.delete()
                .uri("/books/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
