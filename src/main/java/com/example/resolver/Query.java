package com.example.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.entity.AuthorEntity;
import com.example.entity.BookEntity;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<AuthorEntity> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countBooks() {
        return bookRepository.count();
    }

    public long countAuthors() {
        return authorRepository.count();
    }

}
