package view;

import exception.ConsultaNaoEncontradaException;
import exception.HorarioIndisponivelException;
import model.Consulta;
import model.Especialidade;
import service.ConsultaService;
import util.EntradaUtil;

import java.time.LocalDateTime;
import java.util.List;

public class Menu {

    private final ConsultaService service;

    public Menu(ConsultaService service) {
        this.service = service;
    }

    public void iniciar() {
        int opcao;
        do {
            exibirOpcoes();
            opcao = EntradaUtil.lerInteiro("Escolha uma opcao: ");
            executar(opcao);
        } while (opcao != 0);
        ConsultaView.exibirMensagem("Sistema encerrado. Ate logo!");
        EntradaUtil.fechar();
    }

    private void exibirOpcoes() {
        System.out.println("\n========= CLINICA MEDICA =========");
        System.out.println("1 - Cadastrar consulta");
        System.out.println("2 - Remover consulta");
        System.out.println("3 - Filtrar consultas por especialidade");
        System.out.println("4 - Filtrar consultas com valor acima de X");
        System.out.println("5 - Reagendar consulta");
        System.out.println("6 - Ordenar consultas por data");
        System.out.println("7 - Gerar relatorio resumido");
        System.out.println("8 - Cancelar consulta");
        System.out.println("9 - Exibir todas as consultas");
        System.out.println("0 - Sair");
        System.out.println("==================================");
    }

    private void executar(int opcao) {
        switch (opcao) {
            case 1: cadastrar(); break;
            case 2: remover(); break;
            case 3: filtrarPorEspecialidade(); break;
            case 4: filtrarPorValor(); break;
            case 5: reagendar(); break;
            case 6: ordenarPorData(); break;
            case 7: gerarRelatorio(); break;
            case 8: cancelar(); break;
            case 9: exibirTodas(); break;
            case 0: break;
            default: ConsultaView.exibirErro("Opcao invalida.");
        }
    }

    private void cadastrar() {
        try {
            int codigo = EntradaUtil.lerInteiro("Codigo: ");
            String nome = EntradaUtil.lerTexto("Nome do paciente: ");
            Especialidade.exibirOpcoes();
            int op = EntradaUtil.lerInteiro("Especialidade: ");
            Especialidade especialidade = Especialidade.fromOpcao(op);
            LocalDateTime data = EntradaUtil.lerData("Data (dd/MM/yyyy HH:mm): ");
            double valor = EntradaUtil.lerDouble("Valor R$: ");

            Consulta consulta = new Consulta(codigo, nome, especialidade, data, valor);
            service.cadastrar(consulta);
            ConsultaView.exibirMensagem("Consulta cadastrada com sucesso!");
        } catch (HorarioIndisponivelException e) {
            ConsultaView.exibirErro(e.getMessage());
        } catch (IllegalArgumentException e) {
            ConsultaView.exibirErro(e.getMessage());
        }
    }

    private void remover() {
        try {
            int codigo = EntradaUtil.lerInteiro("Codigo da consulta: ");
            service.remover(codigo);
            ConsultaView.exibirMensagem("Consulta removida com sucesso!");
        } catch (ConsultaNaoEncontradaException e) {
            ConsultaView.exibirErro(e.getMessage());
        }
    }

    private void filtrarPorEspecialidade() {
        try {
            Especialidade.exibirOpcoes();
            int op = EntradaUtil.lerInteiro("Especialidade: ");
            Especialidade especialidade = Especialidade.fromOpcao(op);
            List<Consulta> resultado = service.filtrarPorEspecialidade(especialidade);
            ConsultaView.exibirLista(resultado, "Consultas - " + especialidade);
        } catch (IllegalArgumentException e) {
            ConsultaView.exibirErro(e.getMessage());
        }
    }

    private void filtrarPorValor() {
        double valor = EntradaUtil.lerDouble("Valor minimo R$: ");
        List<Consulta> resultado = service.filtrarPorValorMinimo(valor);
        ConsultaView.exibirLista(resultado, "Consultas com valor acima de R$ " + valor);
    }

    private void reagendar() {
        try {
            int codigo = EntradaUtil.lerInteiro("Codigo da consulta: ");
            LocalDateTime novaData = EntradaUtil.lerData("Nova data (dd/MM/yyyy HH:mm): ");
            service.reagendar(codigo, novaData);
            ConsultaView.exibirMensagem("Consulta reagendada com sucesso!");
        } catch (ConsultaNaoEncontradaException | HorarioIndisponivelException e) {
            ConsultaView.exibirErro(e.getMessage());
        }
    }

    private void ordenarPorData() {
        List<Consulta> ordenadas = service.ordenarPorData();
        ConsultaView.exibirLista(ordenadas, "Consultas ordenadas por data");
    }

    private void gerarRelatorio() {
        List<String> relatorio = service.gerarRelatorioResumido();
        ConsultaView.exibirRelatorio(relatorio);
    }

    private void cancelar() {
        try {
            int codigo = EntradaUtil.lerInteiro("Codigo da consulta a cancelar: ");
            service.cancelar(codigo);
            ConsultaView.exibirMensagem("Consulta cancelada com sucesso!");
        } catch (ConsultaNaoEncontradaException e) {
            ConsultaView.exibirErro(e.getMessage());
        }
    }

    private void exibirTodas() {
        List<Consulta> todas = service.listarTodas();
        ConsultaView.exibirLista(todas, "Todas as consultas cadastradas");
    }
}