package br.mack.ps2.apiseriesjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mack.ps2.apiseriesjpa.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    public List<Comentario> findByTextoContaining(String texto);
    public List<Comentario> findBySerieId(Long id);

}
