package com.musicdatabase.service.controller.viewmodel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AlbumViewModel {

    @NotBlank(message = "name is required")
    @Size(min = 2, max = 40)
    private String name;
    @NotBlank(message = "year is required")
    private String year;
    @NotBlank(message = "genre is required")
    private String genre;
}
