package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EntradaUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private EntradaUtil() {
    }

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return SCANNER.nextLine().trim();
    }

    public static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero inteiro.");
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(SCANNER.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero decimal.");
            }
        }
    }

    public static LocalDateTime lerData(String mensagem) {
        while (true) {
            try {
                String entrada = lerTexto(mensagem);
                return DataUtil.parse(entrada);
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida. Use o formato dd/MM/yyyy HH:mm");
            }
        }
    }

    public static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        SCANNER.nextLine();
    }

    public static void fechar() {
        SCANNER.close();
    }
}