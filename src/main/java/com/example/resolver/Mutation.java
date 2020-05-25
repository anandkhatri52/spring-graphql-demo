package com.example.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.entity.AuthorEntity;
import com.example.entity.BookEntity;
import com.example.exception.BookNotFoundException;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Mutation implements GraphQLMutationResolver {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public AuthorEntity newAuthor(String firstName, String lastName) {
        AuthorEntity authorEntity = AuthorEntity.builder()
                .firstName(firstName)
                .firstName(lastName)
                .build();

        authorRepository.save(authorEntity);

        return authorEntity;
    }

    public BookEntity newBook(String title, String isbn, Integer pageCount, Long authorId) {

        BookEntity bookEntity = BookEntity.builder()
                .title(title)
                .isbn(isbn)
                .pageCount(pageCount != null ? pageCount : 0)
                .author(AuthorEntity.builder().id(authorId).build())
                .build();
        return bookRepository.save(bookEntity);
    }

    public Boolean deleteBook(Long id) {
        bookRepository.delete(BookEntity.builder().id(id).build());
        return true;
    }

    public BookEntity updateBookPageCount(Integer pageCount, Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("The book to be updated was not found", id));

        bookEntity.setPageCount(pageCount);
        bookRepository.save(bookEntity);
        return bookEntity;
    }

}
