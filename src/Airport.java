import estruturas.LinkedList.Disordered.LinkedListDisordered;

import static estruturas.ShallowOrDeepCopy.ShallowOrDeepCopy.verifyAndCopy;

/// # Airport
///
/// A classe `Airport` representa um aeroporto, com um nome, um código de identificação
/// e uma lista de voos (`flights`) associados a ele. A classe também oferece mecanismos
/// para criar cópias profundas, verificar igualdade entre aeroportos e gerar representações textuais.
///
/// ## Funcionalidades
/// - **Criação de Aeroportos:** Permite criar novos aeroportos com nome e código únicos.
/// - **Gerenciamento de Voos:** Permite adicionar, remover e listar voos associados a um aeroporto.
/// - **Cópia de Aeroportos (`clone` e construtor de cópia):** Permite criar cópias de aeroportos existentes.
/// - **Comparação de Aeroportos (`equals`, `hashCode`):** Permite comparar aeroportos para verificar igualdade.
/// - **Representação Textual (`toString`):** Fornece uma representação textual legível do aeroporto.
///
/// ## Estrutura Interna
/// - **`name`:** Nome do aeroporto (por exemplo, "Belo Horizonte").
/// - **`code`:** Código do aeroporto (por exemplo, "CNF").
/// - **`flights`:** Lista encadeada de voos associados ao aeroporto.
///
/// ## Uso
/// Crie instâncias de `Airport` utilizando o construtor padrão ou o construtor de cópia, e utilize os metodos disponíveis para gerenciar voos e comparar aeroportos.
///
/// ### Exemplo
/// ```java
/// Airport airport1 = new Airport("São Paulo", "GRU");
/// Flight flight1 = new Flight("GIG", 5678);
/// airport1.getFlights().addLast(flight1);
///
/// Airport airport2 = (Airport) airport1.clone();
/// boolean areEqual = airport1.equals(airport2); // true
/// System.out.println(airport1); // Airport{name='São Paulo', code='GRU', flights=...}
///```
public class Airport implements Cloneable {

    /// ### Campos
    ///
    /// - **`name`:** Nome do aeroporto (por exemplo, "Belo Horizonte").
    /// - **`code`:** Código do aeroporto (por exemplo, "CNF").
    /// - **`flights`:** Lista encadeada de voos associados ao aeroporto.
    private String name;
    private String code;
    private final LinkedListDisordered<Flight> flights;

    /// ## Airport
    ///
    /// Construtor da classe `Airport`. Inicializa um aeroporto com um nome e um código,
    /// e uma lista vazia de voos associados a ele.
    ///
    /// ### Parâmetros
    /// - **`name`:** O nome do aeroporto.
    /// - **`code`:** O código do aeroporto.
    ///
    /// ### Fluxo de Operações
    /// 1. Atribui o nome do aeroporto ao campo `name`.
    /// 2. Atribui o código do aeroporto ao campo `code`.
    /// 3. Inicializa a lista de voos (`flights`) como uma nova instância de `LinkedListDisordered`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste construtor.
    ///
    /// ### Exemplo
    /// ```java
    /// Airport airport = new Airport("Belo Horizonte", "CNF");
    ///```
    ///
    /// @param name O nome do aeroporto.
    /// @param code O código do aeroporto.
    public Airport(String name, String code) {
        this.name = name;  // Atribui o nome do aeroporto.
        this.code = code;  // Atribui o código do aeroporto.
        this.flights = new LinkedListDisordered<>();  // Inicializa a lista de voos como vazia.
    }

    /// ## getName
    ///
    /// Retorna o nome do aeroporto.
    ///
    /// ### Retorno
    /// - **`String`:** O nome do aeroporto.
    ///
    /// ### Fluxo de Operações
    /// 1. Retorna o valor do campo `name`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// String name = airport.getName();
    ///```
    ///
    /// @return O nome do aeroporto.
    public String getName() {
        return name;  // Retorna o nome do aeroporto.
    }

    /// ## getCode
    ///
    /// Retorna o código do aeroporto.
    ///
    /// ### Retorno
    /// - **`String`:** O código do aeroporto.
    ///
    /// ### Fluxo de Operações
    /// 1. Retorna o valor do campo `code`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// String code = airport.getCode();
    ///```
    ///
    /// @return O código do aeroporto.
    public String getCode() {
        return code;  // Retorna o código do aeroporto.
    }

