package functional;

import model.Consulta;

import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class ExibidoresConsulta {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private ExibidoresConsulta() {
    }

    public static Consumer<Consulta> exibirDetalhado() {
        return consulta -> {
            System.out.println("---------------------------------------------");
            System.out.println("Codigo:        " + consulta.getCodigo());
            System.out.println("Paciente:      " + consulta.getNomePaciente());
            System.out.println("Especialidade: " + consulta.getEspecialidade());
            System.out.println("Data/Hora:     " + consulta.getDataConsulta().format(FORMATO_DATA));
            System.out.println("Valor:         R$ " + String.format("%.2f", consulta.getValorConsulta()));
            System.out.println("---------------------------------------------");
        };
    }

    public static Consumer<Consulta> exibirLinha() {
        return consulta -> System.out.println(consulta.toString());
    }
}