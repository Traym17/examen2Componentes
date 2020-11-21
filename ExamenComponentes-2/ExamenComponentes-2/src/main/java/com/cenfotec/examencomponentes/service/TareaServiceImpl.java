package com.cenfotec.examencomponentes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.examencomponentes.domain.Tarea;
import com.cenfotec.examencomponentes.repo.TareaRepository;
@Service
public class TareaServiceImpl implements TareaService {
	
	@Autowired
	TareaRepository tareaRepo;
	
	@Override
	public void save(Tarea tarea) {
		tareaRepo.save(tarea);
		
	}

}
