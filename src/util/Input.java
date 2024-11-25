package util;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.System.in;

/// # Input
///
/// A classe `Input` fornece metodos utilitários para capturar e validar entradas do usuário no console.
/// Ela oferece funcionalidades para obter números inteiros e strings não vazias, garantindo que as entradas
/// sejam válidas antes de retorná-las ao chamador.
///
/// ## Funcionalidades
/// - **Captura de Inteiros (`getInt`):** Solicita e valida a entrada de um número inteiro do usuário.
/// - **Captura de Strings Não Vazias (`getNonEmptyString`):** Solicita e valida a entrada de uma string não vazia do usuário.
///
/// ## Estrutura Interna
/// - **`scanner`:** Instância única de `Scanner` utilizada para ler entradas do console.
///
/// ## Uso
/// Utilize os metodos estáticos `getInt` e `getNonEmptyString` para capturar entradas do usuário de forma segura.
/// Esses metodos garantem que as entradas atendam aos critérios especificados antes de retorná-las.
///
/// ### Exemplo
/// ```java
/// int idade = Input.getInt("Digite sua idade: ");
/// String nome = Input.getNonEmptyString("Digite seu nome: ");
/// System.out.println("Nome: " + nome + ", Idade: " + idade);
///```
public class Input {

    /// ### Campos
    ///
    /// - **`scanner`:** Instância única de `Scanner` utilizada para ler entradas do console.
    private static final Scanner scanner = new Scanner(in);

    /// ## getInt
    ///
    /// Recebe um número inteiro digitado pelo usuário. A função exibe uma
    /// mensagem de solicitação e aguarda até que um número válido seja
    /// fornecido. Em caso de erro, como a inserção de um valor que não é um
    /// número inteiro, exibe uma mensagem de erro e solicita a entrada novamente.
    ///
    /// ### Parâmetros
    /// - **`prompt`** (`String`): A mensagem que será exibida ao usuário pedindo a entrada do número.
    ///
    /// ### Retorno
    /// - **`int`**: O número inteiro inserido pelo usuário após validação.
    ///
    /// ### Fluxo de Operações
    /// 1. Exibe a mensagem de solicitação (`prompt`) ao usuário.
    /// 2. Lê a entrada do usuário como uma linha de texto.
    /// 3. Tenta converter a entrada para um número inteiro usando `parseInt`.
    /// 4. Se a conversão for bem-sucedida, retorna o número.
    /// 5. Se ocorrer uma exceção `NumberFormatException`, exibe uma mensagem de erro e repete o processo.
    ///
    /// ### Exceções
    /// - **`NumberFormatException`**: Capturada internamente para tratar entradas inválidas, mas não propagada para o chamador.
    ///
    /// ### Exemplo
    /// ```java
    /// int idade = Input.getInt("Digite sua idade: ");
    /// System.out.println("Idade digitada: " + idade);
    ///```
    ///
    /// @param prompt A mensagem que será exibida ao usuário pedindo a entrada do número.
    /// @return O número inteiro inserido pelo usuário após validação.
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
                // Se uma exceção 'NumberFormatException' ocorrer, informa o usuário e pede uma nova entrada.
                System.err.println("Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
        return value;
    }

    /// ## getNonEmptyString
    ///
    /// Recebe uma string não vazia digitada pelo usuário. A função exibe uma
    /// mensagem de solicitação e aguarda até que uma string válida seja fornecida.
    /// Se o usuário fornecer uma string vazia, uma mensagem de erro será exibida
    /// e uma nova entrada será solicitada.
    ///
    /// ### Parâmetros
    /// - **`prompt`** (`String`): A mensagem que será exibida ao usuário pedindo a entrada da string.
    ///
    /// ### Retorno
    /// - **`String`**: A string inserida pelo usuário após validação de que não está vazia.
    ///
    /// ### Fluxo de Operações
    /// 1. Exibe a mensagem de solicitação (`prompt`) ao usuário.
    /// 2. Lê a entrada do usuário como uma linha de texto.
    /// 3. Remove espaços em branco no início e no fim da entrada usando `trim`.
    /// 4. Verifica se a string resultante não está vazia.
    /// 5. Se a string não estiver vazia, retorna-a.
    /// 6. Se a string estiver vazia, exibe uma mensagem de erro e repete o processo.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// String nome = Input.getNonEmptyString("Digite seu nome: ");
    /// System.out.println("Nome digitado: " + nome);
    ///```
    ///
    /// @param prompt A mensagem que será exibida ao usuário pedindo a entrada da string.
    /// @return A string inserida pelo usuário após validação de que não está vazia.
    public static String getNonEmptyString(String prompt) {
        String value;
        do {
            System.out.print(prompt);
            // A função 'trim' remove espaços em branco no início e no fim da entrada.
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                // Se a string estiver vazia, uma mensagem de erro é exibida e uma nova entrada é solicitada.
                System.err.println("Entrada inválida. O campo não pode estar vazio.");
            }
        } while (value.isEmpty());
        return value;
    }
}
