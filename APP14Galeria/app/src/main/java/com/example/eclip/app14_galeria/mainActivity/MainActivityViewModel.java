package com.example.eclip.app14_galeria.mainActivity;

import android.arch.lifecycle.ViewModel;

import com.example.eclip.app14_galeria.model.artWorks.bdd.RepositoryArtWork;
import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;

import java.util.ArrayList;

import static com.example.eclip.app14_galeria.utils.Constantes.NO_ITEM_SELECTED;

/**
 * Created by eclip on 25/11/2017.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModel extends ViewModel {

    private ArrayList<ArtWork> data;
    private final RepositoryArtWork repository;
    private int selectedItem = NO_ITEM_SELECTED;

    public MainActivityViewModel(RepositoryArtWork repository) {this.repository = repository;}

    public ArrayList<ArtWork> getData() {
        if (data == null) {
            data = (ArrayList<ArtWork>) repository.getArtWork();
        }
        return data;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }
}