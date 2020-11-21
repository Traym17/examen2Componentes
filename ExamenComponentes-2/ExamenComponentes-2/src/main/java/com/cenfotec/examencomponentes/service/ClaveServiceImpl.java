package com.cenfotec.examencomponentes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.examencomponentes.domain.Clave;
import com.cenfotec.examencomponentes.repo.CategoriaRepository;
import com.cenfotec.examencomponentes.repo.ClaveRepository;

@Service
public class ClaveServiceImpl implements ClaveService {
	
	@Autowired
	ClaveRepository claveRepo;

	@Override
	public void save(Clave clave) {
		claveRepo.save(clave);
		
	}

}
