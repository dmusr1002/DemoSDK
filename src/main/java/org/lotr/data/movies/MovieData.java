package org.lotr.data.movies;

import java.util.List;

public class MovieData {
    private List<Movie> docs;
    private int total;
    private int limit;
    private int offset;
    private int page;
    private int pages;

    // Getters and setters for all fields

    public List<Movie> getDocs() {
        return docs;
    }

    public void setDocs(List<Movie> docs) {
        this.docs = docs;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}