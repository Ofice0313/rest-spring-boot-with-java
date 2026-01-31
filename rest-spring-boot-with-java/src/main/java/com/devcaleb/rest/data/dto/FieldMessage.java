package com.devcaleb.rest.data.dto;

import java.util.Objects;

public class FieldMessage {

    private String fieldMessage;
    private String message;

    public FieldMessage() {
    }

    public FieldMessage(String fieldMessage, String message) {
        this.fieldMessage = fieldMessage;
        this.message = message;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FieldMessage that = (FieldMessage) o;
        return Objects.equals(fieldMessage, that.fieldMessage) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldMessage, message);
    }

    @Override
    public String toString() {
        return "FieldMessage{" +
                "fieldMessage='" + fieldMessage + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
