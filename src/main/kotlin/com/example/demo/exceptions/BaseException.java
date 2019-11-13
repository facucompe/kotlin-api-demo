package com.example.demo.exceptions;

import graphql.GraphQLError;

public abstract class BaseException extends RuntimeException implements GraphQLError {
    abstract String errorMessage();

    @Override
    public String getMessage() {
        return errorMessage();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return null;
    }
}
