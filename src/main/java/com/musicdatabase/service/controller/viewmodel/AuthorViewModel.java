package com.musicdatabase.service.controller.viewmodel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthorViewModel {
    @Size(max = 40, message = "Name must be less than 40 characters")
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Gender is required")
    @Size(min = 1, max = 10)
    private String gender;
    @NotNull(message = "Age is required")
    @Max(120)
    private int age;

    @Override
    public String toString() {
        return "AuthorViewModel{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
