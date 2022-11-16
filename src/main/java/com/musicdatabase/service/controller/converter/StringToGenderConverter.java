package com.musicdatabase.service.controller.converter;


import com.musicdatabase.service.domain.Gender;
import org.springframework.core.convert.converter.Converter;

public class StringToGenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        switch(source){
            case "M" -> {
                return Gender.MALE;
            }
            case "F" -> {
                return Gender.FEMALE;
            }
            default -> {
                return Gender.OTHER;
            }
        }
    }
}
