package com.javarush.task.jdk13.task53.task5307.exceptions;

import java.io.IOException;

public class MyException extends RuntimeException {
    public MyException() { }

    public MyException(String message) {
        super(message);
    }

    public MyException(String s, IOException e) { }
}

