package com.example.eclip.app14_galeria.model.artWorks.bdd;

import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;

import java.util.List;

/**
 * Created by eclip on 24/11/2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public interface RepositoryArtWork {
    List<ArtWork> getArtWork();
}
