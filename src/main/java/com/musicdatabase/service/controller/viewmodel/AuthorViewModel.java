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
    @Size(max = 40)
    private String name;
    @NotBlank
    @Size(min = 1, max = 10, message = "oopsie error")
    private String gender;
    @NotNull
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
