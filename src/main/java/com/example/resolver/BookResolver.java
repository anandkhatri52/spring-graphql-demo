package com.example.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.entity.AuthorEntity;
import com.example.entity.BookEntity;
import com.example.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BookResolver implements GraphQLResolver<BookEntity> {

    private final AuthorRepository authorRepository;

    public Optional<AuthorEntity> getAuthor(BookEntity bookEntity) {
        return authorRepository.findById(bookEntity.getAuthor().getId());
    }
}
