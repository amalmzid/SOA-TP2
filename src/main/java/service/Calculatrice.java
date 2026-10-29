package service;

import jakarta.jws.WebService;

@WebService(
        endpointInterface = "service.ICalculatrice",
        targetNamespace = "http://service/",
        serviceName = "CalculatriceService",
        portName = "CalculatricePort")
public class Calculatrice implements ICalculatrice {

    @Override
    public double somme(double a, double b) {
        return a + b;
    }

    @Override
    public double soustraction(double a, double b) {
        return a - b;
    }

    @Override
    public double multiplication(double a, double b) {
        return a * b;
    }

    @Override
    public double division(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division par zero impossible");
        }
        return a / b;
    }
}
