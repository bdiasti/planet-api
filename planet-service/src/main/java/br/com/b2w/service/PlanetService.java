package br.com.b2w.service;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.b2w.domain.Planet;

/**
 * @author bdiasti
 * 
 * Representa a camada de negocio
 *
 */
public interface PlanetService {
	
	public List<Planet> getPlanetsWithCountFilms()  throws JsonParseException, JsonMappingException, IOException;
	
	public void createPlanet(Planet planet);
	
	public void updatePlanet(Planet planet);
	
	public void deletePlanet(Planet planet);
	
	public List<Planet> getPlanets();
	
	Planet findBy_id(ObjectId _id);
	
	List<Planet> findByNome(String nome);

}
