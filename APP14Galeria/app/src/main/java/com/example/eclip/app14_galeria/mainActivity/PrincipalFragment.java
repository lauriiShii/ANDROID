package com.example.eclip.app14_galeria.mainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.eclip.app14_galeria.R;
import com.example.eclip.app14_galeria.model.artWorks.bdd.DataBaseArtWork;
import com.example.eclip.app14_galeria.model.artWorks.bdd.RepositoryArtWork;
import com.example.eclip.app14_galeria.model.artWorks.bdd.RepositoryImpArtWork;
import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;
import com.example.eclip.app14_galeria.utils.ConfigurationUtils;
import com.example.eclip.app14_galeria.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;

import static com.example.eclip.app14_galeria.utils.Constantes.HTTP_NO_VALID;
import static com.example.eclip.app14_galeria.utils.Constantes.STATE_SELECTED_ITEM;

/**
 *
 * fragment in charge of showing the demo image and the list containing the works of art
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class PrincipalFragment extends Fragment {

    private AdapterArtWork mAdapter; // adapter that gives aesthetics to the listView
    private static Context context; // context that loads when instantiating this fragment of the activity
    private Callback mListener; // listener who listens to the callback call from the activity
    private MainActivityViewModel mViewModel; // viewModel that will provide us with data and certain data of the workArts
    private RepositoryArtWork mRepositoryArtWork; // WorkArts list

    @BindView(R.id.imgDemo)
    ImageView imgDemo;
    @BindView(R.id.lstArtWorks)
    ListView lstArtWorks;
    Unbinder unbinder;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /***
     * Callback: interface that is implemented in the activity that instans this fragment
     */
    public interface Callback {
        void onItemSelected(ArtWork item, int position);
    }

    /***
     * onCreate: create the fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    /***
     * onCreateView: inflates layout views
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /***
     * onAttach: If the callback methods have not been implemented when instantiating
     * the fragment, they should be implemented
     * @param activity
     */
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (Callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + getString(R.string.no_CallBack));
        }
    }

    /***
     * onDetach: finishes the creation of the fragment
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /***
     * onActivityCreated: Create the fragment
     *
     * The viewModel is created that will maintain our data and manage part of the selection of listView items.
     *
     * We recover the viewModel before the change if it exists
     *
     * We inflate the listView with the adapter
     *
     * We load the viewModel if there is a selected item
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createViewModel();
        restoreInstanceState(savedInstanceState);
        inflateItemsUsers();
        loadViewModel();  // solo si hay un item seleccionado
    }

    /***
     * loadViewModel: if there is an item selected when loading the viewModel this will be displayed
     */
    private void loadViewModel(){
        // If item selected.
        if (mViewModel.getSelectedItem() >= 0) {
            if (ConfigurationUtils.hasLandscapeOrientation(getActivity())) {
                showItem(mViewModel.getSelectedItem());
            } else {
                selectItem(mViewModel.getSelectedItem());
            }
        }
    }

    /***
     * createViewModel: we obtain the data from the database with the mRepository and load
     * it into the ViewModel at the same time that we create it
     */
    private void createViewModel (){
        mRepositoryArtWork = RepositoryImpArtWork.getInstance(DataBaseArtWork.getInstance());
        mViewModel = ViewModelProviders.of(this, new MainActivityViewModelFactory(mRepositoryArtWork)).get(
                MainActivityViewModel.class);
    }

    /***
     * inflateItemsUsers: inflates the view of the listView with an adapter
     */
    private void inflateItemsUsers() {
        mAdapter = new AdapterArtWork(context, mViewModel.getData());
        lstArtWorks.setAdapter(mAdapter);
    }

    /***
     * onViewClicked: when you click on an item the image is loaded and the new fragment detail
     * @param position
     */
    @OnItemClick(R.id.lstArtWorks)
    public void onViewClicked(int position) {
        //Mostramos imgen grande
        showItem(position);
    }

    /***
     * restoreInstanceState: store the selected item
     * @param savedInstanceState
     */
    private void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mViewModel.setSelectedItem(savedInstanceState.getInt(STATE_SELECTED_ITEM));
        }
    }

    /***
     * showItem: select the item with what that entails when using selectItem that is
     * defined later and call onItemSelected that is implemented in the activity.
     * @param position
     */
    private void showItem(int position) {
        selectItem(position);
        mListener.onItemSelected((ArtWork) lstArtWorks.getItemAtPosition(position), position);
    }

    /**
     * selectItem: if there is a selected position when the fragment is loaded, it is selected
     * in the listView and the corresponding image is loaded. If not, the default image is loaded
     * and all possible selections of the listView are deleted
     * @param position
     */
    public void selectItem(int position) {
        if (position >= 0) {
            lstArtWorks.setItemChecked(position, true);
            lstArtWorks.setSelection(position);
            //Cargamos la demo de la imagen
            ImageUtils.loadImg(context, imgDemo, mViewModel.getData().get(position).getImage());
        } else {
            ImageUtils.loadImg(context, imgDemo, HTTP_NO_VALID);
            lstArtWorks.setItemChecked(mViewModel.getSelectedItem(), false);
            lstArtWorks.clearChoices();
        }
        mViewModel.setSelectedItem(position);
    }

    /***
     * onSaveInstanceState: if the activity is destroyed we recover the item that was selected
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_ITEM, mViewModel.getSelectedItem());
    }

    /***
     * newInstance: create an instance of this fragment. Stores the context of the activity that calls
     * @param c
     * @return
     */
    public static PrincipalFragment newInstance(Context c) {
        context = c;
        return new PrincipalFragment();
    }
}