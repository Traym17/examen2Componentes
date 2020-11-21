package com.cenfotec.examencomponentes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.examencomponentes.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>  {

}
