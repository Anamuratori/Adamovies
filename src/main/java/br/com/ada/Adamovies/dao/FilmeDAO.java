package br.com.ada.Adamovies.dao;

import br.com.ada.Adamovies.model.Filme;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmeDAO {
    private static List<Filme> filmes = new ArrayList<>();
    private static List<Filme> filmesFavoritos = new ArrayList<>();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static int proximoId = 1;

    static {
        try {
            filmes = objectMapper.readValue(
                    new File("src/main/java/br/com/ada/Adamovies/database/filmes.json"),
                    new TypeReference<>() {});
            System.out.println(("Arquivo 'filmes.json'foi lido!"));
            if (filmes.size() >0) proximoId = filmes.get(filmes.size() -1).getId() + 1;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void adicionar(Filme filme) {
        filme.setId(proximoId++);
        filmes.add(filme);
        salvarJson();
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
    public List<Filme> listarCinco() {
        return filmes.stream()
                .limit(2)
                .collect(Collectors.toList());
    }

    public void remover(int id) {
        filmes.removeIf(filme -> filme.getId() == id);
        salvarJson();
    }
    public void favoritar(int id) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == id) {
                filmesFavoritos.add(f);
                break;
            }
        }
        salvarJson();
    }
    public void desfavoritar(int id) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == id) {
                filmesFavoritos.remove(f);
                break;
            }
        }
        salvarJson();
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
        salvarJson();
    }
    public void dislike (int id) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == id) {
                f.setDislike(f.getDislike()+1);
                break;
            }
        }
        salvarJson();
    }
    public void salvarJson () {
        try {
            objectMapper.writeValue(
                    new File("src/main/java/br/com/ada/Adamovies/database/filmes.json"),
                    filmes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
