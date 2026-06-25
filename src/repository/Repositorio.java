package repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Repositorio<T> {

    private final List<T> elementos;

    public Repositorio() {
        this.elementos = new ArrayList<>();
    }

    public void adicionar(T elemento) {
        elementos.add(elemento);
    }

    public boolean remover(T elemento) {
        return elementos.remove(elemento);
    }

    public List<T> listar() {
        return new ArrayList<>(elementos);
    }

    public T buscar(Predicate<T> filtro) {
        for (T elemento : elementos) {
            if (filtro.test(elemento)) {
                return elemento;
            }
        }
        return null;
    }

    public List<T> filtrar(Predicate<T> filtro) {
        List<T> resultado = new ArrayList<>();
        for (T elemento : elementos) {
            if (filtro.test(elemento)) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }

    public List<T> ordenar(Comparator<T> comparador) {
        List<T> resultado = new ArrayList<>(elementos);
        resultado.sort(comparador);
        return resultado;
    }

    public <R> List<R> transformar(Function<T, R> funcao) {
        List<R> resultado = new ArrayList<>();
        for (T elemento : elementos) {
            resultado.add(funcao.apply(elemento));
        }
        return resultado;
    }

    public void paraCada(Consumer<T> consumidor) {
        for (T elemento : elementos) {
            consumidor.accept(elemento);
        }
    }

    public int tamanho() {
        return elementos.size();
    }

    public boolean estaVazio() {
        return elementos.isEmpty();
    }
}