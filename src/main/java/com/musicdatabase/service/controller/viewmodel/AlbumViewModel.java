package com.musicdatabase.service.controller.viewmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class AlbumViewModel {

    @NotBlank(message = "name is required")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters")
    private String name;
    @NotBlank(message = "Year is required")
    private String year;
    @NotBlank(message = "Genre is required")
    private String genre;
    @NotBlank(message = "Author is required")
    private String author;
}
