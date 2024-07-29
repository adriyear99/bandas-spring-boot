package com.springboot.adriano.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountExistsException extends Exception{
    private String msg;
}
