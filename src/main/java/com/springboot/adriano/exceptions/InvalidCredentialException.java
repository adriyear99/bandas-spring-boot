package com.springboot.adriano.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidCredentialException extends Exception{
    private String msg;
}