    /// ## setName
    ///
    /// Define um novo nome para o aeroporto.
    ///
    /// ### Parâmetros
    /// - **`name`:** O novo nome do aeroporto.
    ///
    /// ### Fluxo de Operações
    /// 1. Atualiza o campo `name` com o novo nome fornecido.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// airport.setName("Rio de Janeiro");
    ///```
    ///
    /// @param name O novo nome do aeroporto.
    public void setName(String name) {
        this.name = name;  // Atualiza o nome do aeroporto.
    }

    /// ## setCode
    ///
    /// Define um novo código para o aeroporto.
    ///
    /// ### Parâmetros
    /// - **`code`:** O novo código do aeroporto.
    ///
    /// ### Fluxo de Operações
    /// 1. Atualiza o campo `code` com o novo código fornecido.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// airport.setCode("GIG");
    ///```
    ///
    /// @param code O novo código do aeroporto.
    public void setCode(String code) {
        this.code = code;  // Atualiza o código do aeroporto.
    }

    /// ## getFlights
    ///
    /// Retorna a lista encadeada de voos associados ao aeroporto.
    ///
    /// ### Retorno
    /// - **`LinkedListDisordered<Flight>`:** A lista de voos do aeroporto.
    ///
    /// ### Fluxo de Operações
    /// 1. Retorna a referência para a lista `flights`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// LinkedListDisordered<Flight> flights = airport.getFlights();
    ///```
    ///
    /// @return A lista de voos do aeroporto.
    public LinkedListDisordered<Flight> getFlights() {
        return flights;  // Retorna a lista de voos associados ao aeroporto.
    }

    /// ## Airport (Construtor de Cópia)
    ///
    /// Construtor de cópia. Cria um novo objeto `Airport` a partir de outro, realizando uma cópia profunda
    /// dos atributos complexos, como a lista de voos (`flights`).
    ///
    /// ### Parâmetros
    /// - **`other`:** O aeroporto a ser copiado.
    ///
    /// ### Fluxo de Operações
    /// 1. Verifica se o objeto `other` é nulo e lança uma exceção se for.
    /// 2. Copia o nome do aeroporto utilizando a função `verifyAndCopy`, garantindo uma cópia segura.
    /// 3. Copia o código do aeroporto da mesma maneira.
    /// 4. Realiza uma cópia profunda da lista de voos (`flights`) usando `verifyAndCopy`.
    ///
    /// ### Exceções
    /// - **`IllegalArgumentException`:** Lança exceção se o objeto `other` for nulo.
    ///
    /// ### Exemplo
    /// ```java
    /// Airport original = new Airport("São Paulo", "GRU");
    /// Airport copy = new Airport(original);
    ///```
    ///
    /// @param other O aeroporto a ser copiado.
    @SuppressWarnings("unchecked")
    public Airport(Airport other) {
        // Verifica se o aeroporto fornecido é nulo, e lança uma exceção se for.
        if (other == null) throw new IllegalArgumentException("Airport não pode ser nulo");

        // Copia o nome do aeroporto usando a função 'verifyAndCopy', garantindo uma cópia segura.
        this.name = (String) verifyAndCopy(other.name);

        // Copia o código do aeroporto da mesma maneira.
        this.code = (String) verifyAndCopy(other.code);

        // Realiza uma cópia profunda da lista de voos.
        this.flights = (LinkedListDisordered<Flight>) verifyAndCopy(other.flights);
    }

    /// ## clone
    ///
    /// metodo que cria uma cópia deste objeto `Airport`. Este metodo sobrescreve `clone()` para
    /// criar uma cópia personalizada sem chamar `super.clone()`, conforme especificado.
    ///
    /// ### Retorno
    /// - **`Object`:** Uma cópia deste objeto `Airport`.
    ///
    /// ### Fluxo de Operações
    /// 1. Tenta criar uma nova instância de `Airport` usando o construtor de cópia.
    /// 2. Se a cópia for bem-sucedida, retorna o clone.
    /// 3. Se ocorrer uma exceção, retorna `null`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo. Exceções internas são ignoradas.
    ///
    /// ### Exemplo
    /// ```java
    /// Airport airport1 = new Airport("São Paulo", "GRU");
    /// Airport airport2 = (Airport) airport1.clone();
    ///```
    ///
    /// @return Uma cópia deste objeto `Airport` ou `null` se a cópia falhar.
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone() {
        Airport clone = null;  // Variável que armazenará o clone.
        try {
            // Tenta criar uma nova instância de 'Airport' usando o construtor de cópia.
            clone = new Airport(this);
        } catch (Exception ignored) {
        } // Qualquer exceção é ignorada; o clone permanecerá 'null'.
        return clone;  // Retorna o clone, ou 'null' se não for possível.
    }

