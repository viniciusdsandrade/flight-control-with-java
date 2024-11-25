import static estruturas.ShallowOrDeepCopy.ShallowOrDeepCopy.verifyAndCopy;

/// # Flight
///
/// A classe `Flight` representa um voo com um número de voo e um código de destino.
/// Esta classe permite criar, copiar e comparar objetos de voos, e é capaz de verificar
/// a igualdade entre voos e gerar uma representação textual.
///
/// ## Funcionalidades
/// - **Criação de Voos:** Permite a criação de novos voos com código de destino e número únicos.
/// - **Cópia de Voos (`clone` e construtor de cópia):** Permite criar cópias de voos existentes.
/// - **Comparação de Voos (`equals`, `hashCode`):** Permite comparar voos para verificar igualdade.
/// - **Representação Textual (`toString`):** Fornece uma representação textual legível do voo.
///
/// ## Estrutura Interna
/// - **destinationCode:** Código do aeroporto de destino para o qual o voo está indo.
/// - **flightNumber:** Número que identifica de forma única o voo.
///
/// ## Uso
/// Crie instâncias de `Flight` utilizando o construtor padrão ou o construtor de cópia, e utilize os metodos disponíveis para gerenciar e comparar voos.
///
/// ### Exemplo
/// ```java
/// Flight flight1 = new Flight("GIG", 1234);
/// Flight flight2 = (Flight) flight1.clone();
/// boolean areEqual = flight1.equals(flight2); // true
/// System.out.println(flight1); // Flight{destinationCode='GIG', flightNumber=1234}
///```
public class Flight implements Cloneable {

    /// ### Campos
    ///
    /// - **`destinationCode`:** O código do aeroporto de destino para o qual o voo está indo.
    /// - **`flightNumber`:** O número que identifica de forma única o voo.
    private final String destinationCode;
    private final int flightNumber;

    /// ## Flight
    ///
    /// Construtor da classe `Flight`. Inicializa um novo voo com o código de destino
    /// e o número do voo fornecidos.
    ///
    /// ### Parâmetros
    /// - **`destinationCode`:** O código do aeroporto de destino.
    /// - **`flightNumber`:** O número do voo.
    ///
    /// ### Fluxo de Operações
    /// 1. Atribui o código de destino ao campo `destinationCode`.
    /// 2. Atribui o número do voo ao campo `flightNumber`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste construtor.
    ///
    /// ### Exemplo
    /// ```java
    /// Flight flight = new Flight("GIG", 1234);
    ///```
    ///
    /// @param destinationCode O código do aeroporto de destino.
    /// @param flightNumber    O número do voo.
    public Flight(String destinationCode, int flightNumber) {
        this.destinationCode = destinationCode;  // Atribui o código de destino
        this.flightNumber = flightNumber;        // Atribui o número do voo
    }

    /// ## getDestinationCode
    ///
    /// Retorna o código do aeroporto de destino para o qual o voo está indo.
    ///
    /// ### Retorno
    /// - **`String`:** O código do aeroporto de destino.
    ///
    /// ### Fluxo de Operações
    /// 1. Retorna o valor do campo `destinationCode`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// String code = flight.getDestinationCode();
    ///```
    ///
    /// @return O código do aeroporto de destino.
    public String getDestinationCode() {
        return destinationCode;  // Retorna o código de destino
    }

    /// ## getFlightNumber
    ///
    /// Retorna o número que identifica o voo.
    ///
    /// ### Retorno
    /// - **`int`:** O número do voo.
    ///
    /// ### Fluxo de Operações
    /// 1. Retorna o valor do campo `flightNumber`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// int number = flight.getFlightNumber();
    ///```
    ///
    /// @return O número do voo.
    public int getFlightNumber() {
        return flightNumber;  // Retorna o número do voo
    }

    /// ## Flight (Construtor de Cópia)
    ///
    /// Construtor de cópia. Cria um novo objeto `Flight` a partir de outro, realizando uma
    /// cópia profunda (deep copy) dos atributos complexos, quando necessário.
    ///
    /// ### Parâmetros
    /// - **`other`:** O objeto `Flight` a ser copiado.
    ///
    /// ### Fluxo de Operações
    /// 1. Verifica se o objeto `other` é nulo e lança uma exceção se for.
    /// 2. Copia o código do destino utilizando a função `verifyAndCopy`, garantindo uma cópia segura.
    /// 3. Copia o número do voo diretamente, pois `int` é um tipo primitivo e não precisa de cópia profunda.
    ///
    /// ### Exceções
    /// - **`IllegalArgumentException`:** Lança exceção se o objeto `other` for nulo.
    ///
    /// ### Exemplo
    /// ```java
    /// Flight original = new Flight("GIG", 1234);
    /// Flight copy = new Flight(original);
    ///```
    ///
    /// @param other O objeto `Flight` a ser copiado.
    /// @throws IllegalArgumentException Se o objeto `other` for nulo.
    public Flight(Flight other) {
        // Verifica se o voo passado como argumento é nulo e lança uma exceção caso seja.
        if (other == null) throw new IllegalArgumentException("Flight não pode ser nulo");

        // Copia o código do destino usando a função 'verifyAndCopy', garantindo uma cópia segura.
        this.destinationCode = (String) verifyAndCopy(other.destinationCode);

        // Copia o número do voo diretamente, pois 'int' é um tipo primitivo e não precisa de cópia profunda.
        this.flightNumber = other.flightNumber;
    }

