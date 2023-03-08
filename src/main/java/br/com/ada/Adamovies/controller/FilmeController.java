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
        List<Filme> listaFilmes = filmeDAO.buscarTodos();
        model.addAttribute("filmes", listaFilmes);
        return "filme_listar";
    }
    @GetMapping("/listareduzida")
    public String listarCinco(Model model) {
        List<Filme> listaFilmes = filmeDAO.listarCinco();
        model.addAttribute("filmes", listaFilmes);
        return "redirect:/home";
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
        return "redirect:/home";
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
        return "redirect:/home";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        filmeDAO.remover(id);
        return "redirect:/home";
    }
    @GetMapping("/like/{id}")
    public String like(@PathVariable int id) {
        filmeDAO.like(id);
        return "redirect:/home";
    }
    @PostMapping("/like")
    public String adicionarLike(@PathVariable int id) {
        filmeDAO.like(id);
        return "redirect:/home";
    }
    @GetMapping("/dislike/{id}")
    public String dislike(@PathVariable int id) {
        filmeDAO.dislike(id);
        return "redirect:/home";
    }
    @PostMapping("/dislike")
    public String adicionarDislike(@PathVariable int id) {
        filmeDAO.dislike(id);
        return "redirect:/home";
    }
    @GetMapping("/favoritar/{id}")
    public String novoFavorito(@PathVariable int id) {
        filmeDAO.favoritar(id);
        return "redirect:/home";
    }

    @PostMapping("/favoritar")
    public String favoritar(Filme filme) {
        filmeDAO.atualizar(filme);
        return "redirect:/home";
    }
    @GetMapping("/desfavoritar/{id}")
    public String excluirFavorito(@PathVariable int id) {
        filmeDAO.desfavoritar(id);
        return "redirect:/home";
    }

    @PostMapping("/desfavoritar")
    public String desfavoritar(Filme filme) {
        filmeDAO.atualizar(filme);
        return "redirect:/home";
    }
    @GetMapping("/buscar/{id}")
    public String listar(@PathVariable int id, Model model) {
        Filme filme = filmeDAO.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "filme_listar";
    }
}