    /// ## equals
    ///
    /// Compara este aeroporto com outro objeto para verificar se são iguais.
    /// Dois aeroportos são considerados iguais se seus códigos e nomes forem iguais, ignorando maiúsculas e minúsculas.
    ///
    /// ### Parâmetros
    /// - **`obj`:** O objeto a ser comparado.
    ///
    /// ### Retorno
    /// - **`boolean`:** `true` se os aeroportos forem iguais, `false` caso contrário.
    ///
    /// ### Fluxo de Operações
    /// 1. Verifica se o objeto comparado é o próprio aeroporto.
    /// 2. Se o objeto for nulo ou de uma classe diferente, retorna `false`.
    /// 3. Realiza o cast para `Airport` e compara o código e o nome do aeroporto.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// Airport airport1 = new Airport("São Paulo", "GRU");
    /// Airport airport2 = new Airport("são paulo", "gru");
    /// boolean areEqual = airport1.equals(airport2); // true
    ///```
    ///
    /// @param obj O objeto a ser comparado.
    /// @return `true` se os aeroportos forem iguais,`false` caso contrário.
    @Override
    public boolean equals(Object obj) {
        // Verifica se o objeto comparado é o próprio aeroporto.
        if (this == obj) return true;

        // Se o objeto for nulo ou de uma classe diferente, retorna 'false'.
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Airport that = (Airport) obj;  // Realiza o cast para `Airport`.

        // Verifica se o código e o nome do aeroporto são iguais, ignorando maiúsculas/minúsculas.
        return this.code.equalsIgnoreCase(that.code) &&
               this.name.equalsIgnoreCase(that.name);
    }

    /// ## hashCode
    ///
    /// Calcula o código hash para este aeroporto, garantindo que objetos iguais gerem o mesmo código hash.
    /// O código hash é baseado no código do aeroporto e no nome do aeroporto.
    ///
    /// ### Retorno
    /// - **`int`:** O código hash gerado para este aeroporto.
    ///
    /// ### Fluxo de Operações
    /// 1. Define um fator multiplicador (`prime`) para gerar o hash.
    /// 2. Calcula o hash considerando o código do aeroporto e o nome do aeroporto.
    /// 3. Garante que o hash gerado seja sempre positivo.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// int hash1 = airport1.hashCode();
    /// int hash2 = airport2.hashCode();
    /// // hash1 == hash2 se airport1.equals(airport2) == true
    ///```
    ///
    /// @return O código hash gerado para este aeroporto.
    @Override
    public int hashCode() {
        final int prime = 31;  // Fator multiplicador para gerar o hash.
        int hash = 1;

        // Calcula o hash com base no código e nome do aeroporto.
        hash = prime * hash + ((this.code == null) ? 0 : this.code.toLowerCase().hashCode());
        hash = prime * hash + ((this.name == null) ? 0 : this.name.toLowerCase().hashCode());

        // Garante que o hash seja positivo.
        if (hash < 0) hash = -hash;

        return hash;  // Retorna o código hash.
    }

    /// ## toString
    ///
    /// Retorna uma representação textual do aeroporto, exibindo seu nome, código e a lista de voos.
    ///
    /// ### Retorno
    /// - **`String`:** Uma string que representa o aeroporto.
    ///
    /// ### Fluxo de Operações
    /// 1. Constrói uma string contendo o nome, código e a lista de voos do aeroporto no formato especificado.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// Airport airport = new Airport("São Paulo", "GRU");
    /// Flight flight = new Flight("GIG", 5678);
    /// airport.getFlights().addLast(flight);
    /// System.out.println(airport.toString());
    /// // Saída: Airport{name='São Paulo', code='GRU', flights=...}
    ///```
    ///
    /// @return Uma string que representa o aeroporto.
    @Override
    public String toString() {
        return "Airport{" +
               "name='" + name + '\'' +
               ", code='" + code + '\'' +
               ", flights=" + flights +
               '}';  // Retorna a string no formato "Airport{name='XXX', code='YYY', flights=...}"
    }
}
