import estruturas.LinkedList.Disordered.LinkedListDisordered;

import java.util.ArrayList;
import java.util.List;

import static util.Input.getInt;
import static util.Input.getNonEmptyString;

/// A classe 'FlightOrganizer' é responsável por organizar aeroportos e voos, permitindo
/// o cadastro de novos aeroportos e voos, bem como a listagem de voos e trajetos possíveis
/// entre aeroportos. Utiliza uma estrutura de dados encadeada desordenada para armazenar
/// os aeroportos e seus respectivos voos.
public class FlightOrganizer {

    /// Lista encadeada desordenada que armazena os aeroportos do sistema.
    private LinkedListDisordered<Airport> airports;

    /// Construtor da classe `FlightOrganizer`. Inicializa a lista de aeroportos e
    /// adiciona alguns aeroportos predefinidos no sistema.
    public FlightOrganizer() {
        airports = new LinkedListDisordered<>();
        initializeAirports();
    }

    /// Inicializa a lista de aeroportos com valores predefinidos, adicionando cinco
    /// aeroportos: Belo Horizonte, Brasília, Rio de Janeiro, Salvador e São Paulo.
    private void initializeAirports() {
        airports.addLast(new Airport("Belo Horizonte", "CNF"));
        airports.addLast(new Airport("Brasília", "BSB"));
        airports.addLast(new Airport("Rio de Janeiro", "GIG"));
        airports.addLast(new Airport("Salvador", "SSA"));
        airports.addLast(new Airport("São Paulo", "GRU"));
    }

    /// Procura um aeroporto pelo seu código. Percorre a lista encadeada de aeroportos
    /// e retorna o aeroporto correspondente ao código fornecido.
    ///
    /// @param code O código do aeroporto a ser encontrado.
    /// @return O aeroporto correspondente ao código fornecido ou `null` se não for encontrado.
    private Airport findAirportByCode(String code) {
        LinkedListDisordered<Airport>.Node current = airports.getPrimeiro();  // Começa pelo primeiro nó da lista
        while (current != null) {  // Continua até percorrer todos os aeroportos
            // Verifica se o código do aeroporto atual corresponde ao código buscado (ignora maiúsculas/minúsculas)
            if (current.getElemento().getCode().equalsIgnoreCase(code))
                return current.getElemento();  // Retorna o aeroporto se for encontrado
            current = current.getProximo();  // Passa para o próximo aeroporto na lista
        }
        return null;  // Retorna null se o aeroporto não for encontrado
    }

    /// Adiciona um novo aeroporto ao sistema. Solicita ao usuário o nome e o código
    /// do aeroporto e o adiciona à lista encadeada, desde que o código não exista previamente.
    public void addAirport() {
        String name = getNonEmptyString("Digite o nome do aeroporto: ");
        String code = getNonEmptyString("Digite o código do aeroporto: ");

        // Verifica se já existe um aeroporto com o mesmo código
        if (findAirportByCode(code) != null) {
            System.err.println("Código de aeroporto já existe.");
            return;  // Se o código já existir, exibe mensagem de erro e encerra
        }

        Airport newAirport = new Airport(name, code); // Se o código não existir, cria e adiciona o novo aeroporto à lista
        airports.addLast(newAirport);  // Adiciona ao final da lista
        System.out.println("Aeroporto adicionado com sucesso.");
    }

