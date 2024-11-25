import estruturas.LinkedList.Disordered.LinkedListDisordered;

import java.util.ArrayList;
import java.util.List;

import static util.Input.getInt;
import static util.Input.getNonEmptyString;

/// # FlightOrganizer
///
/// A classe `FlightOrganizer` é responsável por organizar aeroportos e voos, permitindo
/// o cadastro de novos aeroportos e voos, bem como a listagem de voos e trajetos possíveis
/// entre aeroportos. Utiliza uma estrutura de dados encadeada desordenada (`LinkedListDisordered`)
/// para armazenar os aeroportos e seus respectivos voos.
///
/// ## Funcionalidades
/// - **Cadastro de Aeroportos (`addAirport`):** Permite adicionar novos aeroportos ao sistema, garantindo que os códigos sejam únicos.
/// - **Cadastro de Voos (`addFlight`):** Permite adicionar novos voos entre aeroportos existentes, garantindo que os números de voo sejam únicos.
/// - **Remoção de Voos (`removeFlight`):** Permite remover voos existentes do sistema com base no número do voo.
/// - **Listagem de Voos (`listFlightsFromAirport`):** Exibe todos os voos partindo de um aeroporto específico.
/// - **Listagem de Trajetos (`listPathsBetweenAirports`, `listAllPossiblePaths`):** Exibe todos os trajetos possíveis entre dois aeroportos, incluindo trajetos diretos e indiretos.
/// - **Listagem de Aeroportos (`listAirports`):** Exibe todos os aeroportos cadastrados no sistema.
///
/// ## Estrutura Interna
/// - **airports:** Lista encadeada desordenada que armazena os aeroportos do sistema.
///
/// ## Uso
/// Crie uma instância de `FlightOrganizer` e utilize os metodos disponíveis para gerenciar aeroportos e voos.
///
/// ### Exemplo
/// ```java
/// FlightOrganizer organizer = new FlightOrganizer();
/// organizer.addAirport();
/// organizer.addFlight();
/// organizer.listFlightsFromAirport();
/// organizer.listPathsBetweenAirports();
/// organizer.listAirports();
///```
public class FlightOrganizer {

    /// ### Campos
    ///
    /// - **`airports`:** Lista encadeada desordenada que armazena os aeroportos do sistema.
    private final LinkedListDisordered<Airport> airports;

    /// ## FlightOrganizer
    ///
    /// Construtor da classe `FlightOrganizer`. Inicializa a lista de aeroportos e
    /// adiciona alguns aeroportos predefinidos no sistema.
    ///
    /// ### Fluxo de Operações
    /// 1. Inicializa `airports` como uma nova instância de `LinkedListDisordered`.
    /// 2. Chama o metodo `initializeAirports` para adicionar aeroportos predefinidos.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste construtor.
    ///
    /// ### Exemplo
    /// ```java
    /// FlightOrganizer organizer = new FlightOrganizer();
    ///```
    ///
    /// @see #initializeAirports()
    public FlightOrganizer() {
        airports = new LinkedListDisordered<>();
        initializeAirports();
    }

    /// ## initializeAirports
    ///
    /// Inicializa a lista de aeroportos com valores predefinidos, adicionando cinco
    /// aeroportos: Belo Horizonte, Brasília, Rio de Janeiro, Salvador e São Paulo.
    ///
    /// ### Fluxo de Operações
    /// 1. Adiciona cinco instâncias de `Airport` com nomes e códigos específicos à lista `airports`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// initializeAirports();
    ///```
    ///
    /// @see #FlightOrganizer()
    private void initializeAirports() {
        airports.addLast(new Airport("Belo Horizonte", "CNF"));
        airports.addLast(new Airport("Brasília", "BSB"));
        airports.addLast(new Airport("Rio de Janeiro", "GIG"));
        airports.addLast(new Airport("Salvador", "SSA"));
        airports.addLast(new Airport("São Paulo", "GRU"));
    }

    /// ## findAirportByCode
    ///
    /// Procura um aeroporto pelo seu código. Percorre a lista encadeada de aeroportos
    /// e retorna o aeroporto correspondente ao código fornecido.
    ///
    /// ### Parâmetros
    /// - **`code`:** O código do aeroporto a ser encontrado.
    ///
    /// ### Retorno
    /// - **`Airport`:** O aeroporto correspondente ao código fornecido ou `null` se não for encontrado.
    ///
    /// ### Fluxo de Operações
    /// 1. Inicia a busca pelo primeiro nó da lista `airports`.
    /// 2. Itera através da lista até encontrar o aeroporto com o código correspondente.
    /// 3. Retorna o aeroporto encontrado ou `null` se não existir.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// Airport airport = findAirportByCode("GRU");
    /// if (airport != null){
    ///     System.out.println("Aeroporto encontrado: " + airport.getName());
    ///}
    ///```
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

