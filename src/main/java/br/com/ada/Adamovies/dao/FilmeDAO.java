package br.com.ada.Adamovies.dao;

import br.com.ada.Adamovies.model.Filme;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class FilmeDAO {
    private static List<Filme> filmes = new ArrayList<>();
    private static List<Filme> filmesFavoritos = new ArrayList<>();
    private static int proximoId = 1;

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
    public Filme buscarPorId(int id) {
        return filmes.stream()
                .filter(filme -> filme.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Filme> buscarTodos() {
        return filmes;
    }

    public void remover(int id) {
        filmes.removeIf(filme -> filme.getId() == id);
    }

    public void favoritar(int id) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == id) {
                filmesFavoritos.add(f);
                break;
            }
        }
    }
    public List<Filme> buscarFavoritos() {
        return filmesFavoritos;
    }
    public void like(int id) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == id) {
                f.setLike(f.getLike()+1);
                break;
            }
        }
    }
    public void dislike (int id) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == id) {
                f.setDislike(f.getDislike()+1);
                break;
            }
        }
    }
}
