package com.devsuperior.SoccerCoach202408.services;

import com.devsuperior.SoccerCoach202408.dto.CategoryYouthFootballDTO;
import com.devsuperior.SoccerCoach202408.entities.CategoryYouthFootball;
import com.devsuperior.SoccerCoach202408.repositories.CategoryYouthFootballRepository;
import com.devsuperior.SoccerCoach202408.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        CategoryYouthFootball entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found")); //Se o CategoryYouthFootball não existir, este orElseThrow vai lançar esta excepção
        return new CategoryYouthFootballDTO(entity);
    }

    @Transactional
    public CategoryYouthFootballDTO insert(CategoryYouthFootballDTO dto) { //Vou ter que converter este dto para uma entidade, vou ter que converter este DTO para um objecto do tipo CategoryYouthFootbal que é a minha entidade.
        CategoryYouthFootball entity = new CategoryYouthFootball();
        entity.setName(dto.getName());

        //Agora que já atribui os valores o que irei fazer é salvar e para salvar tenho que chamar o repository e vou chamar o metodo save passando a entidade, só que esse metodo save por padrao ele retorna uma referencia para uma entidade salva por isso a entidade irá receber repository.save da referencia dele;
        //Depois vou ter que retornar essa entidade convertida novamente para um CategoryYouthFootballDTO
        entity= repository.save(entity);

        //Agora vou ter que retornar essa entidade para um CategoryYouthFootballDTO
        return new CategoryYouthFootballDTO(entity); //esta é a implementação
    }

    @Transactional //Aqui para não irmos ao banco de dados 2 vezes vamos utilizar o getReferenceById
    public CategoryYouthFootballDTO update(Long id, CategoryYouthFootballDTO dto) {
        try{
            CategoryYouthFootball entity = repository.getReferenceById(id); // aqui com o getReferenceById ele não toca no banco de dados, ele vai instanciar um objecto provisorio, só quando mandar salvar, ai sim é que ele vai no banco de dados
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryYouthFootballDTO(entity);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }
}

/*Agora como é que faço com que este metodo aqui do findAll acessar o repository e chamar lá no banco de dados as categorias?
1ª coisa vou precisar de uma dependencia, o meu categorService ele tem uma dependencia com CategoryRepository */

//Esse anotação vai registrar essa minha classe como um componente que vai particicpar do sistema de injecção de dependencia automatizado aqui do Spring, isso quer dizer que quem vai gerir as instancias das dependencias dos objectos tipo CategoryService vai ser o Spring