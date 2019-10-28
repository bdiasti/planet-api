package br.com.b2w.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StreamUtils;

import br.com.b2w.domain.Planet;
import br.com.b2w.repository.PlanetRepository;
import br.com.b2w.service.PlanetService;
import br.com.b2w.service.PlanetServiceImpl;
import br.com.b2w.v1.controller.PlanetController;



/**
 * @author bdiasti
 * 
 * Classe responsável pelos nossos testes unitarios
 *
 */
@AutoConfigureMockMvc
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@Import({PlanetController.class, PlanetServiceImpl.class})
@RunWith(SpringRunner.class)
@DirtiesContext
public class PlanetControllerTests {
	
	@MockBean
    PlanetService planetService;
    
    @MockBean
    PlanetRepository planetRepository;

    private final Resource jsonOfCreatePlanet = new ClassPathResource("planet-creation.json");
    private final Resource jsonOfCreatePlanetWithoutClima = new ClassPathResource("planet-creation-without-clima.json");
    private final Resource jsonOfCreatePlanetWithoutTerreno = new ClassPathResource("planet-creation-without-terreno.json");
    private final Resource jsonOfCreatePlanetWithoutNome = new ClassPathResource("planet-creation-without-nome.json");
    
    
    @Autowired
    private MockMvc mockMvc;
    
   
    private String asJson(Resource resource) throws IOException {
        return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
    }

    
	@Test
	public void testCreatePlanetWithSuccess() throws IOException, Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/planet/")
				.content(asJson(jsonOfCreatePlanet))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	@Test
	public void testCreatePlanetWithoutNome() throws IOException, Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/planet/")
				.content(asJson(jsonOfCreatePlanetWithoutNome))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is4xxClientError());

	}
	
	@Test
	public void testCreatePlanetWithoutClima() throws IOException, Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/planet/")
				.content(asJson(jsonOfCreatePlanetWithoutClima))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is4xxClientError());

	}
	
	
	@Test
	public void testCreatePlanetWithoutTerreno() throws IOException, Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/planet/")
				.content(asJson(jsonOfCreatePlanetWithoutTerreno))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is4xxClientError());

	}
	
	@Test
	public void testPlanetGetAllEmpty() throws IOException, Exception {
		
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/planet/")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(content().string("[]"));
			     
	}
	
	@Test
	public void testGetAllWithTwoPlanets() throws Exception {

		Planet planet = new Planet();
		planet.set_id(ObjectId.get());
		planet.setNome("Alderaan");
		planet.setClima("Frio");
	
		Planet planet2 = new Planet();
		planet2.set_id(ObjectId.get());
		planet2.setNome("Alderaan");
		planet2.setClima("Frio");
		
		ArrayList<Planet> planetas = new ArrayList<Planet>();
		planetas.add(planet);
		planetas.add(planet2);
		
		 when(this.planetService.getPlanetsWithCountFilms())
         .thenReturn(planetas);
		 
		 MvcResult result =  mockMvc.perform( MockMvcRequestBuilders
			      .get("/planet/")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		assertNotEquals("[]", content);
				  	
			    	
	}

	@Test
	public void testGetPlanetByID() throws Exception {
		
		Planet planet = new Planet();
		planet.set_id(new ObjectId("5d414dcd24e4990006e7c900"));
		planet.setNome("Alderaan");
		planet.setClima("Frio");
		
		when(this.planetService.findBy_id(new ObjectId("5d414dcd24e4990006e7c900")))
        .thenReturn(planet);
		
		 MvcResult result =  mockMvc.perform( MockMvcRequestBuilders
			      .get("/planet/5d414dcd24e4990006e7c900")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andReturn();
		
		String content = result.getResponse().getContentAsString();
			
		assertThat(content, containsString("5d414dcd24e4990006e7c900"));
	}
	
	@Test
	public void testGetPlanetWithoutID() throws Exception {

		 		mockMvc.perform( MockMvcRequestBuilders
			      .put("/planet/5d414dcd24e4990006e7c900")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().is4xxClientError());

		
	}

	@Test
	public void testUpdatePlanetWithSucess() throws Exception {

		Planet planet = new Planet();
		planet.set_id(new ObjectId("5d414dcd24e4990006e7c900"));
		planet.setNome("Alderaan");
		planet.setClima("Frio");
		planet.setTerreno("terrestre");

		doNothing().when(this.planetService).updatePlanet(planet);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/planet/5d414dcd24e4990006e7c900")
				.content(asJson(jsonOfCreatePlanet))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

	}
	
	
	@Test
	public void testDeleteSuccess() throws Exception {

		Planet planet = new Planet();
		planet.set_id(new ObjectId("5d414dcd24e4990006e7c900"));
		planet.setNome("Alderaan");
		planet.setClima("Frio");

		doNothing().when(this.planetService).deletePlanet(planet);

 		mockMvc.perform( MockMvcRequestBuilders
			      .delete("/planet/5d414dcd24e4990006e7c900")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk());

	}
	
	
	
// Comentado pois é um teste de integração	
//	@Test
//	public void testWithRestQLResultFilms() {
//		
//		ClassConfigRepository config = new ClassConfigRepository();
//		config.put("planets", "https://swapi.co/api/planets/?search=Alderaan");
//
//		RestQL restQL = new RestQL(config);
//		QueryResponse response = restQL.executeQuery("from planets");
//
//		PlanetMapper planetMapper = response.get("planets", PlanetMapper.class);
//		
//		assertEquals(2,planetMapper.getResults().get(0).getFilms().size());
//		
//	}
//	



}
