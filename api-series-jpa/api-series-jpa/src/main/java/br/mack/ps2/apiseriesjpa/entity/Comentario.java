package br.mack.ps2.apiseriesjpa.entity;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comentarios")
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String texto;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "serie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Serie serie;

    //construtor padrão:
    public Comentario(){}

    //outro construtor:
    public Comentario(String texto) {
        this.texto = texto;
    }

    //Getters and Setters:

    /**
	 * @return the id
	 */
    public long getId() {
        return id;
    }

    /**
	 * @param id the id to set
	 */
    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

}