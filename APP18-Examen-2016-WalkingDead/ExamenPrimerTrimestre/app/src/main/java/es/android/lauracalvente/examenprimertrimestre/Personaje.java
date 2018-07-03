package es.android.lauracalvente.examenprimertrimestre;

/**
 * Created by Laura on 09/02/2017.
 */

public class Personaje {

    private String nombrePersonaje, nombreActor, temporada, descripcion, urlPortada;
    Boolean principal;

    public Personaje (String nombrePersonaje, String nombreActor, String temporada, String descripcion, String urlPortada, Boolean principal){
        this.nombrePersonaje = nombrePersonaje;
        this.nombreActor = nombreActor;
        this.temporada = temporada;
        this.descripcion = descripcion;
        this.urlPortada = urlPortada;
        this.principal = principal;
    }

    public Personaje(){};

    public String getNombreActor() {
        return nombreActor;
    }

    public String getTemporada() {
        return temporada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public String getNombrePersonaje() {
        return nombrePersonaje;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setTitulo(String titulo) {
        this.nombrePersonaje = titulo;
    }

    public void setNombreActor(String nombreActor) {
        this.nombreActor = nombreActor;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }
}
