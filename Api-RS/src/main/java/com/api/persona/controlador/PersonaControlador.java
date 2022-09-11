package com.api.persona.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.persona.infraestructura.repositorio.PersonaRepositorio;
import com.api.persona.modelo.Persona;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PersonaControlador {
	
	@Autowired
	PersonaRepositorio personaRepositorio;
	@GetMapping("/personas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Persona> getPersonas(){
		List<Persona> personas = new ArrayList<Persona>();
		personas = personaRepositorio.findAll();
		return personas;
	}
	
	@PostMapping("/personaSave")
	public ResponseEntity<?> savePersona(@RequestBody Persona persona, BindingResult result){
		Persona personaGuardar;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo'"+ err.getField()+"'"+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("Los errores son", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			personaGuardar = personaRepositorio.save(persona);
			
		} catch(DataAccessException e){
			response.put("mensaje", "Error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El usuario fue guardado con éxito");
		response.put("Usuario", personaGuardar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	@PutMapping("/personaSave/{id}")
	public ResponseEntity<?> modificarPersona(@RequestBody Persona persona, BindingResult result,
			@PathVariable int id) {
		
		
		Map<String, Object> response = new HashMap<>();
		Persona personaEditar;
		try {
		personaEditar=personaRepositorio.getById(id);
		}catch(DataAccessException e) {
	
				response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
						.concat(id + " no existe en la base de datos!"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		Persona personaEditada = new Persona();
		
		
		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("Los errores son", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		

		
		try {
			
			personaEditar.setNombre(persona.getNombre());
			personaEditar.setCorreo(persona.getCorreo());
			personaEditar.setContrasena(persona.getContrasena());
			personaEditar.setCelular(persona.getCelular());
			
			
			personaEditada= personaRepositorio.save(personaEditar); 
			
			
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje", "El Usuario se ha modificado con éxito en la BD");
		response.put("Usuario", personaEditada);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/personasDelete/{id}")
	public ResponseEntity<?> eliminarCliente(
			@PathVariable int id) {
		
		
		Map<String, Object> response = new HashMap<>();
		try {
			personaRepositorio.deleteById(id);
		}catch(DataAccessException e) {
	
				response.put("mensaje", "Error: no se pudo eliminar, el usuario. ID: "
						.concat(id + " no existe en la base de datos!"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
			
		
		response.put("mensaje", "El usuario se ha eliminado con éxito en la BD");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	

}
