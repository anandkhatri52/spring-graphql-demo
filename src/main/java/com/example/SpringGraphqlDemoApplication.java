package com.example;

import com.example.entity.AuthorEntity;
import com.example.entity.BookEntity;
import com.example.exception.GraphQLErrorAdapter;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringGraphqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGraphqlDemoApplication.class, args);
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

	@Bean
	public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
		return (args) -> {
			AuthorEntity author = AuthorEntity.builder().firstName("Herbert").lastName("Schildt").build();
			authorRepository.save(author);

			BookEntity bookEntity = BookEntity.builder()
					.title("Java: A Beginner's Guide, Sixth Edition")
					.isbn("0071809252")
					.pageCount(728)
					.author(author)
					.build();
			bookRepository.save(bookEntity);
		};
	}
}
