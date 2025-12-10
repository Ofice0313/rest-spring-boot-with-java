package com.devcaleb.rest_spring_boot_with_java.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}
