package com.galvanize.tmo.paspringstarter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.tmo.paspringstarter.model.Books;
import com.galvanize.tmo.paspringstarter.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessLogic {
    @Autowired
    CacheManager cacheManager;

    /**
     * Populate the cache with books data
     * @param bookDetails Book details that needs to be stored
     * @return Books from cache
     */
    public Books addBooks(Data bookDetails) {
        try {
            Books books = new Books();
            Data entry = new Data();
            entry.setAuthor(bookDetails.getAuthor());
            entry.setTitle(bookDetails.getTitle());
            entry.setYearPublished(bookDetails.getYearPublished());
            if (Optional.ofNullable(cacheManager.getCache("books").get("books")).isPresent()) {
                books = (Books) cacheManager.getCache("books").get("books").get();
                entry.setId(books.getBooks().size() + 1);
                books.getBooks().add(entry);
                cacheManager.getCache("books").put("books", books);
            } else {
                entry.setId(1);
                books.getBooks().add(entry);
                cacheManager.getCache("books").put("books", books);
            }
        } catch (Exception e) {
            System.out.println("Exception while loading / updating data" + e.getMessage());

        }

        return (Books)cacheManager.getCache("books").get("books").get();
    }

    /**
     * This method will get the books from cache
     * @return Books object
     */
    public Books getBooks() {
        if (Optional.ofNullable(cacheManager.getCache("books").get("books")).isPresent()) {
            return (Books)cacheManager.getCache("books").get("books").get();

        }
        return new Books();

    }

    /**
     * Clear the cache with all the books
     */
    public void deleteBooks() {
        cacheManager.getCache("books").evictIfPresent("books");
    }

}
