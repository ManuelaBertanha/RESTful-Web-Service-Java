package br.mack.ps2.apiseriesjpa.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mack.ps2.apiseriesjpa.entity.Serie;
import br.mack.ps2.apiseriesjpa.repository.SerieRepository;

@RestController
public class SerieController {

    @Autowired
    SerieRepository serieRepository;

    //construtor
    //public SerieController() {}

    @PostMapping("/api/series")
    public ResponseEntity<Serie> create(@RequestBody Serie serie) {
        Serie _serie = serieRepository.save(serie);
        return new ResponseEntity<>(_serie, HttpStatus.CREATED);
    }

    @GetMapping("/api/series")
    public ResponseEntity<List<Serie>> read() {
        List<Serie> serie = serieRepository.findAll();
        return new ResponseEntity<>(serie, HttpStatus.OK);
    }

    @GetMapping("/api/series/{id}")
    public ResponseEntity<Serie> readById(@PathVariable("id") long id) {
        Serie serie = serieRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(serie, HttpStatus.OK);
    }

    @PutMapping("/api/series/{id}")
    public Serie update(@RequestBody Serie serie, @PathVariable long id) {
        Optional<Serie> op = this.serieRepository.findById(id);
        if (op.isPresent()) {

            Serie s = op.get();

            String url = serie.getUrl();
            String title = serie.getTitle();
            String director = serie.getDirector();
            String mainCast = serie.getMainCast();
            String genre = serie.getGenre();
            String synopsis = serie.getSynopsis();
            String streaming = serie.getStreaming();
            String awards = serie.getAwards();
            int year = serie.getYear();
            int seasons = serie.getSeasons();
            int rating = serie.getRating();

            if (url != null) s.setUrl(url);
            if (title != null) s.setTitle(title);
            if (director != null) s.setDirector(director);
            if (mainCast != null) s.setMainCast(mainCast);
            if (genre != null) s.setGenre(genre);
            if (synopsis != null) s.setSynopsis(synopsis);
            if (streaming != null) s.setStreaming(streaming);
            if (awards != null) s.setAwards(awards);
            if (year != 0) s.setYear(year);
            if (seasons != 0) s.setSeasons(seasons);
            if (rating != 0) s.setRating(rating);

            this.serieRepository.save(s);
            return s;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/api/series/{id}", produces = "application/json")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        serieRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}