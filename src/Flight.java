import static estruturas.ShallowOrDeepCopy.ShallowOrDeepCopy.verifyAndCopy;

/// A classe 'Flight' representa um voo com um número de voo e um código de destino.
/// Esta classe permite criar, copiar e comparar objetos de voos, e é capaz de verificar
/// a igualdade entre voos e gerar uma representação textual.
public class Flight implements Cloneable {

    /// O código do aeroporto de destino para o qual o voo está indo.
    private String destinationCode;

    /// O número que identifica de forma única o voo.
    private int flightNumber;

    /// Construtor da classe 'Flight'. Inicializa um novo voo com o código de destino
    /// e o número do voo fornecidos.
    ///
    /// @param destinationCode O código do aeroporto de destino.
    /// @param flightNumber O número do voo.
    public Flight(String destinationCode, int flightNumber) {
        this.destinationCode = destinationCode;  // Atribui o código de destino
        this.flightNumber = flightNumber;  // Atribui o número do voo
    }

    /// Retorna o código do aeroporto de destino para o qual o voo está indo.
    ///
    /// @return O código do aeroporto de destino.
    public String getDestinationCode() {
        return destinationCode;  // Retorna o código de destino
    }

    /// Retorna o número que identifica o voo.
    ///
    /// @return O número do voo.
    public int getFlightNumber() {
        return flightNumber;  // Retorna o número do voo
    }

    /// Construtor de cópia. Cria um novo objeto 'Flight' a partir de outro, realizando uma
    /// cópia profunda (deep copy) dos atributos complexos, quando necessário.
    ///
    /// @param other O objeto 'Flight' a ser copiado.
    public Flight(Flight other) {
        // Verifica se o voo passado como argumento é nulo e lança uma exceção caso seja.
        if (other == null) throw new IllegalArgumentException("Flight não pode ser nulo");

        // Copia o código do destino usando a função 'verifyAndCopy', garantindo uma cópia segura.
        this.destinationCode = (String) verifyAndCopy(other.destinationCode);

        // Copia o número do voo diretamente, pois 'int' é um tipo primitivo e não precisa de cópia profunda.
        this.flightNumber = other.flightNumber;
    }

    /// Metodo que cria uma cópia do objeto 'Flight'. Este metodo sobrescreve 'clone()' para
    /// criar uma cópia de forma personalizada sem chamar 'super.clone()', conforme especificado.
    ///
    /// @return Uma cópia deste objeto 'Flight'.
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

    /// Compara este voo com outro objeto para verificar se são iguais. Dois voos são considerados
    /// iguais se seus números de voo forem iguais e os códigos de destino forem iguais (ignorando
    /// maiúsculas e minúsculas).
    ///
    /// @param obj O objeto a ser comparado.
    /// @return 'true' se os voos forem iguais, 'false' caso contrário.
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

    /// Calcula o código hash para este voo, garantindo que objetos iguais gerem o mesmo código
    /// hash. O código hash é calculado com base no código de destino e no número do voo.
    ///
    /// @return O código hash gerado para este voo.
    @Override
    public int hashCode() {
        final int prime = 31;  // Fator multiplicador para gerar o hash
        int hash = 1;

        // Calcula o hash considerando o código do destino e o número do voo
        hash *= prime + ((this.destinationCode == null) ? 0 : this.destinationCode.hashCode());
        hash *= prime + flightNumber;

        // Garante que o hash gerado seja sempre positivo
        if (hash < 0) hash = -hash;

        return hash;  // Retorna o código hash gerado
    }

    /// Retorna uma representação textual do voo, exibindo o código de destino e o número do voo
    /// em um formato legível.
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
