package br.com.ada.Adamovies.model;

import java.util.ArrayList;
import java.util.List;

public class FilmeComAtor {
    private Filme filme;
    private List<Integer> idsAtores;

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public List<Integer> getIdsAtores() {
        return idsAtores;
    }

    public void setIdsAtores(List<Integer> idsAtores) {
        this.idsAtores = idsAtores;
    }
}
