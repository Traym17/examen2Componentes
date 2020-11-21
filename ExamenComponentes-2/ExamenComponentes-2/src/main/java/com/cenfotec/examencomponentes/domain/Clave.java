package com.cenfotec.examencomponentes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Clave {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String name;
	
	@ManyToOne
    @JoinColumn(name="clave_id", nullable=false)
	private Workshop workshop;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Workshop getAnthology() {
		return workshop;
	}

	public void setAnthology(Workshop Workshop) {
		this.workshop = workshop;
	}
}
