package br.com.ada.Adamovies.dao;

import br.com.ada.Adamovies.model.Ator;
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
public class AtorDAO {
    private static List<Ator> atores = new ArrayList<>();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static int proximoId = 1;

    static {
        try {
            atores = objectMapper.readValue(
                    new File("src/main/java/br/com/ada/Adamovies/database/atores.json"),
                    new TypeReference<>() {});
            if (atores.size() >0) proximoId = atores.get(atores.size() -1).getId() + 1;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void atualizar(Ator ator) {
        for (int i = 0; i < atores.size(); i++) {
            Ator a = atores.get(i);
            if (a.getId() == ator.getId()) {
                atores.set(i, ator);
                break;
            }
        }
        salvarJson();
    }
    public void adicionar(Ator ator) {
        ator.setId(proximoId++);
        atores.add(ator);
        salvarJson();
    }
    public Ator buscarPorId(int id){
        return atores.stream()
                .filter(ator -> ator.getId() == id)
                .findFirst().orElse(null);
    }
    public List<Ator> buscarNaLista(List<Integer> idsAtores) {
        return atores.stream()
                .filter(ator -> idsAtores.contains(ator.getId()))
                .collect(Collectors.toList());
    }
    public List<Ator> buscarTodos() {
        return atores;
    }
    public void remover(int id) {
        atores.removeIf(ator -> ator.getId() == id);
        salvarJson();
    }
    public void salvarJson () {
        try {
            objectMapper.writeValue(
                    new File("src/main/java/br/com/ada/Adamovies/database/atores.json"),
                    atores);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
