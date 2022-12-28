package com.musicdatabase.service.controller.converter;


import com.musicdatabase.service.model.Gender;
import org.springframework.core.convert.converter.Converter;

public class StringToGenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        switch (source) {
            case "MALE" -> {
                return Gender.MALE;
            }
            case "FEMALE" -> {
                return Gender.FEMALE;
            }
            default -> {
                return Gender.OTHER;
            }
        }
    }
}
