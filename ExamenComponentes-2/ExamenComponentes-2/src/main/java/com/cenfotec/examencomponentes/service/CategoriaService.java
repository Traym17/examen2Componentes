package com.cenfotec.examencomponentes.service;

import java.util.List;
import java.util.Optional;

import com.cenfotec.examencomponentes.domain.Categoria;

public interface CategoriaService {
	public void save(Categoria categoria);
	public void delete(Categoria categoria);
	public List<Categoria> getAll();
	public Optional<Categoria> get(Long id);
}
