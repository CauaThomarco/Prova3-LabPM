package exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HorarioIndisponivelException extends Exception {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public HorarioIndisponivelException(String mensagem) {
        super(mensagem);
    }

    public HorarioIndisponivelException(LocalDateTime data) {
        super("Ja existe uma consulta marcada para " + data.format(FORMATO_DATA));
    }
}