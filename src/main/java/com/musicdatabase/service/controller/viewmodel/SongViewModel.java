package com.musicdatabase.service.controller.viewmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class SongViewModel {
    private String title;
    private int index;
    @NotNull(message = "Duration is required")
    private int minutes;
    @NotNull(message = "Duration is required")
    private int seconds;
    private String author;
    private String album;

    public double getLength() {
        if (seconds == 0) return minutes;
        return minutes + (seconds / 100.0);
    }

}
