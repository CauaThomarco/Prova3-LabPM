package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtil {

    public static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private DataUtil() {
    }

    public static LocalDateTime parse(String texto) {
        return LocalDateTime.parse(texto, FORMATO);
    }

    public static String formatar(LocalDateTime data) {
        return data.format(FORMATO);
    }
}