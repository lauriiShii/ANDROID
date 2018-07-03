package com.example.eclip.app14_galeria.model.artWorks.bdd;

import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;

import java.util.List;

/**
 * Created by eclip on 24/11/2017.
 */

public class RepositoryImpArtWork implements RepositoryArtWork{

    private static RepositoryImpArtWork instance;
    private static DataBaseArtWork database;

    private RepositoryImpArtWork(DataBaseArtWork database) {
        this.database = database;
    }

    public static RepositoryImpArtWork getInstance(DataBaseArtWork database) {
        if (instance == null) {
            instance = new RepositoryImpArtWork(database);
        }
        return instance;
    }

    @Override
    public List<ArtWork> getArtWork() {
        return database.getArtWorks();
    }
}
