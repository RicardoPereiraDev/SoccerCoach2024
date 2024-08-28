package com.devsuperior.SoccerCoach202408.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;


@Entity // fazer o codigo com base na especificação se amanha vc trocar a implementação, vc irá trocar o hibernate por outra implementação e a sua app ira funcionando
@Table(name = "tb_category")
public class CategoryYouthFootball implements Serializable {
    private static final long serialVersionUID = 1L; //É um padrão da linguagem java para que o objecto possa ser convertido em Bytes, isso serve para o objecto poder passar nas redes, ser gravado em arquivo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// esta é anotation para o nosso Id ser autoincrementável
    private Long id;
    private String name;

    public CategoryYouthFootball(){

    }

    public CategoryYouthFootball(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryYouthFootball that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
