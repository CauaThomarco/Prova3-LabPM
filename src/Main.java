import exception.HorarioIndisponivelException;
import model.Consulta;
import model.Especialidade;
import repository.Repositorio;
import service.ConsultaService;
import view.Menu;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Repositorio<Consulta> repositorio = new Repositorio<>();
        ConsultaService service = new ConsultaService(repositorio);

        popularConsultasIniciais(service);

        Menu menu = new Menu(service);
        menu.iniciar();
    }

    private static void popularConsultasIniciais(ConsultaService service) {
        Consulta[] iniciais = {
                new Consulta(1, "Joao Silva", Especialidade.CARDIOLOGIA,
                        LocalDateTime.of(2026, 6, 15, 14, 0), 250.00),
                new Consulta(2, "Maria Souza", Especialidade.PEDIATRIA,
                        LocalDateTime.of(2026, 6, 16, 9, 30), 180.00),
                new Consulta(3, "Carlos Oliveira", Especialidade.ORTOPEDIA,
                        LocalDateTime.of(2026, 6, 17, 11, 0), 300.00),
                new Consulta(4, "Ana Pereira", Especialidade.DERMATOLOGIA,
                        LocalDateTime.of(2026, 6, 18, 15, 45), 220.50),
                new Consulta(5, "Pedro Santos", Especialidade.CARDIOLOGIA,
                        LocalDateTime.of(2026, 6, 19, 8, 0), 270.00)
        };

        for (Consulta consulta : iniciais) {
            try {
                service.cadastrar(consulta);
            } catch (HorarioIndisponivelException e) {
                System.out.println("[Aviso] " + e.getMessage());
            }
        }
    }
}