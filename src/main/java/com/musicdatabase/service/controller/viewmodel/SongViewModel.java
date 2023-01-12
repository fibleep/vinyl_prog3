package com.musicdatabase.service.controller.viewmodel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SongViewModel {

    @NotBlank(message = "Title is required")
    @NotNull(message = "Title is required")
    private String title;
    private int index;
    @NotNull(message = "Duration is required")
    private int minutes;
    @NotNull(message = "Duration is required")
    private int seconds;
    @NotNull(message = "Album is required")
    private String author;
    private String album;

}
