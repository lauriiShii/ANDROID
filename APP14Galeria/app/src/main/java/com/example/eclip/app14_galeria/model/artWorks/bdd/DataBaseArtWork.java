package com.example.eclip.app14_galeria.model.artWorks.bdd;

import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;

import java.util.ArrayList;

/**
 * Created by eclip on 24/11/2017.
 */

public class DataBaseArtWork {

    private static DataBaseArtWork instance;
    private final ArrayList<ArtWork> artWorks;

    private DataBaseArtWork(){
        artWorks = new ArrayList<ArtWork>();
        loadDataBaseArtWork();
    }

    private void loadDataBaseArtWork(){
        artWorks.add(new ArtWork(maxId()+1,"https://pre00.deviantart.net/006f/th/pre/i/2013/260/6/b/care_of_magical_creatures_class_by_dreamsoffools-d6mpnsx.png", "Dreamsoffols", "Mobogenie.com", 2011));
        artWorks.add(new ArtWork(maxId()+1,"https://pbs.twimg.com/media/Bm-zrnpCIAI2kCP.jpg", "Dormiens Nunquam", "Accionknightbus", 2008));
        artWorks.add(new ArtWork(maxId()+1,"https://s-media-cache-ak0.pinimg.com/originals/20/74/53/2074537cd514bc0c439ce308770e19df.jpg", "Hogwarts <3", "pinterest", 2016));
        artWorks.add(new ArtWork(maxId()+1,"https://i.pinimg.com/736x/fc/8b/db/fc8bdbfe2cdeaeaec1289ca26739acf7--harry-potter-marauders-the-marauders.jpg", "Map", "Lego Harry", 2005));
        artWorks.add(new ArtWork(maxId()+1,"https://i.pinimg.com/736x/4d/d8/7d/4dd87d0a6a12d9524f8a101247b682ca--harry-potter-wall-art-harry-potter-books.jpg", "The Questing Room", "Pinterest", 2011));
        artWorks.add(new ArtWork(maxId()+1,"https://data.whicdn.com/images/252431445/original.jpg", "fantastics animals", "wehwartit.com", 2013));
        artWorks.add(new ArtWork(maxId()+1,"https://i.pinimg.com/736x/2f/89/2c/2f892cda2aa3e4ba5fb50d3068ea5e7e--harry-potter-illustrations-art-illustrations.jpg", "Group Magical", "Pinterest", 2017));
        artWorks.add(new ArtWork(maxId()+1,"https://s-media-cache-ak0.pinimg.com/originals/b7/4d/14/b74d14b23ebcb0d35921f69e678d701e.jpg", "Dobbly", "Art Prints", 2001));
        artWorks.add(new ArtWork(maxId()+1,"http://70f186a60af817fe0731-09dac41207c435675bfd529a14211b5c.r92.cf1.rackcdn.com/assets/attachments_p/000/024/811/size500_harrypotter_cherischolten_hagridandthekids_detail1.jpg", "Hagrid", "gallerynucleus.com", 2003));

    }

    /***
     * DataBaseUsers: Inicializa una instancia de la bdd de usuarios.
     * @return instancia de la bdd
     */
    public static DataBaseArtWork getInstance() {
        if (instance == null) {
            instance = new DataBaseArtWork();
        }
        return instance;
    }

    /***
     * GetArtWork: nos da la lista completa de las imagenes
     * @return artWorks
     */
    public ArrayList<ArtWork> getArtWorks(){return artWorks;}

    /***
     * MaxId: busca el maximo id
     * @return devuelve el id maximo que se encuentra en la bdd
     */
    public int maxId(){
        int maxId = 0;
        if(artWorks.size() > 0)
            for (ArtWork artWork : artWorks){
                if(maxId < artWork.getId())
                    maxId = artWork.getId();
            }

        return maxId;
    }
}
