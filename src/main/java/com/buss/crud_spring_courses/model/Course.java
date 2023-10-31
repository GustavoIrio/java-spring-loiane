package com.buss.crud_spring_courses.model;

import com.buss.crud_spring_courses.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Column(length = 12, nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Active|Inactive")
    @Column(length = 12, nullable = false)
    private String status = "Active";
}
