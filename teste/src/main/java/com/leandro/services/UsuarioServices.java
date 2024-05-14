package com.leandro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.leandro.entities.Usuario;
import com.leandro.repositories.UsuarioRepository;
import com.leandro.services.exceptions.DatabaseException;
import com.leandro.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class UsuarioServices {
@Autowired
	
	private UsuarioRepository repository;
	public List<Usuario>findAll(){
		return repository.findAll();
	}
	public Usuario findById(Long id) {
		Optional<Usuario>obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	public Usuario insert(Usuario obj) {
		return repository.save(obj);
	}
	public void delete (Long id) {
		try {
			
		repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	public Usuario update(Long id,Usuario obj) {
		try {
		Usuario entity = repository.getReferenceById(id);
		updateData(entity,obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e ) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(Usuario entity,Usuario obj) {
		entity.setName(obj.getName());
		entity.setData(obj.getData());
		entity.setEndereco(obj.getEndereco());
	}
	
	

}
