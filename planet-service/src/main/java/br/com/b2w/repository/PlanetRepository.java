package br.com.b2w.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.b2w.domain.Planet;


/**
 * @author bdiasti
 * 
 * Representa a camada de acesso a dados
 *
 */
public interface PlanetRepository  extends MongoRepository<Planet, String> {

	Planet findBy_id(ObjectId _id);
	
	List<Planet> findByNome(String nome);
	
	
}