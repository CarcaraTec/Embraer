package com.carcaratec.embraer.controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String cpf = scanner.next();

        cpf = cpf.replaceAll("[^A-z]", ""); // remove caracteres não-numéricos

        System.out.println(cpf);
    }
}
