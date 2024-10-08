package com.devsuperior.SoccerCoach202408.resources;

import com.devsuperior.SoccerCoach202408.dto.CategoryYouthFootballDTO;
import com.devsuperior.SoccerCoach202408.entities.CategoryYouthFootball;
import com.devsuperior.SoccerCoach202408.repositories.CategoryYouthFootballRepository;
import com.devsuperior.SoccerCoach202408.services.CategoryYouthFootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController//isto é uma forma de usar o que já está implementado que foi feito por de trás dos panos
@RequestMapping(value = "/categories") //ja temos aqui uma classe que vai responder nesta rota aqui e agora vamos criar o nosso primeiro endpoint, ou seja, a 1ª rota possivel que vai responder alguma coisa.
public class CategoryYouthFootballResource {

    @Autowired
    private CategoryYouthFootballService service;

    @GetMapping
    public ResponseEntity<List<CategoryYouthFootballDTO>>findAll(){
        List<CategoryYouthFootballDTO> list = service.findAll();
        //Agora vou ter que instanciar uma classe que implementa uma interface
        /*
        List<CategoryYouthFootball>list = new ArrayList<>();
        list.add(new CategoryYouthFootball(1L, "Benjamins"));//Agora vou adicionar um objecto do tipo CategoryYoutFootball
        list.add(new CategoryYouthFootball(2L,"Infantis"));
*/

        //Agora para configurar que esse metodo vai ser um webservices, ou seja, vai se um endPoint do meu recurso Category, vou ter que colocar outro annottation GetMapping
        return  ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryYouthFootballDTO>findById(@PathVariable Long id){
        CategoryYouthFootballDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CategoryYouthFootballDTO> insert(@RequestBody CategoryYouthFootballDTO dto){
        dto = service.insert(dto); //Eu chamo o insert, vou inserir esse dto no banco de dados e o resultado da inserção já com o id inserido pelo banco de dados vai retornar na variavel categoryYouthFootballDto
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
/*O nosso backend vai disponibilizar uma API, ou seja, são os recursos que voçe vai disponibilizar para as aplicações utilizar, por exemplo o app movel vai utilizar a sua API que seria esta parte do codigo backend,
por exemplo aplicativo web vai utilizar sua API, então a sua API vai implementar através dos controladores REST e utiliza-se o termo de resources, é como se o recurso fosse um conceito e o controlador fosse a forma de implementar esse conceito  */