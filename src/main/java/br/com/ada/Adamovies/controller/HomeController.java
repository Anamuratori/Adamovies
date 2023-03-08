package br.com.ada.Adamovies.controller;

import br.com.ada.Adamovies.dao.FilmeDAO;
import br.com.ada.Adamovies.dao.NoticiaDAO;
import br.com.ada.Adamovies.model.Filme;
import br.com.ada.Adamovies.model.Noticia;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private FilmeDAO filmeDAO;
    @Autowired
    private NoticiaDAO noticiaDAO;
    @GetMapping
    public String listar(Model model) {
        List<Noticia> listaNoticias = noticiaDAO.buscarTodos();
        List<Filme> listaFilmes = filmeDAO.buscarTodos();
        model.addAttribute("filmes", listaFilmes);
        model.addAttribute("noticias", listaNoticias);
        return "pagina_inicial";
    }
}
