package com.avaliacao.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.repository.CrudRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractService <T, PK extends Serializable> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract CrudRepository<T, PK> getRepository();
	
	public T save(T entity) throws ServiceException {
		try {
			return getRepository().save(entity);
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new ServiceException("Erro na gravação", ex);
		}
	}
	
	public Optional<T>  findById(PK id) throws ServiceException {
		try {
			return Optional.of(getRepository().findById(id).get()) ;
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new ServiceException("Erro na consulta", ex);
		}
	}
	
	public List<T> findAll() throws ServiceException {
		try {
			return (List<T>) getRepository().findAll();

		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new ServiceException("Erro na consulta", ex);
		}
	}
	
	public void saveAll(List<T> lst) throws ServiceException {
		try {
			getRepository().saveAll(lst);

		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new ServiceException("Erro gravacao em Lista", ex);
		}
	}
	
	public void delete(T op) throws ServiceException {
		try {
			getRepository().delete(op);
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new ServiceException("Erro ao deletar registro", ex);
		}
	}
	
	

}
