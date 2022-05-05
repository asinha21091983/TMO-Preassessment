package com.galvanize.tmo.paspringstarter.model;

import java.util.ArrayList;
import java.util.List;

public class Books {

    public List<Data> getBooks() {
        return books;
    }

    public void setBooks(List<Data> books) {
        this.books = books;
    }

    List<Data> books = new ArrayList<>();
}
