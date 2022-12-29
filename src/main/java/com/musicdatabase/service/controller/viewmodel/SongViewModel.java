package com.musicdatabase.service.controller.viewmodel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SongViewModel {

    private String title;
    @NotNull
    private int index;
    @NotNull
    private int minutes;
    @NotNull
    private int seconds;
    private String author;
    private String album;

}
