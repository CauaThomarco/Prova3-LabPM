package exception;

public class ConsultaNaoEncontradaException extends Exception {

    public ConsultaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public ConsultaNaoEncontradaException(int codigo) {
        super("Consulta com codigo " + codigo + " nao encontrada.");
    }
}