package com.cenfotec.examencomponentes.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cenfotec.examencomponentes.domain.Categoria;
import com.cenfotec.examencomponentes.domain.Clave;
import com.cenfotec.examencomponentes.repo.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepo;
	
	
	public void save(Categoria categoria) {
		categoriaRepo.save(categoria);
	}


	@Override
	public List<Categoria> getAll() {
		// TODO Auto-generated method stub
		return categoriaRepo.findAll();
	}


	@Override
	public Optional<Categoria> get(Long id) {
		// TODO Auto-generated method stub
		return categoriaRepo.findById(id);
	}


	@Override
	public void delete(Categoria categoria) {
		categoriaRepo.delete(categoria);
		
	}
}
