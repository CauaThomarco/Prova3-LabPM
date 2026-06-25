package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Consulta {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private int codigo;
    private String nomePaciente;
    private Especialidade especialidade;
    private LocalDateTime dataConsulta;
    private double valorConsulta;

    public Consulta(int codigo, String nomePaciente, Especialidade especialidade,
                    LocalDateTime dataConsulta, double valorConsulta) {
        this.codigo = codigo;
        this.nomePaciente = nomePaciente;
        this.especialidade = especialidade;
        this.dataConsulta = dataConsulta;
        this.valorConsulta = valorConsulta;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Consulta)) return false;
        Consulta outra = (Consulta) obj;
        return this.codigo == outra.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo +
                " | Paciente: " + nomePaciente +
                " | Especialidade: " + especialidade +
                " | Data: " + dataConsulta.format(FORMATO_DATA) +
                " | Valor: R$ " + String.format("%.2f", valorConsulta);
    }
}