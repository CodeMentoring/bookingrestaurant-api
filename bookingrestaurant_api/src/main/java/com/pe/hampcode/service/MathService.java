package com.pe.hampcode.service;

import com.pe.hampcode.entity.Calculator;

public class MathService {
    private final Calculator calculator;

    public MathService(Calculator calculator) {
        this.calculator = calculator;
    }

    public int add(int a, int b) {
        return calculator.add(a, b);
    }

    public int subtract(int a, int b) {
        return calculator.subtract(a, b);
    }
}
