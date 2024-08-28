package com.devsuperior.SoccerCoach202408.entities;

import java.io.Serializable;
import java.util.Objects;

public class CategoryYouthFootball implements Serializable {
    private static final long serialVersionUID = 1L; //É um padrão da linguagem java para que o objecto possa ser convertido em Bytes, isso serve para o objecto poder passar nas redes, ser gravado em arquivo

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
