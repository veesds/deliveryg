package br.senai.service;

import br.senai.model.Compra;

import java.util.List;

public interface CompraService {

    public List<Compra> findAll();
    public List<Compra> searchFunction(String pesquisa);
    public Compra findById(Long id);

    public Compra save(Compra compra);

    public void deleteById(Long id);
}
