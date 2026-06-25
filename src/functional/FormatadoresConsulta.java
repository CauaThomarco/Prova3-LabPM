package functional;

import model.Consulta;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class FormatadoresConsulta {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private FormatadoresConsulta() {
    }

    public static Function<Consulta, String> resumo() {
        return consulta -> consulta.getNomePaciente()
                + " - " + consulta.getEspecialidade()
                + " - " + consulta.getDataConsulta().format(FORMATO_DATA);
    }

    public static Function<Consulta, String> relatorioItem() {
        return consulta -> "* " + consulta.getNomePaciente()
                + " | " + consulta.getEspecialidade()
                + " | " + consulta.getDataConsulta().format(FORMATO_DATA)
                + " | R$ " + String.format("%.2f", consulta.getValorConsulta());
    }
}