    /// ## clone
    ///
    /// metodo que cria uma cópia do objeto `Flight`. Este metodo sobrescreve `clone()` para
    /// criar uma cópia de forma personalizada sem chamar `super.clone()`, conforme especificado.
    ///
    /// ### Retorno
    /// - **`Object`:** Uma cópia deste objeto `Flight`.
    ///
    /// ### Fluxo de Operações
    /// 1. Tenta criar uma nova instância de `Flight` usando o construtor de cópia.
    /// 2. Se a cópia for bem-sucedida, retorna o clone.
    /// 3. Se ocorrer uma exceção, retorna `null`.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo. Exceções internas são ignoradas.
    ///
    /// ### Exemplo
    /// ```java
    /// Flight flight1 = new Flight("GIG", 1234);
    /// Flight flight2 = (Flight) flight1.clone();
    ///```
    ///
    /// @return Uma cópia deste objeto `Flight` ou `null` se a cópia falhar.
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")  // Suprime o aviso por não chamar `super.clone()`
    public Object clone() {
        Flight clone = null;  // Variável que irá armazenar o clone
        try {
            // Tenta criar uma nova instância de 'Flight' usando o construtor de cópia
            clone = new Flight(this);
        } catch (Exception ignored) {
            // Qualquer exceção é ignorada; o clone permanecerá 'null'
        }
        return clone;  // Retorna o clone ou `null` se falhar
    }

    /// ## equals
    ///
    /// Compara este voo com outro objeto para verificar se são iguais. Dois voos são considerados
    /// iguais se seus números de voo forem iguais e os códigos de destino forem iguais (ignorando
    /// maiúsculas e minúsculas).
    ///
    /// ### Parâmetros
    /// - **`obj`:** O objeto a ser comparado.
    ///
    /// ### Retorno
    /// - **`boolean`:** `true` se os voos forem iguais, `false` caso contrário.
    ///
    /// ### Fluxo de Operações
    /// 1. Verifica se o objeto comparado é o próprio voo.
    /// 2. Se o objeto for nulo ou de uma classe diferente, retorna `false`.
    /// 3. Realiza o cast para `Flight` e compara o número do voo e o código de destino.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// Flight flight1 = new Flight("GIG", 1234);
    /// Flight flight2 = new Flight("gig", 1234);
    /// boolean areEqual = flight1.equals(flight2); // true
    ///```
    ///
    /// @param obj O objeto a ser comparado.
    /// @return `true` se os voos forem iguais,`false` caso contrário.
    @Override
    public boolean equals(Object obj) {
        // Verifica se o objeto comparado é o próprio voo
        if (this == obj) return true;

        // Se o objeto for nulo ou de uma classe diferente, retorna 'false'
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Flight that = (Flight) obj;  // Realiza o cast para `Flight`

        // Verifica se o número do voo e o código de destino são iguais
        return flightNumber == that.flightNumber &&
               destinationCode.equalsIgnoreCase(that.destinationCode);
    }

    /// ## hashCode
    ///
    /// Calcula o código hash para este voo, garantindo que objetos iguais gerem o mesmo código
    /// hash. O código hash é calculado com base no código de destino e no número do voo.
    ///
    /// ### Retorno
    /// - **`int`:** O código hash gerado para este voo.
    ///
    /// ### Fluxo de Operações
    /// 1. Define um fator multiplicador (`prime`) para gerar o hash.
    /// 2. Calcula o hash considerando o código do destino e o número do voo.
    /// 3. Garante que o hash gerado seja sempre positivo.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// int hash1 = flight1.hashCode();
    /// int hash2 = flight2.hashCode();
    /// // hash1 == hash2 se flight1.equals(flight2) == true
    ///```
    ///
    /// @return O código hash gerado para este voo.
    @Override
    public int hashCode() {
        final int prime = 31;  // Fator multiplicador para gerar o hash
        int hash = 1;

        // Calcula o hash considerando o código do destino e o número do voo
        hash = prime * hash + ((this.destinationCode == null) ? 0 : this.destinationCode.toLowerCase().hashCode());
        hash = prime * hash + flightNumber;

        // Garante que o hash gerado seja sempre positivo
        if (hash < 0) hash = -hash;

        return hash;  // Retorna o código hash gerado
    }

    /// ## toString
    ///
    /// Retorna uma representação textual do voo, exibindo o código de destino e o número do voo
    /// em um formato legível.
    ///
    /// ### Retorno
    /// - **`String`:** Uma string que representa o voo.
    ///
    /// ### Fluxo de Operações
    /// 1. Constrói uma string contendo o código de destino e o número do voo no formato especificado.
    ///
    /// ### Exceções
    /// Nenhuma exceção lançada diretamente neste metodo.
    ///
    /// ### Exemplo
    /// ```java
    /// Flight flight = new Flight("GIG", 1234);
    /// System.out.println(flight.toString());
    /// // Saída: Flight{destinationCode='GIG', flightNumber=1234}
    ///```
    ///
    /// @return Uma string que representa o voo.
    @Override
    public String toString() {
        return "Flight{" +
               "destinationCode='" + destinationCode + '\'' +
               ", flightNumber=" + flightNumber +
               '}';  // Retorna a string no formato "Flight{destinationCode='XXX', flightNumber=YYY}"
    }
}
