package com.devsuperior.SoccerCoach202408.repositories;

import com.devsuperior.SoccerCoach202408.entities.CategoryYouthFootball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //qnd eu faço isto aqui, os objectos do categoryRepository passam a ser geridos pelo Spring
public interface CategoryYouthFootballRepository extends JpaRepository<CategoryYouthFootball,Long> {


}


/*paRepository<CategoryYouthFootball,Long> É a identidade dentro <> e o Id é do tipo Long  */