    /// ## addAirport
    ///
    /// Adiciona um novo aeroporto ao sistema. Solicita ao usuário o nome e o código
    /// do aeroporto e o adiciona à lista encadeada, desde que o código não exista previamente.
    ///
    /// ### Fluxo de Operações
    /// 1. Solicita ao usuário o nome do aeroporto.
    /// 2. Solicita ao usuário o código do aeroporto.
    /// 3. Verifica se já existe um aeroporto com o mesmo código usando `findAirportByCode`.
    /// 4. Se o código for único, cria uma nova instância de `Airport` e a adiciona à lista `airports`.
    /// 5. Informa ao usuário que o aeroporto foi adicionado com sucesso ou exibe uma mensagem de erro se o código já existir.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// FlightOrganizer organizer = new FlightOrganizer();
    /// organizer.addAirport();
    ///```
    ///
    /// @see #findAirportByCode(String)
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

    /// ## addFlight
    ///
    /// Adiciona um voo entre dois aeroportos. Solicita ao usuário o código do aeroporto
    /// de origem, o código do aeroporto de destino e o número do voo. Se os aeroportos forem
    /// válidos e o número do voo não estiver duplicado, o voo é cadastrado.
    ///
    /// ### Fluxo de Operações
    /// 1. Solicita ao usuário o código do aeroporto de origem.
    /// 2. Solicita ao usuário o código do aeroporto de destino.
    /// 3. Solicita ao usuário o número do voo.
    /// 4. Verifica se os aeroportos de origem e destino existem usando `findAirportByCode`.
    /// 5. Verifica se o número do voo já existe em qualquer aeroporto.
    /// 6. Se todas as verificações passarem, cria uma nova instância de `Flight` e a adiciona ao aeroporto de origem.
    /// 7. Informa ao usuário que o voo foi adicionado com sucesso ou exibe uma mensagem de erro se houver duplicações.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// FlightOrganizer organizer = new FlightOrganizer();
    /// organizer.addFlight();
    ///```
    ///
    /// @see #findAirportByCode(String)
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

    /// ## removeFlight
    ///
    /// Remove um voo do sistema. Solicita ao usuário o número do voo e, se encontrado,
    /// o voo é removido da lista de voos do aeroporto de origem correspondente.
    ///
    /// ### Fluxo de Operações
    /// 1. Solicita ao usuário o número do voo a ser removido.
    /// 2. Itera através da lista de aeroportos e de seus voos para encontrar o voo correspondente.
    /// 3. Se o voo for encontrado, remove-o da lista de voos do aeroporto de origem.
    /// 4. Informa ao usuário que o voo foi removido com sucesso ou exibe uma mensagem de erro se o voo não for encontrado.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// FlightOrganizer organizer = new FlightOrganizer();
    /// organizer.removeFlight();
    ///```
    ///
    /// @see #findAirportByCode(String)
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

    /// ## listFlightsFromAirport
    ///
    /// Lista todos os voos partindo de um aeroporto específico. Solicita o código do
    /// aeroporto e exibe os voos cadastrados para aquele aeroporto.
    ///
    /// ### Fluxo de Operações
    /// 1. Solicita ao usuário o código do aeroporto.
    /// 2. Busca o aeroporto correspondente usando `findAirportByCode`.
    /// 3. Se o aeroporto for encontrado, itera sobre sua lista de voos e exibe detalhes de cada voo.
    /// 4. Exibe mensagens de erro se o aeroporto não for encontrado ou se não houver voos cadastrados.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// FlightOrganizer organizer = new FlightOrganizer();
    /// organizer.listFlightsFromAirport();
    ///```
    ///
    /// @see #findAirportByCode(String)
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

