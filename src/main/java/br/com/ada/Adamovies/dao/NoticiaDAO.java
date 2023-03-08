package br.com.ada.Adamovies.dao;

import br.com.ada.Adamovies.model.Filme;
import br.com.ada.Adamovies.model.Noticia;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class NoticiaDAO {
    private static List<Noticia> noticias = new ArrayList<>();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static int proximoId = 1;

    static {
        try {
            noticias = objectMapper.readValue(
                    new File("src/main/java/br/com/ada/Adamovies/database/noticias.json"),
                    new TypeReference<>() {});
            System.out.println(("Arquivo 'noticias.json'foi lido!"));
            if (noticias.size() >0) proximoId = noticias.get(noticias.size() -1).getId() + 1;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void adicionar(Noticia noticia) {
        noticia.setId(proximoId++);
        noticias.add(noticia);
        salvarJson();
    }
    public void atualizar(Noticia noticia) {
        for (int i = 0; i < noticias.size(); i++) {
            Noticia n = noticias.get(i);
            if (n.getId() == noticia.getId()) {
                noticias.set(i, noticia);
                break;
            }
        }
    }
    public Noticia buscarPorId(int id) {
        return noticias.stream()
                .filter(noticia -> noticia.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Noticia> buscarTodos() {
        return noticias;
    }
    public void remover(int id) {
        noticias.removeIf(noticia -> noticia.getId() == id);
        salvarJson();
    }
    public void salvarJson () {
        try {
            objectMapper.writeValue(
                    new File("src/main/java/br/com/ada/Adamovies/database/noticias.json"),
                    noticias);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
