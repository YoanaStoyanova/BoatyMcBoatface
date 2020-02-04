package org.fmi.spring.boatyservice.api.bindings;

import java.util.List;

import org.springframework.data.domain.Page;

public class PagedResponse<T> {

    public List<T> content;
    public int number;
    public int numberOfElements;
    public int totalPages;
    public long totalElements;

    public PagedResponse() { }

    public PagedResponse(Page<T> page) {
        this.content = page.getContent();
        this.number = page.getNumber();
        this.numberOfElements = page.getNumberOfElements();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
