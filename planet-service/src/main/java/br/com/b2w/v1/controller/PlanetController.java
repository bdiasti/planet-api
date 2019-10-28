package br.com.b2w.v1.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import br.com.b2w.domain.Planet;
import br.com.b2w.domain.PlanetModel;
import br.com.b2w.service.PlanetService;
import lombok.extern.slf4j.Slf4j;


/**
 * @author bdiasti
 * 
 * Representa a camada controller, responsável por receber as requisições
 *
 */
@RestController
@RequestMapping({"planet"})
@Api(value = "Endpoints para cadastrar e buscar planetas")
public class PlanetController {
		
	@Autowired
	PlanetService planetService;
	
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "Endpoint para listar planetas cadastrados e quantidade de filmes que participaram",
      produces = "Application/JSON", response = Planet.class, httpMethod = "GET")
	public List<Planet> getAllPlanets() throws JsonParseException, JsonMappingException, IOException {
	  return planetService.getPlanetsWithCountFilms();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Endpoint para listar um planeta por ID",
    produces = "Application/JSON", response = Planet.class, httpMethod = "GET")
	public Planet getPlanetaById(@PathVariable("id") ObjectId id) {
	  return planetService.findBy_id(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = "Endpoint criar um planeta",
    produces = "Application/JSON", response = Planet.class, httpMethod = "POST" )
	public Planet createPlanet(@Valid @RequestBody PlanetModel planetModel) {
	   Planet planet = new Planet();	
	  planet.set_id(ObjectId.get());
	  planet.setNome(planetModel.getNome());
	  planet.setClima(planetModel.getClima());
	  planet.setTerreno(planetModel.getTerreno());
	  
	  planetService.createPlanet(planet);
	  return planet;
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Endpoint para atualizar um planeta", produces = "Application/JSON", response = Planet.class, httpMethod = "PUT")
	public void modifyPlanetById(@PathVariable("id") ObjectId id, @Valid @RequestBody PlanetModel planetModel) {
		Planet planet = new Planet();
		planet.set_id(id);
		planet.setNome(planetModel.getNome());
		planet.setClima(planetModel.getClima());
		planet.setTerreno(planetModel.getTerreno());
		planetService.updatePlanet(planet);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Endpoint para deletar um planeta",
    produces = "Application/JSON", response = Planet.class, httpMethod = "DELETE")
	public void deletePlanet(@PathVariable ObjectId id) {
		planetService.deletePlanet(planetService.findBy_id(id));
	}
	
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	@ApiOperation(value = "Endpoint para buscar um planeta por nome",
    produces = "Application/JSON", response = Planet.class, httpMethod = "GET")
	public List<Planet> getPlanetaByName(@PathVariable("nome") String nome) {
	  return planetService.findByNome(nome);
	}
	
	

}
