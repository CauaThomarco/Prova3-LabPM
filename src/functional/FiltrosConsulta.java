package functional;

import model.Consulta;
import model.Especialidade;

import java.util.function.Predicate;

public class FiltrosConsulta {

    private FiltrosConsulta() {
    }

    public static Predicate<Consulta> porEspecialidade(Especialidade especialidade) {
        return consulta -> consulta.getEspecialidade() == especialidade;
    }

    public static Predicate<Consulta> porValorMinimo(double valor) {
        return consulta -> consulta.getValorConsulta() > valor;
    }

    public static Predicate<Consulta> porCodigo(int codigo) {
        return consulta -> consulta.getCodigo() == codigo;
    }
}