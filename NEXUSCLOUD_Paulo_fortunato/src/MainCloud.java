// Locale fixa o ponto decimal para apresentar exatamente 5.50 no teste final. RA: 25142855-2
import java.util.Locale;

// Executa o roteiro de testes na ordem indicada no enunciado. RA: 25142855-2
public class MainCloud {
    // Declara a exceção checked para as alocações dos passos 6 e 8; não captura a unchecked do passo 6. RA: 25142855-2
    public static void main(String[] args) throws CapacidadeExcedidaException {
        // Passo 1: cria o gerenciador e seu banco de dados em memória. RA: 25142855-2
        cloudManager manager = new cloudManager();

        // Passo 2: cria o cliente usando o CPF e o nome exemplificados no enunciado. RA: 25142855-2
        cliente cliente = new cliente("111.222.333-44", "Wayne Enterprises");

        // Passo 3: cria o servidor desligado, com 16 GB de RAM e preço de 5.50 por hora. RA: 25142855-2
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        // Passo 4: registra o cliente antes de associar o servidor ao conjunto dele. RA: 25142855-2
        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        // Passo 5: cria containers de 10 e 8 GB; nomes de aplicações são exemplos para preencher nomeApp do UML. RA: 25142855-2
        Container container1 = new Container("C-01", "Aplicacao 1", 10.0);
        Container container2 = new Container("C-02", "Aplicacao 2", 8.0);

        // Passo 6: descomente a próxima linha e execute para observar ServidorOfflineException sem try-catch. RA: 25142855-2
        // servidor.alocarContainer(container1); // RA: 25142855-2

        // Passo 7: após testar a falha, mantenha a linha anterior comentada e execute novamente; liga o servidor. RA: 25142855-2
        servidor.ligar();

        // Passo 8: aloca o primeiro container; 10 GB cabem nos 16 GB do servidor ligado. RA: 25142855-2
        servidor.alocarContainer(container1);

        // Passo 9: tenta atingir 18 GB; captura a exceção checked e mostra só sua mensagem, permitindo continuar. RA: 25142855-2
        try {
            servidor.alocarContainer(container2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        // Passo 10: o único servidor está ligado; imprime sua tarifa com duas casas decimais. RA: 25142855-2
        System.out.printf(Locale.US, "%.2f%n", manager.calcularFaturaAtiva(cliente));
    }
}
