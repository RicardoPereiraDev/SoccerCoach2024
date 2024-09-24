package com.devsuperior.SoccerCoach202408.services;

import com.devsuperior.SoccerCoach202408.dto.CategoryYouthFootballDTO;
import com.devsuperior.SoccerCoach202408.entities.CategoryYouthFootball;
import com.devsuperior.SoccerCoach202408.repositories.CategoryYouthFootballRepository;
import com.devsuperior.SoccerCoach202408.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryYouthFootballService {

    @Autowired
    private CategoryYouthFootballRepository repository; //Como eu faço agora para que dentro do meu SERVICE eu injecte automaticamente aqui uma instancia gerida pelo Spring? Vai ser só colocar uma anotation aqui em cima do private CategoryYouthRepository

    /*Se colocar aqui o Transactional, eu garanto que o meu metodo vai executar uma transação com o banco de dados e se colocar o readOnly eu garanto que não vou estar travando o meu banco de dados     */
    @Transactional(readOnly = true)
    public List<CategoryYouthFootballDTO> findAll(){
        List<CategoryYouthFootball> list = repository.findAll();//Fui lá no repository, busquei todas as categorias do banco de dados e guardei nessa lista de CategoryYouthFootball

        //COM METODO LAMBDA
        List<CategoryYouthFootballDTO> listDto = list.stream().map(x -> new CategoryYouthFootballDTO(x)).collect(Collectors.toList());


        //Agora vou ter que converter essa lista de categorias para uma lista de categoriasDTO
       // List<CategoryYouthFootballDTO> listDto = new ArrayList<>(); //Estou a instaciar uma lista vazia

        /*Agora posso percorrer um for, ou seja, para cada categoria cat na minha lista list, o que é que eu vou fazer?  */
        //for(CategoryYouthFootball cat : list){
            //para cada elemento da list, pego esse elemento da list que é o cat, passo ele no argumento do constructor CategoryYouthFootballDTO, ai eu vou instanciar um DTO com essa Categoria "CategoryYouthFootballDTO" e add esse DTO na minha lista de listDTO E NO FINAL DAS CONTAS EU MANDO RETORNAR UM listDTO;
          //  listDto.add(new CategoryYouthFootballDTO(cat));

        //}
        /*Comclusão: É uma forma de vc converter uma lista que era de List<CategoryYouthFootball> para uma lista que agora vai ser de DTO  */
        //return listDto;
        return listDto;
    }

    @Transactional(readOnly = true)
    public CategoryYouthFootballDTO findById(Long id) {
        Optional<CategoryYouthFootball> obj = repository.findById(id);
        CategoryYouthFootball entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found")); //Se o CategoryYouthFootball não existir, este orElseThrow vai lançar esta excepção
        return new CategoryYouthFootballDTO(entity);
    }
}

/*Agora como é que faço com que este metodo aqui do findAll acessar o repository e chamar lá no banco de dados as categorias?
1ª coisa vou precisar de uma dependencia, o meu categorService ele tem uma dependencia com CategoryRepository */

//Esse anotação vai registrar essa minha classe como um componente que vai particicpar do sistema de injecção de dependencia automatizado aqui do Spring, isso quer dizer que quem vai gerir as instancias das dependencias dos objectos tipo CategoryService vai ser o Spring