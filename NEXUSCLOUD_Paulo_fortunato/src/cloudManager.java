// Map relaciona clientes a conjuntos; HashMap e HashSet usam hashes para localizar os objetos. RA: 25142855-2
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

// Centraliza o cadastro de clientes, os servidores associados e o cálculo do faturamento. RA: 25142855-2
public class cloudManager {
    // Cada cliente é chave de um conjunto de servidores únicos, sem ordem garantida. RA: 25142855-2
    private final Map<cliente, Set<Servidor>> bancoDeDados;

    // Inicializa o banco em memória usando a implementação HashMap solicitada. RA: 25142855-2
    public cloudManager() {
        bancoDeDados = new HashMap<>();
    }

    // Cadastra um conjunto vazio; não apaga servidores se o mesmo CPF já estiver registrado. RA: 25142855-2
    public void registrarCliente(cliente c) {
        // Rejeita referências nulas com uma mensagem explícita antes de alterar o cadastro. RA: 25142855-2
        Objects.requireNonNull(c, "O cliente não pode ser nulo.");
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    // Associa o servidor ao cliente previamente registrado; o HashSet impede repetição por IP. RA: 25142855-2
    public void provisionarServidor(cliente c, Servidor s) {
        // Valida o servidor e o cadastro antes da associação, evitando NullPointerException acidental. RA: 25142855-2
        Objects.requireNonNull(s, "O servidor não pode ser nulo.");
        obterServidores(c).add(s);
    }

    // Filtra somente servidores ligados e soma seus preços por hora com Streams, sem laços for. RA: 25142855-2
    public double calcularFaturaAtiva(cliente c) {
        return obterServidores(c).stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
    // Centraliza a consulta; exige cadastro prévio sem criar clientes implicitamente. RA: 25142855-2
    private Set<Servidor> obterServidores(cliente c) {
        Objects.requireNonNull(c, "O cliente não pode ser nulo.");
        Set<Servidor> servidores = bancoDeDados.get(c);

        // Um cliente ausente recebe um erro explicativo, em vez de acesso a uma coleção nula. RA: 25142855-2
        if (servidores == null) {
            throw new IllegalArgumentException("Cliente não cadastrado: " + c.getCpf());
        }
        return servidores;
    }
}
