package com.javarush.task.jdk13.task53.task5307.exceptions;

public class MyExeption extends RuntimeException {
    public MyExeption() {
    }

    public MyExeption(String message) {
        super(message);
    }
}
