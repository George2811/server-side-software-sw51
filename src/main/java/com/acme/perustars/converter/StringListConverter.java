package com.acme.perustars.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return strings != null ? String.join(";", strings) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return s != null ? Arrays.asList(s.split(";")) : emptyList();
    }
}
