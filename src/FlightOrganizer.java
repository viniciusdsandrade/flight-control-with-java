import estruturas.LinkedList.Disordered.LinkedListDisordered;

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
            if (current.getElemento().getCode().equalsIgnoreCase(code)) {
                return current.getElemento();  // Retorna o aeroporto se for encontrado
            }
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

        // Se o código não existir, cria e adiciona o novo aeroporto à lista
        Airport newAirport = new Airport(name, code);
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

        // Verifica se já existe um voo com o mesmo número partindo do aeroporto de origem
        LinkedListDisordered<Flight>.Node current = sourceAirport.getFlights().getPrimeiro();
        while (current != null) {  // Percorre a lista de voos do aeroporto de origem
            if (current.getElemento().getFlightNumber() == flightNumber) {
                System.err.println("Número de voo já existe para este aeroporto.");
                return;  // Se o voo já existir, exibe erro e encerra
            }
            current = current.getProximo();  // Passa para o próximo voo
        }

        // Se o voo não existir, cria e adiciona o novo voo à lista
        Flight newFlight = new Flight(destCode, flightNumber);
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
                    if (prev == null) {
                        flights.removeFirst();  // Remove o primeiro voo da lista
                    } else {
                        prev.setProximo(current.getProximo());  // Remove o voo intermediário
                        flights.tamanho--;
                    }
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
            System.out.println("Voo não encontrado.");  // Exibe erro se o voo não foi encontrado
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
            System.out.println("Nenhum aeroporto cadastrado.");
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
    /// @param destCode Código do aeroporto de destino.
    /// @param path Caminho atual sendo percorrido.
    /// @param visited Lista de aeroportos já visitados para evitar ciclos.
    private void findPaths(
            String currentCode,
            String destCode,
            LinkedListDisordered<String> path,
            LinkedListDisordered<String> visited
    ) {
        path.addLast(currentCode);  // Adiciona o aeroporto atual ao caminho
        visited.addLast(currentCode);  // Marca o aeroporto como visitado

        if (currentCode.equalsIgnoreCase(destCode)) {
            printPath(path);  // Se o destino foi alcançado, imprime o caminho
        } else {
            // Procura o aeroporto atual na lista de aeroportos
            Airport currentAirport = findAirportByCode(currentCode);
            if (currentAirport != null) {
                // Percorre todos os voos partindo do aeroporto atual
                LinkedListDisordered<Flight>.Node flightNode = currentAirport.getFlights().getPrimeiro();
                while (flightNode != null) {
                    String nextCode = flightNode.getElemento().getDestinationCode();

                    // Se o próximo aeroporto ainda não foi visitado, segue o caminho
                    if (!visited.contains(nextCode)) {
                        findPaths(nextCode, destCode, path, visited);  // Chamada recursiva para o próximo aeroporto
                    }
                    flightNode = flightNode.getProximo();  // Move para o próximo voo
                }
            }
        }

        // Remove o aeroporto atual do caminho e da lista de visitados para permitir outras rotas
        path.removeLast();
        visited.removeLast();
    }

    /// Imprime o caminho encontrado entre dois aeroportos. O caminho é impresso no formato
    /// de lista encadeada representando os aeroportos percorridos.
    ///
    /// @param path O caminho percorrido entre dois aeroportos.
    private void printPath(LinkedListDisordered<String> path) {
        System.out.println(path);
    }
}
