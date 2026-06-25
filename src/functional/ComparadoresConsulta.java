package functional;

import model.Consulta;

import java.util.Comparator;

public class ComparadoresConsulta {

    private ComparadoresConsulta() {
    }

    public static Comparator<Consulta> porNomePaciente() {
        return Comparator.comparing(Consulta::getNomePaciente, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Consulta> porData() {
        return Comparator.comparing(Consulta::getDataConsulta);
    }
}