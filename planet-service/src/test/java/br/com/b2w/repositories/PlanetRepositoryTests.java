package br.com.b2w.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.domain.Planet;
import br.com.b2w.repository.PlanetRepository;



/**
 * @author bdiasti
 * 
 * Testes unitarios da camada respository
 *
 */
@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class PlanetRepositoryTests {
	
    @Autowired
    PlanetRepository planetRepository;
	
	
    @Test
    public void testRepository() throws  Exception {
    	
        this.planetRepository.deleteAll();
        
		Planet planet = new Planet();
		planet.set_id(new ObjectId("5d414dcd24e4990006e7c900"));
		planet.setNome("Alderaan");
		planet.setClima("Frio");
		
		Planet saved= this.planetRepository.save(planet);

        Assert.assertNotNull(saved.get_id());

        List<Planet> list  = this.planetRepository.findAll();

        Assert.assertEquals(1, list.size());
    }

}
