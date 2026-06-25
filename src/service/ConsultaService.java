package service;

import exception.ConsultaNaoEncontradaException;
import exception.HorarioIndisponivelException;
import functional.ComparadoresConsulta;
import functional.FiltrosConsulta;
import functional.FormatadoresConsulta;
import model.Consulta;
import model.Especialidade;
import repository.Repositorio;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultaService {

    private final Repositorio<Consulta> repositorio;

    public ConsultaService(Repositorio<Consulta> repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Consulta consulta) throws HorarioIndisponivelException {
        validarHorarioDisponivel(consulta.getDataConsulta(), consulta.getCodigo());
        repositorio.adicionar(consulta);
    }

    public Consulta buscarPorCodigo(int codigo) throws ConsultaNaoEncontradaException {
        Consulta encontrada = repositorio.buscar(FiltrosConsulta.porCodigo(codigo));
        if (encontrada == null) {
            throw new ConsultaNaoEncontradaException(codigo);
        }
        return encontrada;
    }

    public void remover(int codigo) throws ConsultaNaoEncontradaException {
        Consulta consulta = buscarPorCodigo(codigo);
        repositorio.remover(consulta);
    }

    public void cancelar(int codigo) throws ConsultaNaoEncontradaException {
        remover(codigo);
    }

    public void reagendar(int codigo, LocalDateTime novaData)
            throws ConsultaNaoEncontradaException, HorarioIndisponivelException {
        Consulta consulta = buscarPorCodigo(codigo);
        validarHorarioDisponivel(novaData, codigo);
        consulta.setDataConsulta(novaData);
    }

    public List<Consulta> filtrarPorEspecialidade(Especialidade especialidade) {
        return repositorio.filtrar(FiltrosConsulta.porEspecialidade(especialidade));
    }

    public List<Consulta> filtrarPorValorMinimo(double valor) {
        return repositorio.filtrar(FiltrosConsulta.porValorMinimo(valor));
    }

    public List<Consulta> ordenarPorData() {
        return repositorio.ordenar(ComparadoresConsulta.porData());
    }

    public List<Consulta> ordenarPorNome() {
        return repositorio.ordenar(ComparadoresConsulta.porNomePaciente());
    }

    public List<Consulta> listarTodas() {
        return repositorio.listar();
    }

    public List<String> gerarRelatorioResumido() {
        return repositorio.transformar(FormatadoresConsulta.relatorioItem());
    }

    public boolean estaVazio() {
        return repositorio.estaVazio();
    }

    public int quantidade() {
        return repositorio.tamanho();
    }

    private void validarHorarioDisponivel(LocalDateTime data, int codigoIgnorado)
            throws HorarioIndisponivelException {
        Consulta existente = repositorio.buscar(consulta ->
                consulta.getDataConsulta().equals(data)
                        && consulta.getCodigo() != codigoIgnorado);
        if (existente != null) {
            throw new HorarioIndisponivelException(data);
        }
    }
}