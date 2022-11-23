package com.musicdatabase.service.model.session;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Getter
public class PageVisit {
    private final String url;
    private final LocalDateTime timestamp;

    public PageVisit(String url) {
        this.url = url;
        this.timestamp = LocalDateTime.now();
    }

    public String getTimestampString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return timestamp.format(formatter);
    }
}
