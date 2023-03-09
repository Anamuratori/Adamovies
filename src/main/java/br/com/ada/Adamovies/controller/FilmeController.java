package br.com.ada.Adamovies.controller;

import br.com.ada.Adamovies.dao.FilmeDAO;
import br.com.ada.Adamovies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private FilmeDAO filmeDAO;
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
    public String listar(@PathVariable int id, Model model) {
        Filme filme = filmeDAO.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "filme_buscar";
    }
}