    /// Adiciona um voo entre dois aeroportos. Solicita ao usuário o código do aeroporto
    /// de origem, o código do aeroporto de destino e o número do voo. Se os aeroportos forem
    /// válidos e o número do voo não estiver duplicado, o voo é cadastrado.
    public void addFlight() {
        String sourceCode = getNonEmptyString("Digite o código do aeroporto de origem: ");
        String destCode = getNonEmptyString("Digite o código do aeroporto de destino: ");
        int flightNumber = getInt("Digite o número do voo: ");

        // Busca os aeroportos de origem e destino
        Airport sourceAirport = findAirportByCode(sourceCode);
        Airport destAirport = findAirportByCode(destCode);

        // Verifica se o aeroporto de origem foi encontrado
        if (sourceAirport == null) {
            System.err.println("Aeroporto de origem não encontrado.");
            return;  // Se não encontrado, exibe mensagem de erro e encerra
        }

        // Verifica se o aeroporto de destino foi encontrado
        if (destAirport == null) {
            System.err.println("Aeroporto de destino não encontrado.");
            return;  // Se não encontrado, exibe mensagem de erro e encerra
        }

        // Verifica se já existe um voo com o mesmo número em qualquer aeroporto
        boolean flightNumberExists = false;
        LinkedListDisordered<Airport>.Node airportNode = airports.getPrimeiro();  // Começa pelo primeiro aeroporto
        while (airportNode != null && !flightNumberExists) {  // Percorre todos os aeroportos
            LinkedListDisordered<Flight>.Node current = airportNode.getElemento().getFlights().getPrimeiro();
            while (current != null) {  // Percorre todos os voos do aeroporto
                if (current.getElemento().getFlightNumber() == flightNumber) {
                    flightNumberExists = true;
                    break;
                }
                current = current.getProximo();  // Passa para o próximo voo
            }
            airportNode = airportNode.getProximo();  // Passa para o próximo aeroporto
        }

        if (flightNumberExists) {
            System.err.println("Número de voo já existe.");
            return;  // Se o número do voo já existir, exibe erro e encerra
        }

        Flight newFlight = new Flight(destCode, flightNumber); // Se o voo não existir, cria e adiciona o novo voo à lista
        sourceAirport.getFlights().addLast(newFlight);  // Adiciona ao final da lista de voos
        System.out.println("Voo adicionado com sucesso.");
    }

    /// Remove um voo do sistema. Solicita ao usuário o número do voo e, se encontrado,
    /// o voo é removido da lista de voos do aeroporto de origem correspondente.
    public void removeFlight() {
        int flightNumber = getInt("Digite o número do voo a ser removido: ");  // Solicita o número do voo

        boolean found = false;  // Variável de controle para indicar se o voo foi encontrado
        LinkedListDisordered<Airport>.Node airportNode = airports.getPrimeiro();  // Começa pelo primeiro aeroporto
        while (airportNode != null) {  // Percorre a lista de aeroportos
            LinkedListDisordered<Flight> flights = airportNode.getElemento().getFlights();  // Obtém a lista de voos do aeroporto
            LinkedListDisordered<Flight>.Node prev = null;
            LinkedListDisordered<Flight>.Node current = flights.getPrimeiro();  // Começa pelo primeiro voo

            // Percorre a lista de voos até encontrar o voo a ser removido
            while (current != null) {
                if (current.getElemento().getFlightNumber() == flightNumber) {  // Se o voo foi encontrado

                    if (prev == null) flights.removeFirst();  // Remove o primeiro voo da lista
                    else prev.setProximo(current.getProximo());  // Remove o voo intermediário

                    flights.tamanho--;  // Ajusta corretamente o tamanho da lista
                    System.out.println("Voo removido com sucesso.");

                    found = true;  // Indica que o voo foi encontrado e removido
                    break;
                }
                prev = current;  // Atualiza o ponteiro anterior
                current = current.getProximo();  // Passa para o próximo voo
            }
            if (found) break;  // Se o voo foi removido, sai do loop
            airportNode = airportNode.getProximo();  // Passa para o próximo aeroporto
        }
        if (!found) {
            System.err.println("Voo não encontrado.");  // Exibe erro se o voo não foi encontrado
        }
    }

    /// Lista todos os voos partindo de um aeroporto específico. Solicita o código do
    /// aeroporto e exibe os voos cadastrados para aquele aeroporto.
    public void listFlightsFromAirport() {
        String code = getNonEmptyString("Digite o código do aeroporto: ");  // Solicita o código do aeroporto

        Airport airport = findAirportByCode(code);  // Busca o aeroporto pelo código

        // Se o aeroporto não for encontrado, exibe mensagem de erro
        if (airport == null) {
            System.err.println("Aeroporto não encontrado.");
            return;
        }

        // Exibe os voos partindo do aeroporto encontrado
        System.out.println("Voos a partir de " + airport.getName() + ":");
        LinkedListDisordered<Flight>.Node flightNode = airport.getFlights().getPrimeiro();

        while (flightNode != null) {  // Percorre a lista de voos do aeroporto
            Flight flight = flightNode.getElemento();  // Obtém os detalhes do voo
            String destCode = flight.getDestinationCode();  // Obtém o código do destino
            Airport destAirport = findAirportByCode(destCode);  // Busca o aeroporto de destino
            String destName = (destAirport != null) ? destAirport.getName() : "Desconhecido";  // Se encontrado, exibe o nome do destino
            System.out.println("Voo " + flight.getFlightNumber() + " para " + destName + " (" + destCode + ")");
            flightNode = flightNode.getProximo();  // Passa para o próximo voo
        }
    }

