package br.com.ada.Adamovies.controller;

import br.com.ada.Adamovies.dao.FilmeDAO;
import br.com.ada.Adamovies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private FilmeDAO filmeDAO;
    @GetMapping
    public String listar(Model model) {
        List<Filme> lista = filmeDAO.buscarTodos();
        Filme titanic = new Filme();
        titanic.setTitulo("Titanic");
        titanic.setGenero("Drama");
        titanic.setDuracao(3.14);
        titanic.setImagem("<img src=https://upload.wikimedia.org/wikipedia/pt/2/22/Titanic_poster.jpg>");
        lista.add(titanic);
        model.addAttribute("filmes", lista);
        return "filme_listar";
    }
    @GetMapping("/novo")
    public String novo (Model model) {
        model.addAttribute("filme", new Filme());
        return "filme_novo";
    }
    @PostMapping("/novo")
    public String adicionar (Filme filme) {
        filmeDAO.adicionar(filme);
        return "redirect:/filme";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Filme filme = filmeDAO.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "filme_editar";
    }

    @PostMapping("/editar")
    public String atualizar(Filme filme) {
        filmeDAO.atualizar(filme);
        return "redirect:/filme";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        filmeDAO.remover(id);
        return "redirect:/filme";
    }
}
