package com.musicdatabase.service.domain.session;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PageVisit {
    private final String url;
    private final LocalDateTime timestamp;
    public PageVisit(String url) {
        this.url = url;
        this.timestamp = LocalDateTime.now();
    }
}
