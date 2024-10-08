package util;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.System.in;

public class Input {

    private static final Scanner scanner = new Scanner(in);

    /// Recebe um número inteiro digitado pelo usuário. A função exibe uma
    /// mensagem de solicitação e aguarda até que um número válido seja
    /// fornecido. Em caso de erro, como a inserção de um valor que não é um
    /// número inteiro, exibe uma mensagem de erro e solicita a entrada novamente.
    ///
    /// @param prompt a mensagem que será exibida ao usuário pedindo a entrada do número.
    /// @return o número inteiro inserido pelo usuário após validação.
    public static int getInt(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                // Tenta converter a entrada do usuário em um número inteiro. A função
                // 'parseInt' lança uma exceção se a entrada não for um número inteiro válido.
                value = parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                //Se uma exceção 'NumberFormatException' ocorrer, informa o usuário e pede uma nova entrada.
                System.err.println("Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
        return value;
    }

    /// Recebe uma string não vazia digitada pelo usuário. A função exibe uma
    /// mensagem de solicitação e aguarda até que uma string válida seja fornecida.
    /// Se o usuário fornecer uma string vazia, uma mensagem de erro será exibida
    /// e uma nova entrada será solicitada.
    ///
    /// @param prompt a mensagem que será exibida ao usuário pedindo a entrada da string.
    /// @return a string inserida pelo usuário após validação de que não está vazia.
    public static String getNonEmptyString(String prompt) {
        String value;
        do {
            System.out.print(prompt);
            // A função 'trim' remove espaços em branco no início e no fim da entrada.
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                //Se a string estiver vazia, uma mensagem de erro é exibida e uma nova entrada é solicitada.
                System.err.println("Entrada inválida. O campo não pode estar vazio.");
            }
        } while (value.isEmpty());
        return value;
    }
}
