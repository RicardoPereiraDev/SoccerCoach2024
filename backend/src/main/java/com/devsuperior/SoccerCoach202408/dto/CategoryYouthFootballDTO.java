package com.devsuperior.SoccerCoach202408.dto;

import com.devsuperior.SoccerCoach202408.entities.CategoryYouthFootball;

import java.io.Serial;
import java.io.Serializable;

public class CategoryYouthFootballDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;

    public CategoryYouthFootballDTO(){

    }

    public CategoryYouthFootballDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }


/*Em relação ao constructor infra, desta forma eu já vou ter um constructor que vai povoar para mim um CategoryDTO simplesmente por eu passar a entidade com um argumento */
    public CategoryYouthFootballDTO(CategoryYouthFootball entitiy){
        this.id = entitiy.getId();
        this.name = entitiy.getName();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
