package com.example.primeiroteste.pkgVendas;

import com.example.primeiroteste.pkgEstoque.Produto;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<Produto> carrinho;

    public Carrinho() {
        this.carrinho = new ArrayList<>();
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }
}