    /// Lista todos os possíveis trajetos entre dois aeroportos. Solicita os códigos de origem
    /// e destino e exibe todos os caminhos possíveis entre os aeroportos, permitindo trajetos
    /// diretos e indiretos.
    public void listPathsBetweenAirports() {
        String sourceCode = getNonEmptyString("Digite o código do aeroporto de origem: ");  // Solicita o código do aeroporto de origem
        String destCode = getNonEmptyString("Digite o código do aeroporto de destino: ");  // Solicita o código do aeroporto de destino

        Airport sourceAirport = findAirportByCode(sourceCode);  // Busca o aeroporto de origem
        Airport destAirport = findAirportByCode(destCode);  // Busca o aeroporto de destino

        if (sourceAirport == null || destAirport == null) {
            System.err.println("Aeroporto de origem ou destino não encontrado.");
            return;
        }

        // Exibe os trajetos possíveis entre os dois aeroportos
        System.out.println("Possíveis trajetos de " + sourceAirport.getName() + " para " + destAirport.getName() + ":");

        LinkedListDisordered<String> path = new LinkedListDisordered<>();  // Lista para armazenar o trajeto atual
        LinkedListDisordered<String> visited = new LinkedListDisordered<>();  // Lista para armazenar os aeroportos visitados
        findPaths(sourceCode, destCode, path, visited);  // Inicia a busca pelos trajetos
    }

    /// Lista todos os aeroportos cadastrados no sistema. Percorre a lista encadeada de aeroportos
    /// e exibe o nome e o código de cada um.
    public void listAirports() {
        System.out.println("Aeroportos cadastrados no sistema:");
        LinkedListDisordered<Airport>.Node current = airports.getPrimeiro();

        if (current == null) {
            System.err.println("Nenhum aeroporto cadastrado.");
            return;
        }

        while (current != null) {
            Airport airport = current.getElemento();
            System.out.println(airport.getName() + " (" + airport.getCode() + ")");
            current = current.getProximo();
        }
    }

    /// Função recursiva que encontra todos os caminhos possíveis entre dois aeroportos.
    /// A função segue a técnica de busca em profundidade (DFS) para explorar todas as rotas.
    ///
    /// @param currentCode Código do aeroporto atual no caminho.
    /// @param destCode    Código do aeroporto de destino.
    /// @param path        Caminho atual sendo percorrido.
    /// @param visited     Lista de aeroportos já visitados para evitar ciclos.
    private void findPaths(
            String currentCode,
            String destCode,
            LinkedListDisordered<String> path,
            LinkedListDisordered<String> visited
    ) {
        path.addLast(currentCode);
        visited.addLast(currentCode);

        // Mensagem de depuração
        System.out.println("Visitando: " + currentCode);

        if (currentCode.equalsIgnoreCase(destCode)) {
            System.out.println("Destino alcançado!");
            printPath(path);
        } else {
            Airport currentAirport = findAirportByCode(currentCode);
            if (currentAirport != null) {
                LinkedListDisordered<Flight>.Node flightNode = currentAirport.getFlights().getPrimeiro();

                if (flightNode == null) System.out.println("Nenhum voo encontrado a partir de: " + currentCode);

                while (flightNode != null) {
                    String nextCode = flightNode.getElemento().getDestinationCode();

                    // Mensagem de depuração para cada voo encontrado
                    System.out.println("Voo encontrado: " + currentCode + " -> " + nextCode);

                    if (!visited.contains(nextCode)) findPaths(nextCode, destCode, path, visited);

                    flightNode = flightNode.getProximo();
                }
            } else {
                System.err.println("Aeroporto não encontrado: " + currentCode);
            }
        }

        path.removeLast();
        visited.removeLast();
    }

