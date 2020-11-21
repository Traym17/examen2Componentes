package com.cenfotec.examencomponentes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.examencomponentes.domain.Tarea;

public interface TareaRepository extends JpaRepository<Tarea,Long> {

}
