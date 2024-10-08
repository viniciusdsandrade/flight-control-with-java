import estruturas.LinkedList.Disordered.LinkedListDisordered;

import static estruturas.ShallowOrDeepCopy.ShallowOrDeepCopy.verifyAndCopy;

/// A classe 'Airport' representa um aeroporto, com um nome, um código de identificação
/// e uma lista de voos (flights) associados a ele. A classe também oferece mecanismos
/// para criar cópias profundas, verificar igualdade entre aeroportos e gerar representações textuais.
public class Airport implements Cloneable {

    /// Nome do aeroporto (ex: "Belo Horizonte").
    private String name;

    /// Código do aeroporto (ex: "CNF").
    private String code;

    /// Lista encadeada de voos associados ao aeroporto.
    private LinkedListDisordered<Flight> flights;

    /// Construtor da classe 'Airport'. Inicializa um aeroporto com um nome e um código,
    /// e uma lista vazia de voos associados a ele.
    ///
    /// @param name O nome do aeroporto.
    /// @param code O código do aeroporto.
    public Airport(String name, String code) {
        this.name = name;  // Atribui o nome do aeroporto.
        this.code = code;  // Atribui o código do aeroporto.
        this.flights = new LinkedListDisordered<>();  // Inicializa a lista de voos como vazia.
    }

    /// Retorna o nome do aeroporto.
    ///
    /// @return O nome do aeroporto.
    public String getName() {
        return name;  // Retorna o nome do aeroporto.
    }

    /// Retorna o código do aeroporto.
    ///
    /// @return O código do aeroporto.
    public String getCode() {
        return code;  // Retorna o código do aeroporto.
    }

    /// Define um novo nome para o aeroporto.
    ///
    /// @param name O novo nome do aeroporto.
    public void setName(String name) {
        this.name = name;  // Atualiza o nome do aeroporto.
    }

    /// Define um novo código para o aeroporto.
    ///
    /// @param code O novo código do aeroporto.
    public void setCode(String code) {
        this.code = code;  // Atualiza o código do aeroporto.
    }

    /// Retorna a lista encadeada de voos associados ao aeroporto.
    ///
    /// @return A lista de voos do aeroporto.
    public LinkedListDisordered<Flight> getFlights() {
        return flights;  // Retorna a lista de voos associados ao aeroporto.
    }

    /// Construtor de cópia. Cria um novo objeto 'Airport' a partir de outro, realizando uma cópia profunda
    /// dos atributos complexos, como a lista de voos (flights).
    ///
    /// @param other O aeroporto a ser copiado.
    @SuppressWarnings("unchecked")
    public Airport(Airport other) {
        // Verifica se o aeroporto fornecido é nulo, e lança uma exceção se for.
        if (other == null) throw new IllegalArgumentException("Airport não pode ser nulo");

        // Copia o nome do aeroporto usando a função 'verifyAndCopy', que garante uma cópia segura.
        this.name = (String) verifyAndCopy(other.name);

        // Copia o código do aeroporto da mesma maneira.
        this.code = (String) verifyAndCopy(other.code);

        // Realiza uma cópia profunda da lista de voos.
        this.flights = (LinkedListDisordered<Flight>) verifyAndCopy(other.flights);
    }

    /// Metodo que cria uma cópia deste objeto 'Airport'. Este metodo sobrescreve 'clone()' para
    /// criar uma cópia personalizada sem chamar 'super.clone()', conforme especificado.
    ///
    /// @return Uma cópia deste objeto 'Airport'.
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone() {
        Airport clone = null;  // Variável que armazenará o clone.
        try {
            // Tenta criar uma nova instância de 'Airport' usando o construtor de cópia.
            clone = new Airport(this);
        } catch (Exception ignored) {
            // Qualquer exceção é ignorada; o clone permanecerá 'null' se ocorrer.
        }
        return clone;  // Retorna o clone, ou 'null' se não for possível.
    }

    /// Compara este aeroporto com outro objeto para verificar se são iguais.
    /// Dois aeroportos são considerados iguais se seus códigos e nomes forem iguais, ignorando maiúsculas e minúsculas.
    ///
    /// @param obj O objeto a ser comparado.
    /// @return 'true' se os aeroportos forem iguais, 'false' caso contrário.
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

    /// Calcula o código hash para este aeroporto, garantindo que objetos iguais gerem o mesmo código hash.
    /// O código hash é baseado no código do aeroporto e no nome do aeroporto.
    ///
    /// @return O código hash gerado para este aeroporto.
    @Override
    public int hashCode() {
        final int prime = 31;  // Fator multiplicador para gerar o hash.
        int hash = 1;

        // Calcula o hash com base no código e nome do aeroporto.
        hash *= prime + ((this.code == null) ? 0 : this.code.hashCode());
        hash *= prime + ((this.name == null) ? 0 : this.name.hashCode());

        // Garante que o hash seja positivo.
        if (hash < 0) hash = -hash;

        return hash;  // Retorna o código hash.
    }

    /// Retorna uma representação textual do aeroporto, exibindo seu nome, código e a lista de voos.
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