    /// Função para listar todas as combinações possíveis de trajetos entre dois aeroportos distintos.
    /// Esta função ignora a existência de voos e lista todas as sequências possíveis de aeroportos
    /// do aeroporto de origem ao aeroporto de destino.
    ///
    /// @param sourceCode Código do aeroporto de origem.
    /// @param destCode   Código do aeroporto de destino.
    public void listAllPossiblePaths(String sourceCode, String destCode) {
        // Validação inicial: verifica se os códigos dos aeroportos de origem e destino são diferentes.
        // Caso sejam iguais, não há sentido em listar trajetos, então a função exibe um erro e retorna.
        if (sourceCode.equalsIgnoreCase(destCode)) {
            System.err.println("Os códigos dos aeroportos de origem e destino devem ser distintos.");
            return;
        }

        // Busca o aeroporto de origem e o aeroporto de destino pelos códigos fornecidos.
        // Se algum dos aeroportos não for encontrado, a função exibe uma mensagem de erro e retorna.
        Airport sourceAirport = findAirportByCode(sourceCode);
        Airport destAirport = findAirportByCode(destCode);

        if (sourceAirport == null || destAirport == null) {
            System.err.println("Aeroporto de origem ou destino não encontrado.");
            return;
        }

        // Coleta todos os aeroportos intermediários (excluindo o de origem e o de destino)
        // para poderem ser combinados nos trajetos. Usa uma lista 'intermediateCodes' para armazenar os códigos.
        List<String> intermediateCodes = new ArrayList<>();
        LinkedListDisordered<Airport>.Node current = airports.getPrimeiro();

        // Percorre todos os aeroportos registrados, exceto o de origem e o de destino,
        // e adiciona os códigos dos intermediários na lista 'intermediateCodes'.
        while (current != null) {
            String code = current.getElemento().getCode();
            if (!code.equalsIgnoreCase(sourceCode) && !code.equalsIgnoreCase(destCode))
                intermediateCodes.add(code.toUpperCase()); // Adiciona o código em maiúsculas para padronizar
            current = current.getProximo(); // Move para o próximo aeroporto na lista
        }

        // Lista para armazenar todas as combinações de aeroportos intermediários.
        List<List<String>> allCombinations = new ArrayList<>();

        // `n` é o número total de aeroportos intermediários.
        int n = intermediateCodes.size();

        // Gera todas as combinações possíveis de aeroportos intermediários de tamanho 'k', onde 'k' varia de 0 a 'n'.
        // 'combine' é uma função auxiliar que gera as combinações recursivamente.
        for (int k = 0; k <= n; k++)  // 'k' é o tamanho da combinação (número de aeroportos intermediários)
            combine(intermediateCodes, 0, k, new ArrayList<>(), allCombinations);

        // Exibe uma mensagem inicial informando os trajetos possíveis de origem a destino.
        System.out.println("Listando todas as combinações possíveis de trajetos de "
                           + sourceAirport.getName() + " (" + sourceCode.toUpperCase() + ") para "
                           + destAirport.getName() + " (" + destCode.toUpperCase() + "):\n");

        // 'trajetoNumero' é um contador para numerar os trajetos listados.
        int trajetoNumero = 1;

        // Para cada combinação de aeroportos intermediários, geramos todas as permutações possíveis.
        // Isso porque, para cada combinação, a ordem dos aeroportos pode ser rearranjada.
        for (List<String> combination : allCombinations) {

            List<List<String>> permutations = permute(combination); // 'permute' gera todas as permutações da combinação.

            for (List<String> permuted : permutations) {
                // Constrói o trajeto completo, começando pelo aeroporto de origem, passando pelos intermediários permutados, e terminando no aeroporto de destino.
                StringBuilder trajeto = new StringBuilder();
                trajeto.append(sourceCode.toUpperCase()); // Adiciona o aeroporto de origem

                for (String code : permuted)
                    trajeto.append(" -> ").append(code); // Adiciona cada aeroporto intermediário
                trajeto.append(" -> ").append(destCode.toUpperCase()); // Adiciona o aeroporto de destino

                // Exibe o trajeto formatado.
                System.out.println("Trajeto " + trajetoNumero + ": " + trajeto);
                trajetoNumero++;
            }
        }

        // Exibe o número total de trajetos possíveis encontrados.
        System.out.println("\nTotal de trajetos possíveis: " + (trajetoNumero - 1));
    }


