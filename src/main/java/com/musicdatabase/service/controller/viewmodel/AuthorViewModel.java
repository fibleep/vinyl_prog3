package com.musicdatabase.service.controller.viewmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class AuthorViewModel {
    @Size(max = 40, message = "Name must be less than 40 characters")
    @NotBlank
    private String name;
    @Size(min = 1, max = 10)
    private String gender;
    @Max(120)
    private int age;
}
