package com.devsuperior.SoccerCoach202408.services;

import com.devsuperior.SoccerCoach202408.entities.CategoryYouthFootball;
import com.devsuperior.SoccerCoach202408.repositories.CategoryYouthFootballRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryYouthFootballService {

    @Autowired
    private CategoryYouthFootballRepository repository; //Como eu faço agora para que dentro do meu SERVICE eu injecte automaticamente aqui uma instancia gerida pelo Spring? Vai ser só colocar uma anotation aqui em cima do private CategoryYouthRepository

    /*Se colocar aqui o Transactional, eu garanto que o meu metodo vai executar uma transação com o banco de dados e se colocar o readOnly eu garanto que não vou estar travando o meu banco de dados     */
    @Transactional(readOnly = true)
    public List<CategoryYouthFootball> findAll(){
        return repository.findAll();

    }
}

/*Agora como é que faço com que este metodo aqui do findAll acessar o repository e chamar lá no banco de dados as categorias?
1ª coisa vou precisar de uma dependencia, o meu categorService ele tem uma dependencia com CategoryRepository */

//Esse anotação vai registrar essa minha classe como um componente que vai particicpar do sistema de injecção de dependencia automatizado aqui do Spring, isso quer dizer que quem vai gerir as instancias das dependencias dos objectos tipo CategoryService vai ser o Spring