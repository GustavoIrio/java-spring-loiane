package com.buss.crud_spring_courses.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CourseDTO(
        Long id,
        @NotBlank @NotNull @Length(min = 5, max = 100) String name,
        String category) {
}
