package view;

import functional.ExibidoresConsulta;
import model.Consulta;

import java.util.List;
import java.util.function.Consumer;

public class ConsultaView {

    private static final Consumer<Consulta> EXIBIDOR_DETALHADO =
            ExibidoresConsulta.exibirDetalhado();

    private ConsultaView() {
    }

    public static void exibirConsulta(Consulta consulta) {
        EXIBIDOR_DETALHADO.accept(consulta);
    }

    public static void exibirLista(List<Consulta> consultas, String titulo) {
        System.out.println("\n=== " + titulo + " ===");
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
            return;
        }
        consultas.forEach(EXIBIDOR_DETALHADO);
    }

    public static void exibirRelatorio(List<String> linhas) {
        System.out.println("\n========== RELATORIO RESUMIDO ==========");
        if (linhas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
        } else {
            linhas.forEach(System.out::println);
        }
        System.out.println("========================================");
    }

    public static void exibirMensagem(String mensagem) {
        System.out.println("\n>> " + mensagem);
    }

    public static void exibirErro(String mensagem) {
        System.out.println("\n[ERRO] " + mensagem);
    }
}