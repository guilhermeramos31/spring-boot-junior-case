package com.guilhermeramos31.springbootjuniorcase.repositories.specifications;

import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    private static Specification<Book> joinEqualsIgnoreCase(String relationName, String nameToCompare) {
        return (root, query, criteriaBuilder) -> {
            if (nameToCompare == null || nameToCompare.isBlank()) return criteriaBuilder.conjunction();

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(relationName).get("name")),
                    "%" + nameToCompare.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Book> hasCategoryName(String categoryName) {
        return joinEqualsIgnoreCase("category", categoryName);
    }

    public static Specification<Book> hasAuthorName(String authorName) {
        return joinEqualsIgnoreCase("author", authorName);
    }

    public static Specification<Book> hasYearPublished(Integer yearPublished) {
        return (root, query, criteriaBuilder) -> {
            if (yearPublished == null || yearPublished <= 0) return criteriaBuilder.conjunction().isNull();

            return criteriaBuilder.equal(root.get("yearPublished"), yearPublished);
        };
    }
}
