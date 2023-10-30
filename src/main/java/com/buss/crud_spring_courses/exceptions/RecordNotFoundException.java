package com.buss.crud_spring_courses.exceptions;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id) {
        super("Record not found with this ID: "+ id);
    }
}
