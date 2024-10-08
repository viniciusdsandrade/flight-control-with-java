import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.in;
import static util.Input.getNonEmptyString;

/// A classe 'Main' serve como ponto de entrada para o sistema de gerenciamento de voos.
/// Ela apresenta um menu interativo para que o usuário possa realizar operações como
/// cadastro de aeroportos, voos, remoção de voos, e listagem de trajetos entre aeroportos.
/// O sistema garante que entradas incorretas sejam tratadas, solicitando novas entradas
/// quando necessário.
public class Main {

    /// Scanner para capturar a entrada do usuário. A constante 'scanner' é utilizada
    /// em todos os métodos que requerem interações com o usuário.
    private static final Scanner scanner = new Scanner(in);

    /// Instância da classe 'FlightOrganizer', que gerencia as operações de voo e aeroportos.
    private static final FlightOrganizer organizer = new FlightOrganizer();

    /// Metodo principal ('main') que inicia o programa. Ele exibe um menu e
    /// processa as opções selecionadas pelo usuário em um loop contínuo até que
    /// o usuário escolha a opção de sair.
    ///
    /// @param args Argumentos de linha de comando (não utilizados neste contexto).
    public static void main(String[] args) {
        int option; // Armazena a opção escolhida pelo usuário
        do {
            option = displayMenu(); // Exibe o menu e captura a opção
            handleMenuOption(option); // Executa a operação correspondente à opção
        } while (option != 0); // Continua até que a opção 0 (Sair) seja selecionada
    }

    /// Exibe o menu interativo para o usuário e captura a entrada da opção desejada.
    /// Este metodo garante que a entrada seja validada, evitando erro de tipo incorreto,
    /// como letras ou símbolos quando números são esperados.
    ///
    /// @return O número da opção selecionada pelo usuário.
    private static int displayMenu() {
        int option = -1; // Inicializa a opção com um valor inválido
        boolean validInput = false; // Flag para controlar a validade da entrada

        // Loop para garantir que o usuário insira uma opção válida
        while (!validInput) {
            try {
                // Exibe o menu de opções
                System.out.println("\nMenu:");
                System.out.println("1. Cadastrar Aeroporto");
                System.out.println("2. Cadastrar Voo");
                System.out.println("3. Remover Voo");
                System.out.println("4. Listar Voos a partir de um Aeroporto");
                System.out.println("5. Listar Trajetos entre Aeroportos");
                System.out.println("6. Listar Aeroportos");
                System.out.println("7. Listar Todas as Combinações Possíveis de Trajetos Entre Dois Aeroportos (Test)");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                option = scanner.nextInt(); // Captura a opção digitada pelo usuário

                validInput = true; // Se a entrada for válida, sai do loop
            } catch (InputMismatchException e) {
                // Tratamento de erro para entradas inválidas (ex: letras ou símbolos)
                System.err.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Consome a entrada inválida para evitar loops infinitos
            }
        }

        return option; // Retorna a opção validada
    }

    /// Processa a opção selecionada pelo usuário no menu.
    /// Este metodo chama o metodo correspondente à opção do menu, executando a ação desejada.
    ///
    /// @param option A opção escolhida pelo usuário, que determina qual operação será executada.
    private static void handleMenuOption(int option) {
        scanner.nextLine();

        // Realiza a operação correspondente à opção selecionada
        switch (option) {
            case 1 -> organizer.addAirport(); // Chama o metodo para cadastrar um aeroporto
            case 2 -> organizer.addFlight();  // Chama o metodo para cadastrar um voo
            case 3 -> organizer.removeFlight(); // Chama o metodo para remover um voo
            case 4 -> organizer.listFlightsFromAirport(); // Lista voos a partir de um aeroporto
            case 5 -> organizer.listPathsBetweenAirports(); // Lista trajetos entre aeroportos
            case 6 -> organizer.listAirports(); // Lista todos os aeroportos cadastrados
            case 7 -> handleListAllPossiblePaths(); // Chama o novo metodo de teste
            case 0 -> System.err.println("Saindo..."); // Encerra o programa
            default -> System.err.println("Opção inválida."); // Trata opções inválidas
        }
    }

    private static void handleListAllPossiblePaths() {
        String sourceCode = getNonEmptyString("Digite o código do aeroporto de origem para o teste: ");
        String destCode = getNonEmptyString("Digite o código do aeroporto de destino para o teste: ");

        // Chama a nova função para listar todas as combinações possíveis de trajetos
        organizer.listAllPossiblePaths(sourceCode, destCode);
    }
}
