package model;

public enum Especialidade {
    CARDIOLOGIA,
    PEDIATRIA,
    ORTOPEDIA,
    DERMATOLOGIA;

    public static Especialidade fromOpcao(int opcao) {
        Especialidade[] valores = values();
        if (opcao < 1 || opcao > valores.length) {
            throw new IllegalArgumentException("Especialidade invalida.");
        }
        return valores[opcao - 1];
    }

    public static void exibirOpcoes() {
        Especialidade[] valores = values();
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + " - " + valores[i]);
        }
    }
}