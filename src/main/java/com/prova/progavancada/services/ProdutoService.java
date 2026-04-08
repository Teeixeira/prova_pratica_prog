package com.prova.progavancada.services;

import com.prova.progavancada.models.ProdutoModel;
import com.prova.progavancada.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoModel save(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> findAll(){
        return produtoRepository.findAll();
    }

    public ProdutoModel findById(Long id){
        return produtoRepository.findById(id).get();
    }

    public void delete(Long id){
        produtoRepository.deleteById(id);
    }
}