    /// Função auxiliar para gerar combinações de aeroportos.
    /// Esta função gera todas as combinações possíveis de aeroportos a partir da lista de códigos fornecida.
    /// A combinação é controlada pelo valor de 'k', que indica o número de elementos a serem escolhidos.
    ///
    /// @param codes     Lista de códigos de aeroportos.
    /// @param start     Índice inicial para a combinação atual.
    /// @param k         Tamanho da combinação desejada (quantidade de elementos).
    /// @param current   Combinação atual sendo construída (parcialmente completa).
    /// @param allCombos Lista de todas as combinações geradas.
    private void combine(
            List<String> codes,
            int start,
            int k,
            List<String> current,
            List<List<String>> allCombos
    ) {
        // Caso base: quando 'k' chega a zero, significa que a combinação atual tem o tamanho desejado.
        // A combinação é adicionada à lista 'allCombos', criando uma nova instância para não alterar a original.
        if (k == 0) {
            allCombos.add(new ArrayList<>(current));  // Armazena a combinação atual
            return;
        }

        // Loop para percorrer os códigos de aeroportos a partir do índice 'start'.
        // `i <= codes.size() - k` garante que ainda haja aeroportos suficientes para completar a combinação.
        for (int i = start; i <= codes.size() - k; i++) {
            current.add(codes.get(i)); // Adiciona o código atual à combinação em construção.
            combine(codes, i + 1, k - 1, current, allCombos); // Chama recursivamente 'combine' para continuar construindo a combinação com o próximo elemento.
            current.removeLast(); // Remove o último elemento adicionado (backtracking) para tentar a próxima combinação possível.
        }
    }

    /// Função auxiliar para gerar todas as permutações de uma combinação de aeroportos.
    /// Permutações são rearranjos das combinações onde a ordem dos aeroportos é importante.
    ///
    /// @param combination Combinação de códigos de aeroportos a ser permutada.
    /// @return Lista de todas as permutações possíveis da combinação.
    private List<List<String>> permute(List<String> combination) {
        List<List<String>> results = new ArrayList<>();

        // Caso base: se a combinação estiver vazia, retorna uma permutação vazia.
        if (combination.isEmpty()) {
            results.add(new ArrayList<>());  // Permutação de uma lista vazia
            return results;
        }

        // Loop para cada elemento da combinação. O elemento 'current' será fixado,
        // enquanto o restante será permutado recursivamente.
        for (int i = 0; i < combination.size(); i++) {
            // Armazena o elemento atual da combinação como 'current'.
            String current = combination.get(i);

            // Cria uma nova lista 'remaining', removendo o elemento atual para permutar os restantes.
            List<String> remaining = new ArrayList<>(combination);
            remaining.remove(i);

            // Chama recursivamente 'permute' para permutar o restante dos elementos.
            List<List<String>> remainingPermuted = permute(remaining);

            // Para cada permutação do restante, adiciona o elemento 'current' no início da permutação,
            // gerando uma nova permutação completa que inclui 'current'
            for (List<String> permuted : remainingPermuted) {
                List<String> newPermuted = new ArrayList<>();
                newPermuted.add(current);  // Adiciona o elemento fixado no início
                newPermuted.addAll(permuted);  // Adiciona o restante permutado

                results.add(newPermuted); // Adiciona a nova permutação à lista de resultados.
            }
        }

        // Retorna todas as permutações possíveis da combinação original.
        return results;
    }

    /// Função para imprimir um caminho representado por uma lista encadeada de aeroportos.
    /// Formata e exibe o caminho, ligando os aeroportos com " -> " para indicar a sequência.
    ///
    /// @param path Lista encadeada contendo o caminho dos aeroportos.
    private void printPath(LinkedListDisordered<String> path) {
        StringBuilder sb = new StringBuilder();

        // Percorre a lista encadeada de aeroportos, começando pelo primeiro.
        LinkedListDisordered<String>.Node current = path.getPrimeiro();

        // Concatena o código de cada aeroporto, adicionando " -> " entre eles para indicar o trajeto.
        while (current != null) {
            sb.append(current.getElemento());  // Adiciona o código do aeroporto atual

            // Adiciona a seta (" → ") se ainda houver um próximo aeroporto no trajeto.
            if (current.getProximo() != null) sb.append(" -> ");

            current = current.getProximo();  // Avança para o próximo aeroporto
        }

        // Exibe o caminho formatado.
        System.out.println(sb);
    }
}
