package br.com.ada.Adamovies.controller;

import br.com.ada.Adamovies.dao.AtorDAO;
import br.com.ada.Adamovies.dao.FilmeDAO;
import br.com.ada.Adamovies.model.Ator;
import br.com.ada.Adamovies.model.Filme;
import br.com.ada.Adamovies.model.FilmeComAtor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private FilmeDAO filmeDAO;
    @Autowired
    private AtorDAO atorDAO ;
    @GetMapping
    public String listar(Model model) {
        List<Filme> listaFilmes = filmeDAO.buscarTodos();
        model.addAttribute("filmes", listaFilmes);
        return "filme_listar";
    }
    @GetMapping("/favoritos")
    public String listarFavoritos (Model model) {
        List<Filme> lista = filmeDAO.buscarFavoritos();
        model.addAttribute("filmes", lista);
        return "filme_listar";
    }

    @GetMapping("/novo")
    public String novo (Model model) {
        Filme filme = new Filme();
        List<Integer> idsAtores = new ArrayList<>();
        FilmeComAtor filmeCompleto = new FilmeComAtor();

        filmeCompleto.setFilme(filme);
        filmeCompleto.setIdsAtores(idsAtores);

        model.addAttribute("filmeCompleto", filmeCompleto);
        model.addAttribute("lista_atores", atorDAO.buscarTodos());
        return "filme_novo";
    }
    @PostMapping("/novo")
    public String adicionar (@ModelAttribute("filmeCompleto") FilmeComAtor filmeCompleto) {
        Filme filme = filmeCompleto.getFilme();
        List<Integer> idsAtores = filmeCompleto.getIdsAtores();
        filme.setAtores(atorDAO.buscarNaLista(idsAtores));

        filmeDAO.adicionar(filme);
        return "redirect:/filme";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Filme filme = filmeDAO.buscarPorId(id);
        FilmeComAtor filmeCompleto = new FilmeComAtor();
        filmeCompleto.setFilme(filme);
        filmeCompleto.setIdsAtores(filme.getAtores().stream().map(Ator::getId).collect(Collectors.toList()));
        model.addAttribute("filmeCompleto", filmeCompleto);
        model.addAttribute("lista_atores", atorDAO.buscarTodos());
        return "filme_editar";
    }

    @PostMapping("/editar")
    public String atualizar(@ModelAttribute("filmeCompleto") FilmeComAtor filmeCompleto) {
        Filme filme = filmeCompleto.getFilme();
        List<Integer> idsAtores = filmeCompleto.getIdsAtores();
        filme.setAtores(atorDAO.buscarNaLista(idsAtores));

        filmeDAO.atualizar(filme);
        return "redirect:/filme";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        filmeDAO.remover(id);
        return "redirect:/filme";
    }
    @GetMapping("/like/{id}")
    public String like(@PathVariable int id, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        filmeDAO.like(id);
        return "redirect:" + referrer;
    }
    @GetMapping("/dislike/{id}")
    public String dislike(@PathVariable int id, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        filmeDAO.dislike(id);
        return "redirect:" + referrer;
    }

    @GetMapping("/favoritar/{id}")
    public String novoFavorito(@PathVariable int id, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        filmeDAO.favoritar(id);
        return "redirect:" + referrer;
    }

    @GetMapping("/desfavoritar/{id}")
    public String excluirFavorito(@PathVariable int id, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        filmeDAO.desfavoritar(id);
        return "redirect:" + referrer;
    }

    @GetMapping("/buscar/{id}")
    public String buscarPorId(@PathVariable int id, Model model) {
        Filme filme = filmeDAO.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "filme_buscar";
    }
}
