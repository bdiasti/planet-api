
Planet API
-------------

Permite cadastrar, consultar e excluir planetas. 

Quando realizamos consulta na API é feita uma busca na API swapi afim de descobrir a quantidade de filmes que este planeta apareceu.

https://swapi.co/api/planets/?search=Alderaan

A busca é feita utilizando http://restql.b2w.io/

```java
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

```

Arquitetura Microserviço
-------------

![](https://i.ibb.co/YNZxckv/planet-arquitetura.png)

* Apache: Reponsável por receber as requisições e redirecionar para o Service Discovery.

* Consul: Responsável por registrar nossos microserviços, neste caso temos apenas o Planet Service

* Planet Service: Microserviço construido em Spring Boot.

* MongoDB: Banco de dados que nosso microserviço vai utilizar.

Foram criados 11 casos de testes unitários para este microserviço.


Instalação
-------------

A documentação da API pode ser acessada e pode ser realizado testes no endereço:

http://planet-service.tk:8080/swagger-ui.html

Para testar o load balance

http://planet-service.tk/planet-api/planet/


Instalação Docker
-------------

Com o docker instalado, execute o script run.sh para disponibilizar o ambiente:

source ./run.sh


