package br.com.ada.Adamovies.controller;

import br.com.ada.Adamovies.dao.AtorDAO;
import br.com.ada.Adamovies.model.Ator;
import br.com.ada.Adamovies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ator")
public class AtorController {
    @Autowired
    private AtorDAO atorDAO;
    @GetMapping
    public String listar(Model model) {
        List<Ator> listaAtores = atorDAO.buscarTodos();
        model.addAttribute("atores", listaAtores);
        return "ator_listar";
    }
    @GetMapping("/novo")
    public String novo (Model model) {
        model.addAttribute("ator", new Ator());
        return "ator_novo";
    }
    @PostMapping("/novo")
    public String adicionar (Ator ator) {
        atorDAO.adicionar(ator);
        return "redirect:/filme";
    }
}
