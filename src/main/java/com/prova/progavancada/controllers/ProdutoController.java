package com.prova.progavancada.controllers;

import com.prova.progavancada.models.ProdutoModel;
import com.prova.progavancada.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> save(@RequestBody ProdutoModel produto){
        ProdutoModel produtoModel = produtoService.save(produto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoModel.getId())
                .toUri();

        return ResponseEntity.ok().body(produtoModel);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> findAll(){
        List<ProdutoModel> produtos = produtoService.findAll();

        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> findById(@PathVariable Long id){
        ProdutoModel produto = produtoService.findById(id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> update(@PathVariable Long id, @RequestBody ProdutoModel produtoModel){
        ProdutoModel existente = produtoService.findById(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        produtoModel.setId(id);
        ProdutoModel atualizado = produtoService.save(produtoModel);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ProdutoModel existente = produtoService.findById(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}