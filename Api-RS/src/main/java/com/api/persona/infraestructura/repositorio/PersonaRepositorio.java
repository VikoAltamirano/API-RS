package com.api.persona.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.persona.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer>{

}
