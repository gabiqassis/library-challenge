package dev.gabiqassis.hering.domain.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationCpf {

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


        if (!validateDigitosCheckers(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        return cpf;
    }

    private static boolean validateDigitosCheckers(String cpf) {
        int firstVerifier = calculateDigital(cpf, 9);
        int secondVerifier = calculateDigital(cpf, 10);
        return cpf.charAt(9) == (char) (firstVerifier + '0') &&
                cpf.charAt(10) == (char) (secondVerifier + '0');
    }

    private static int calculateDigital(String cpf, int length) {
        int sum = 0;
        int weight = length + 1;

        for (int i = 0; i < length; i++) {
            sum += (cpf.charAt(i) - '0') * weight--;
        }

        int resto = 11 - (sum % 11);
        return (resto > 9) ? 0 : resto;
    }
}