// Coleções armazenam os containers; Objects implementa a identidade pelo IP. RA: 25142855-2
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Representa um servidor, sua capacidade, seu estado e suas aplicações. RA: 25142855-2
public class Servidor {
    // Mantém os dados encapsulados e a lista pronta desde a criação, na ordem de inserção. RA: 25142855-2
    // A identidade é imutável para preservar a consistência de equals e hashCode nas coleções. RA: 25142855-2
    private final String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;
    private List<Container> containers = new ArrayList<>();

    // Recebe IP, RAM e preço na ordem do UML; todo servidor nasce desligado. RA: 25142855-2
    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false;
    }

    // Consulta o estado usado no filtro de faturamento. RA: 25142855-2
    public boolean isStatusLigado() {
        return statusLigado;
    }

    // Retorna o preço por hora usado no cálculo da fatura ativa. RA: 25142855-2
    public double getPrecoPorHora() {
        return precoPorHora;
    }

    // Altera o estado para permitir alocações e incluir o servidor no faturamento. RA: 25142855-2
    public void ligar() {
        statusLigado = true;
    }

    // Desliga o servidor, impedindo novas alocações e retirando-o da fatura ativa. RA: 25142855-2
    public void desligar() {
        statusLigado = false;
    }

    // Valida as regras antes de alterar a lista; declara a exceção checked exigida no UML. RA: 25142855-2
    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        // Fail-fast: rejeita imediatamente a alocação se o servidor estiver desligado. RA: 25142855-2
        if (!statusLigado) {
            throw new ServidorOfflineException(
                "Erro: Não é possível alocar containers em um servidor desligado.");
        }

        // Soma o consumo atual ao novo; só rejeita se ultrapassar a capacidade total. RA: 25142855-2
        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException(
                "Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        }

        // Adiciona apenas após todas as validações, preservando a lista em caso de falha. RA: 25142855-2
        containers.add(c);
    }

    // Converte cada container em seu consumo de RAM e soma com Streams; lista vazia resulta em zero. RA: 25142855-2
    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    // O IP define a identidade; estado, preço, RAM e aplicações não participam da comparação. RA: 25142855-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Servidor outro = (Servidor) obj;
        return Objects.equals(ip, outro.ip);
    }

    // Mantém o contrato com equals e permite que HashSet rejeite IPs duplicados. RA: 25142855-2
    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}