    /// ## listPathsBetweenAirports
    ///
    /// Lista todos os possíveis trajetos entre dois aeroportos. Solicita os códigos de origem
    /// e destino e exibe todos os caminhos possíveis entre os aeroportos, permitindo trajetos
    /// diretos e indiretos.
    ///
    /// ### Fluxo de Operações
    /// 1. Solicita ao usuário o código do aeroporto de origem.
    /// 2. Solicita ao usuário o código do aeroporto de destino.
    /// 3. Busca os aeroportos de origem e destino usando `findAirportByCode`.
    /// 4. Se ambos os aeroportos forem encontrados, inicia a busca por trajetos utilizando `findPaths`.
    /// 5. Exibe mensagens de erro se os aeroportos não forem encontrados.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// FlightOrganizer organizer = new FlightOrganizer();
    /// organizer.listPathsBetweenAirports();
    ///```
    ///
    /// @see #findAirportByCode(String)
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
        LinkedListDisordered<String> visited = new LinkedListDisordered<>();  // Lista para armazenar os aeroportos já visitados
        findPaths(sourceCode, destCode, path, visited);  // Inicia a busca pelos trajetos
    }

    /// ## listAirports
    ///
    /// Lista todos os aeroportos cadastrados no sistema. Percorre a lista encadeada de aeroportos
    /// e exibe o nome e o código de cada um.
    ///
    /// ### Fluxo de Operações
    /// 1. Inicia a iteração pelo primeiro nó da lista `airports`.
    /// 2. Itera através da lista, exibindo o nome e o código de cada aeroporto.
    /// 3. Exibe mensagens de erro se não houver aeroportos cadastrados.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// FlightOrganizer organizer = new FlightOrganizer();
    /// organizer.listAirports();
    ///```
    ///
    /// @see #findAirportByCode(String)
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

    /// ## findPaths
    ///
    /// Função recursiva que encontra todos os caminhos possíveis entre dois aeroportos.
    /// A função segue a técnica de busca em profundidade (DFS) para explorar todas as rotas.
    ///
    /// ### Parâmetros
    /// - **`currentCode`:** Código do aeroporto atual no caminho.
    /// - **`destCode`:** Código do aeroporto de destino.
    /// - **`path`:** Caminho atual sendo percorrido.
    /// - **`visited`:** Lista de aeroportos já visitados para evitar ciclos.
    ///
    /// ### Fluxo de Operações
    /// 1. Adiciona o aeroporto atual ao `path` e marca-o como visitado.
    /// 2. Se o aeroporto atual for o destino, imprime o trajeto.
    /// 3. Caso contrário, itera sobre todos os voos do aeroporto atual:
    ///    - Para cada voo, se o destino não tiver sido visitado, chama recursivamente `findPaths`.
    /// 4. Remove o aeroporto atual do `path` e da lista de `visited` (backtracking).
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// findPaths("GRU", "GIG", new LinkedListDisordered<>(), new LinkedListDisordered<>());
    ///```
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

    /// ## listAllPossiblePaths
    ///
    /// Função para listar todas as combinações possíveis de trajetos entre dois aeroportos distintos.
    /// Esta função ignora a existência de voos e lista todas as sequências possíveis de aeroportos
    /// do aeroporto de origem ao aeroporto de destino.
    ///
    /// ### Parâmetros
    /// - **`sourceCode`:** Código do aeroporto de origem.
    /// - **`destCode`:** Código do aeroporto de destino.
    ///
    /// ### Fluxo de Operações
    /// 1. Valida se os códigos dos aeroportos de origem e destino são distintos.
    /// 2. Busca os aeroportos de origem e destino usando `findAirportByCode`.
    /// 3. Coleta todos os aeroportos intermediários (excluindo os de origem e destino).
    /// 4. Gera todas as combinações possíveis de aeroportos intermediários.
    /// 5. Para cada combinação, gera todas as permutações possíveis.
    /// 6. Constrói e exibe cada trajeto completo a partir das permutações.
    /// 7. Informa o número total de trajetos possíveis encontrados.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// FlightOrganizer organizer = new FlightOrganizer();
    /// organizer.listAllPossiblePaths("GRU", "GIG");
    ///```
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
        List<List<String>> allCombos = new ArrayList<>();

        // `n` é o número total de aeroportos intermediários.
        int n = intermediateCodes.size();

        // Gera todas as combinações possíveis de aeroportos intermediários de tamanho 'k', onde 'k' varia de 0 a 'n'.
        // 'combine' é uma função auxiliar que gera as combinações recursivamente.
        for (int k = 0; k <= n; k++)  // 'k' é o tamanho da combinação (número de aeroportos intermediários)
            combine(intermediateCodes, 0, k, new ArrayList<>(), allCombos);

        // Exibe uma mensagem inicial informando os trajetos possíveis de origem a destino.
        System.out.println("Listando todas as combinações possíveis de trajetos de "
                           + sourceAirport.getName() + " (" + sourceCode.toUpperCase() + ") para "
                           + destAirport.getName() + " (" + destCode.toUpperCase() + "):\n");

        // 'trajetoNumero' é um contador para numerar os trajetos listados.
        int trajetoNumero = 1;

        // Para cada combinação de aeroportos intermediários, geramos todas as permutações possíveis.
        // Isso porque, para cada combinação, a ordem dos aeroportos pode ser rearranjada.
        for (List<String> combination : allCombos) {

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

    /// ## combine
    ///
    /// Função auxiliar para gerar combinações de aeroportos.
    /// Esta função gera todas as combinações possíveis de aeroportos a partir da lista de códigos fornecida.
    /// A combinação é controlada pelo valor de `k`, que indica o número de elementos a serem escolhidos.
    ///
    /// ### Parâmetros
    /// - **`codes`:** Lista de códigos de aeroportos.
    /// - **`start`:** Índice inicial para a combinação atual.
    /// - **`k`:** Tamanho da combinação desejada (quantidade de elementos).
    /// - **`current`:** Combinação atual sendo construída (parcialmente completa).
    /// - **`allCombos`:** Lista de todas as combinações geradas.
    ///
    /// ### Fluxo de Operações
    /// 1. Se `k` for zero, adiciona a combinação atual à lista `allCombos` e retorna.
    /// 2. Itera sobre os códigos a partir do índice `start`.
    /// 3. Adiciona o código atual à combinação e chama recursivamente `combine` com `k-1`.
    /// 4. Remove o último elemento adicionado (backtracking) para tentar a próxima combinação possível.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// List<List<String>> allCombos = new ArrayList<>();
    /// combine(intermediateCodes, 0, 2, new ArrayList<>(), allCombos);
    ///```
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

    /// ## permute
    ///
    /// Função auxiliar para gerar todas as permutações de uma combinação de aeroportos.
    /// Permutações são rearranjos das combinações onde a ordem dos aeroportos é importante.
    ///
    /// ### Parâmetros
    /// - **`combination`:** Combinação de códigos de aeroportos a ser permutada.
    ///
    /// ### Retorno
    /// - **`List<List<String>>`:** Lista de todas as permutações possíveis da combinação.
    ///
    /// ### Fluxo de Operações
    /// 1. Se a combinação estiver vazia, adiciona uma lista vazia à lista de resultados e retorna.
    /// 2. Itera sobre cada elemento da combinação, fixando-o como o primeiro elemento.
    /// 3. Gera permutações recursivamente para o restante dos elementos.
    /// 4. Adiciona cada permutação resultante à lista de resultados.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// List<String> combination = Arrays.asList("SSA", "BSB");
    /// List<List<String>> permutations = permute(combination);
    /// // permutations = [["SSA", "BSB"],["BSB", "SSA"]]
    ///```
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

    /// ## printPath
    ///
    /// Função para imprimir um caminho representado por uma lista encadeada de aeroportos.
    /// Formata e exibe o caminho, ligando os aeroportos com " -> " para indicar a sequência.
    ///
    /// ### Parâmetros
    /// - **`path`:** Lista encadeada contendo o caminho dos aeroportos.
    ///
    /// ### Fluxo de Operações
    /// 1. Inicializa um `StringBuilder`.
    /// 2. Itera sobre cada código de aeroporto na lista `path`, concatenando-os com " -> ".
    /// 3. Exibe o caminho formatado no console.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// LinkedListDisordered<String> path = new LinkedListDisordered<>();
    /// path.addLast("GRU");
    /// path.addLast("GIG");
    /// printPath(path);
    /// // Saída: GRU -> GIG
    ///```
    ///
    /// @param path Lista encadeada contendo o caminho dos aeroportos.
    private void printPath(LinkedListDisordered<String> path) {
        StringBuilder sb = new StringBuilder();

        // Percorre a lista encadeada de aeroportos, começando pelo primeiro.
        LinkedListDisordered<String>.Node current = path.getPrimeiro();

        // Concatena o código de cada aeroporto, adicionando " -> " entre eles para indicar o trajeto.
        while (current != null) {
            sb.append(current.getElemento());  // Adiciona o código do aeroporto atual

            // Adiciona a seta (" -> ") se ainda houver um próximo aeroporto no trajeto.
            if (current.getProximo() != null) sb.append(" -> ");

            current = current.getProximo();  // Avança para o próximo aeroporto
        }

        // Exibe o caminho formatado.
        System.out.println(sb);
    }
}
