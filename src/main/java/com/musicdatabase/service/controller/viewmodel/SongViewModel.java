package com.musicdatabase.service.controller.viewmodel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SongViewModel {

    @NotBlank
    private String title;
    @NotNull
    private int index;
    @NotNull
    private int minutes;
    private int seconds;
    @NotBlank
    private String author;
    private String album;

}
