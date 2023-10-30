package com.buss.crud_spring_courses.services;

import com.buss.crud_spring_courses.exceptions.RecordNotFoundException;
import com.buss.crud_spring_courses.model.Course;
import com.buss.crud_spring_courses.repositories.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id));
    }

    public Course createCourse(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id)
                .map(courseFound -> {
                    courseFound.setName(course.getName());
                    courseFound.setCategory(course.getCategory());
                    return courseRepository.save(courseFound);
                }).orElseThrow(()-> new RecordNotFoundException(id));
    }

    public void deleteCourse(@PathVariable @NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException(id)));
    }

}
