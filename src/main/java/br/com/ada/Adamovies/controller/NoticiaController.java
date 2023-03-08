package br.com.ada.Adamovies.controller;

import br.com.ada.Adamovies.dao.NoticiaDAO;
import br.com.ada.Adamovies.model.Filme;
import br.com.ada.Adamovies.model.Noticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/noticia")
public class NoticiaController {
    @Autowired
    private NoticiaDAO noticiaDAO;
    @GetMapping
    public String listar(Model model) {
        List<Noticia> listaNoticias = noticiaDAO.buscarTodos();
        model.addAttribute("noticias", listaNoticias);
        return "noticia_listar";
    }
    @GetMapping("/novo")
    public String novo (Model model) {
        model.addAttribute("noticia", new Noticia());
        return "noticia_novo";
    }
    @PostMapping("/novo")
    public String adicionar (Noticia noticia) {
        noticiaDAO.adicionar(noticia);
        return "redirect:/noticia";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Noticia noticia = noticiaDAO.buscarPorId(id);
        model.addAttribute("noticia", noticia);
        return "noticia_editar";
    }

    @PostMapping("/editar")
    public String atualizar(Noticia noticia) {
        noticiaDAO.atualizar(noticia);
        return "redirect:/noticia";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        noticiaDAO.remover(id);
        return "redirect:/noticia";
    }
}
