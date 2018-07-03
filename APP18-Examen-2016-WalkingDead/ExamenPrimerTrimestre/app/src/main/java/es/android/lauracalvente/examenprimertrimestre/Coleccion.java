package es.android.lauracalvente.examenprimertrimestre;

import java.util.ArrayList;

/**
 * Created by Laura on 09/02/2017.
 */

public class Coleccion {
    private static ArrayList<Personaje> bdd = new ArrayList<>();

    static {
        String descripcion = "Glenn es un personaje de ficción de la serie de cómic The Walking Dead y es interpretado por Steven Yeun en la serie de televisión del mismo nombre en el canal AMC.";
        bdd.add(new Personaje("Glenn Rhee", "Steven Yeun","1",descripcion,"http://vignette1.wikia.nocookie.net/p__/images/1/19/Glenn_Rhee!_(Steven_Yeun)_-A-.png/revision/latest?cb=20141111151505&path-prefix=protagonist", false));
        descripcion = "Daryl Dixon es un personaje ficticio de la serie de televisión The Walking Dead, que se transmite por AMC en Estados Unidos y se basa en la serie de cómics del mismo nombre.";
        bdd.add(new Personaje("Daryl Dixon","Norman Mark","3",descripcion,"http://www.vixenvarsity.com/wp-content/uploads/2015/01/the-walking-dead-season-5-daryl-dixon-portrait.jpg", true));
        descripcion = "Carol Peletier, o simplemente Carol, es un personaje ficticio de la serie de cómics de The Walking Dead y es interpretado por Melissa McBride en la serie de televisión estadounidense del mismo nombre The Walking Dead. ";
        bdd.add(new Personaje("Carol Peletier","Melissa McBride","1",descripcion,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/CarolPeletier.jpg/220px-CarolPeletier.jpg",false));
        descripcion = "Glenn es un personaje de ficción de la serie de cómic The Walking Dead y es interpretado por Steven Yeun en la serie de televisión del mismo nombre en el canal AMC.";
        bdd.add(new Personaje("Glenn Rhee", "Steven Yeun","1",descripcion,"http://vignette1.wikia.nocookie.net/p__/images/1/19/Glenn_Rhee!_(Steven_Yeun)_-A-.png/revision/latest?cb=20141111151505&path-prefix=protagonist", false));
        descripcion = "Daryl Dixon es un personaje ficticio de la serie de televisión The Walking Dead, que se transmite por AMC en Estados Unidos y se basa en la serie de cómics del mismo nombre.";
        bdd.add(new Personaje("Daryl Dixon","Norman Mark","3",descripcion,"http://www.vixenvarsity.com/wp-content/uploads/2015/01/the-walking-dead-season-5-daryl-dixon-portrait.jpg", true));
        descripcion = "Carol Peletier, o simplemente Carol, es un personaje ficticio de la serie de cómics de The Walking Dead y es interpretado por Melissa McBride en la serie de televisión estadounidense del mismo nombre The Walking Dead. ";
        bdd.add(new Personaje("Carol Peletier","Melissa McBride","1",descripcion,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/CarolPeletier.jpg/220px-CarolPeletier.jpg",false));
        descripcion = "Glenn es un personaje de ficción de la serie de cómic The Walking Dead y es interpretado por Steven Yeun en la serie de televisión del mismo nombre en el canal AMC.";
        bdd.add(new Personaje("Glenn Rhee", "Steven Yeun","1",descripcion,"http://vignette1.wikia.nocookie.net/p__/images/1/19/Glenn_Rhee!_(Steven_Yeun)_-A-.png/revision/latest?cb=20141111151505&path-prefix=protagonist", false));
        descripcion = "Daryl Dixon es un personaje ficticio de la serie de televisión The Walking Dead, que se transmite por AMC en Estados Unidos y se basa en la serie de cómics del mismo nombre.";
        bdd.add(new Personaje("Daryl Dixon","Norman Mark","3",descripcion,"http://www.vixenvarsity.com/wp-content/uploads/2015/01/the-walking-dead-season-5-daryl-dixon-portrait.jpg", true));
        descripcion = "Carol Peletier, o simplemente Carol, es un personaje ficticio de la serie de cómics de The Walking Dead y es interpretado por Melissa McBride en la serie de televisión estadounidense del mismo nombre The Walking Dead. ";
        bdd.add(new Personaje("Carol Peletier","Melissa McBride","1",descripcion,"https://upload.wikimedia.org/wikipedia/en/thumb/8/80/CarolPeletier.jpg/220px-CarolPeletier.jpg",false));
    }

    public static void agregarPersonaje(Personaje personaje) {
        bdd.add(personaje);
    }

    public static void eliminarPersonaje(Personaje personaje) {
        bdd.remove(personaje);
    }

    public static Personaje getPersonajeAtIndex(int position) {
        return bdd.get(position);
    }

    public static ArrayList<Personaje> getpersonajes() {
        return bdd;
    }
}