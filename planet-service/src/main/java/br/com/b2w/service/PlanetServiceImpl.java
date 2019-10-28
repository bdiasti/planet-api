package br.com.b2w.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.b2w.domain.Planet;
import br.com.b2w.mapper.PlanetMapper;
import br.com.b2w.repository.PlanetRepository;
import restql.core.RestQL;
import restql.core.config.ClassConfigRepository;
import restql.core.response.QueryResponse;

/**
 * @author bdiasti
 * 
 * Representa a camada de negocio
 *
 */
@Service
public class PlanetServiceImpl implements PlanetService {

	@Inject
	RestTemplate restTemplate;
	
	@Value("${operations.serviceURL}")
	String serviceURL;
	
	@Inject
	PlanetRepository planetRepository;
	
	@Override
	public List<Planet> getPlanetsWithCountFilms() throws JsonParseException, JsonMappingException, IOException {
		
		List<Planet> planetUptad = new ArrayList<Planet>();
		
		List<Planet>  planets = planetRepository.findAll();
		
		for(Planet planet: planets) {
			planet.setCountFilms(getPlanetSwapi(planet.getNome()));
			planetUptad.add(planet);
			
		}
		
		return planetUptad;
	}
	
	

	@Override
	public void createPlanet(Planet planet) {
		planetRepository.save(planet);
	}


	@Override
	public void updatePlanet(Planet planet) {
		if(planet != null)
			planetRepository.save(planet);
		
	}


	@Override
	public void deletePlanet(Planet planet) {
		if(planet != null)
			planetRepository.delete(planet);
		
	}


	@Override
	public List<Planet> getPlanets() {
		return planetRepository.findAll();
	}


	@Override
	public Planet findBy_id(ObjectId _id) {
		
		Planet planet = planetRepository.findBy_id(_id);
		
		if(planet != null ) {
			try {
				planet.setCountFilms(getPlanetSwapi(planet.getNome()));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return planet;
			
		}else
			return null;
			
	}

	
	public int getPlanetSwapi(String planet) throws JsonParseException, JsonMappingException, IOException {
		
		// Feito de acordo com a doc: https://github.com/B2W-BIT/restQL-java
		ClassConfigRepository config = new ClassConfigRepository();
		config.put("planets",serviceURL + URLEncoder.encode(planet, "utf-8"));

		RestQL restQL = new RestQL(config);
		QueryResponse response = restQL.executeQuery("from planets");
		PlanetMapper planetMapper = response.get("planets", PlanetMapper.class);
		
		if (planetMapper.getResults().size() > 0)
			return  planetMapper.getResults().get(0).getFilms().size();
		else
			return 0;

	}



	@Override
	public List<Planet> findByNome(String nome) {
		
		List<Planet> planets = planetRepository.findByNome(nome);
		ArrayList<Planet> planetasAtualizado = new ArrayList<Planet>();
		
		if(planets.size() >= 1) {
			try {
				for(Planet planet:planets) {
					planet.setCountFilms(getPlanetSwapi(planet.getNome()));
					planetasAtualizado.add(planet);
				}
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return planetasAtualizado;
			
		}else {
			
			return planetasAtualizado;
		}
	}
}
