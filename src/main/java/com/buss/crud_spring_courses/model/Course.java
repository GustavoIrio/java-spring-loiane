package com.buss.crud_spring_courses.model;

import com.buss.crud_spring_courses.enums.Category;
import com.buss.crud_spring_courses.enums.Status;
import com.buss.crud_spring_courses.enums.converters.CategoryConverter;
import com.buss.crud_spring_courses.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inactive' WHERE id = ?") //soft delete - utiliza o metodo delete porem altera apenas o status ao inves de deletar
@Where(clause = "status = 'Active'") //como usamos o soft delete, ao utilizar os get, vai puxar apenas os ativos
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 12, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;
}
