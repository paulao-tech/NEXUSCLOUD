// Checked: exige tratamento ou declaração na assinatura de quem chama. RA: 25142855-2
public class CapacidadeExcedidaException extends Exception {
    // Identifica a versão serializada da exceção, herdada de Throwable. RA: 25142855-2
    private static final long serialVersionUID = 1L;

    // Encaminha a mensagem ao construtor da exceção base para uso em getMessage(). RA: 25142855-2
    public CapacidadeExcedidaException(String msg) {
        super(msg);
    }
}
