package br.com.ada.Adamovies.dao;

import br.com.ada.Adamovies.model.Filme;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class FilmeDAO {
    private static List<Filme> filmes = new ArrayList<>();
    private static int proximoId = 1;
    private static int like = 0;
    private static int dislike = 0;

    public void adicionar(Filme filme) {
        filme.setId(proximoId++);
        filmes.add(filme);
    }

    public void atualizar(Filme filme) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == filme.getId()) {
                filmes.set(i, filme);
                break;
            }
        }
    }

    public void remover(int id) {
        filmes.removeIf(filme -> filme.getId() == id);
    }

    public Filme buscarPorId(int id) {
        return filmes.stream()
                .filter(filme -> filme.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Filme> buscarTodos() {
        return filmes;
    }
}
