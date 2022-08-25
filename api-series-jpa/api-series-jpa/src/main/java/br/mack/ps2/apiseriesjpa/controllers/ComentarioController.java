package br.mack.ps2.apiseriesjpa.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.mack.ps2.apiseriesjpa.entity.Serie;
import br.mack.ps2.apiseriesjpa.entity.Comentario;
import br.mack.ps2.apiseriesjpa.repository.SerieRepository;
import br.mack.ps2.apiseriesjpa.repository.ComentarioRepository;

@RestController
@RequestMapping("/api")
public class ComentarioController {

    @Autowired
    SerieRepository serieRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    @PostMapping("/series/{serieId}/comentarios")
    public ResponseEntity<Comentario> create(@PathVariable("serieId") long serieId, @RequestBody Comentario comentario) {
        Serie serie = serieRepository.findById(serieId)
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        comentario.setSerie(serie);
        Comentario _comentario = comentarioRepository.save(comentario);
        return new ResponseEntity<>(_comentario, HttpStatus.CREATED);
    }

    @GetMapping("/comentarios")
    public ResponseEntity<List<Comentario>> read() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

    @GetMapping("/series/{serieId}/comentarios")
    public ResponseEntity<List<Comentario>> readBySerieId(@PathVariable("serieId") long serieId) {
        List<Comentario> comentarios = comentarioRepository.findBySerieId(serieId);
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<Comentario> read(@PathVariable("id") long id) {
        Comentario comentario = comentarioRepository.findById(id)
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        comentarioRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}