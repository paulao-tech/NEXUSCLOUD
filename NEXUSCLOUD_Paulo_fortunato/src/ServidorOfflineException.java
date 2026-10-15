// Unchecked: indica a tentativa de alocar em um servidor desligado. RA: 25142855-2
public class ServidorOfflineException extends RuntimeException {
    // Identifica a versão serializada da exceção, herdada de Throwable. RA: 25142855-2
    private static final long serialVersionUID = 1L;

    // Encaminha a mensagem ao construtor da exceção base para uso em getMessage(). RA: 25142855-2
    public ServidorOfflineException(String msg) {
        super(msg);
    }
}
