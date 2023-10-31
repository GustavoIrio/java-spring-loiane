package com.buss.crud_spring_courses.enums.converters;

import com.buss.crud_spring_courses.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        if(category == null) {
            return null;
        }
        return category.getValue();
    }

    @SneakyThrows
    @Override
    public Category convertToEntityAttribute(String value) {
        if(value == null) {
            return null;
        }
        return Stream.of(Category.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessException::new);
    }
}
