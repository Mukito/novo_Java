package com.mukito.estudo_CRUD.controller;

import com.mukito.estudo_CRUD.model.Produto;

import org.springframework.web.bind.annotation.*;
import com.mukito.estudo_CRUD.repository.ProdutoRepository;
//import com.mukito.estudo_CRUD.entity.Produto;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;




@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private final ProdutoRepository produtoRepository;

    // Cria o construtor 
    public ProdutoController(ProdutoRepository produtoRepository) {
        // this para instanciar essa classe
        this.produtoRepository = produtoRepository;
    }

    // Post Maping
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProduto() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }
    

}
