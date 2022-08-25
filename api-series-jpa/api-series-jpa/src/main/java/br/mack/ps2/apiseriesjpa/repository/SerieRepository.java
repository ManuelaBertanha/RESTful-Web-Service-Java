package br.mack.ps2.apiseriesjpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.mack.ps2.apiseriesjpa.entity.Serie;

public interface SerieRepository extends JpaRepository <Serie, Long> {

    List<Serie> findByTitleContaining(String title);
    
}