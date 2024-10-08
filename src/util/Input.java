package util;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);


    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int getInt(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
        return value;
    }

    public static String getNonEmptyString(String prompt) {
        String value;
        do {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Entrada inválida. O campo não pode estar vazio.");
            }
        } while (value.isEmpty());
        return value;
    }
}