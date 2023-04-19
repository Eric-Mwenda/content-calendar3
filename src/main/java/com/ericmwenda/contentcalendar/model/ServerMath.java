package com.ericmwenda.contentcalendar.model;

public class ServerMath {
    public int firstNumber;
    public int secondNumber;
    public int result;
    public MathOperation mathOperation;

    public enum MathOperation{
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION
    }
}
