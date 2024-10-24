package dev.gabiqassis.hering.domain.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationCPF {

    public static String validateCPF(String cpf) {
        if (cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio");
        }
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            throw new IllegalArgumentException("O CPF deve conter 11 dígitos.");
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }


        if (!validarDigitosVerificadores(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        return cpf;
    }

    private static boolean validarDigitosVerificadores(String cpf) {
        int primeiroVerificador = calcularDigito(cpf, 9);
        int segundoVerificador = calcularDigito(cpf, 10);
        return cpf.charAt(9) == (char) (primeiroVerificador + '0') &&
                cpf.charAt(10) == (char) (segundoVerificador + '0');
    }

    private static int calcularDigito(String cpf, int length) {
        int soma = 0;
        int peso = length + 1;

        for (int i = 0; i < length; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }

        int resto = 11 - (soma % 11);
        return (resto > 9) ? 0 : resto;
    }
}