package com.example.primeiroteste.pkgVendas;

import com.example.primeiroteste.pkgEstoque.Produto;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<Produto> itens;

    public Carrinho(List<Produto> itens) {
        this.itens = itens;
    }

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }
}
