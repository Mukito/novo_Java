package com.mukito.estudo_CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mukito.estudo_CRUD.model.Produto;



@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
