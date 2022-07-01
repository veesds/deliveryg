package br.senai.service;

import br.senai.model.Cliente;
import br.senai.model.Compra;
import br.senai.model.Evento;
import br.senai.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CompraServiceImpl implements CompraService{

    @Autowired
    CompraRepository compraRepository;

    @Override
    public List<Compra> findAll() {
        return compraRepository.findAll(Sort.by("id"));
    }

    @Override
    public List<Compra> searchFunction(String pesquisa){
        List<Compra> compra = compraRepository.findAll();
        List<Compra> compraFiltrados = new ArrayList<>();
        for (Compra e: compra) {if(e.getProduto().contains(pesquisa)){compraFiltrados.add(e);}}
        return compraFiltrados;
    }

    @Override
    public Compra findById(Long id) {
        Optional listCompra = compraRepository.findById(id);
        if (!listCompra.isEmpty()){
            return (Compra) listCompra.get();
        } else {
            return new Compra();
        }
    }

    @Override
    public Compra save(Compra compra) {
        try{
            return compraRepository.save(compra);
        } catch (Exception e){
            throw (e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            compraRepository.deleteById(id);
        } catch (Exception e){
            throw e;
        }

    }
}
