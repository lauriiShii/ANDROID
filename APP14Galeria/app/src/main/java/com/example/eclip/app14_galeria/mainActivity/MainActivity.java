package com.example.eclip.app14_galeria.mainActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.eclip.app14_galeria.R;
import com.example.eclip.app14_galeria.mainDetails.DetailActivity;
import com.example.eclip.app14_galeria.mainDetails.DetailFragment;
import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;
import com.example.eclip.app14_galeria.utils.ConfigurationUtils;
import com.example.eclip.app14_galeria.utils.FragmentUtils;

import static com.example.eclip.app14_galeria.utils.Constantes.NO_ITEM_SELECTED;
import static com.example.eclip.app14_galeria.utils.Constantes.TAG_DETAIL_FRAGMENT;
import static com.example.eclip.app14_galeria.utils.Constantes.TAG_MAIN_FRAGMENT;

/**
 *
 * This activity contains the two fragments in charge of showing all the views of
 * the application and of applying all the logic, for this reason you must implement
 * the calback of both PrincipalFragment and DatailFragment.
 *
 * When this horizontal shows the two fragments, when this vertical only shows the
 * main one and if it needs it, it shows a daughter activity.
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements PrincipalFragment.Callback,
        DetailFragment.Callback {

    /***
     * onCreate: we load the fragments
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragments();
    }

    /***
     * loadFragments: if the main fragment does not exist, we create it and instantiate it.
     *
     * if the position of the screen is horizontal then we check if there is a fragment of details,
     * if it does not exist, we create it to which we pass an empty work of art in this case.
     */
    private void loadFragments() {
        if (getSupportFragmentManager().findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.frgPrincipal, PrincipalFragment.newInstance(this), TAG_MAIN_FRAGMENT);
        }
        if (ConfigurationUtils.hasLandscapeOrientation(this)) {
            if (getSupportFragmentManager().findFragmentByTag(TAG_DETAIL_FRAGMENT) == null) {
                FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.frgDetail, DetailFragment.newInstance(this, new ArtWork(), NO_ITEM_SELECTED), TAG_MAIN_FRAGMENT);
            }
        }
    }

    /***
     * onItemSelected: if the orientation of the screen is horizontal we replace the fragment of details.
     *
     * It is an important detail to replace it because it is what will allow us to reload the previously
     * selected work when the backpressed is pressed.
     *
     * If the orientation is vertical we open a new activity that will load the fragment details in this
     * way we can not use the backpressed to load the previous work.
     *
     * @param item work that was clicked
     * @param position of the work that was clicked within the listView of the princiapal fragment
     */
    @Override
    public void onItemSelected(ArtWork item, int position) {
        if (ConfigurationUtils.hasLandscapeOrientation(this)) {
            FragmentUtils.replaceFragmentAddToBackstack(getSupportFragmentManager(), R.id.frgDetail,
                    DetailFragment.newInstance(this, item, position), TAG_DETAIL_FRAGMENT, item,
                    FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        } else {
            DetailActivity.start(this, item, position);
        }
    }

    /***
     * onDetailShown: method implemented in this activity by CALLBACK. Thanks to this we can load the work shown
     * earlier than the one currently shown, always speaking horizontally.
     *
     * The method selectItem that contains the Principal fragment is called and this method
     * is responsible for updating the entire interface, when we run out of work to load we exit the app
     *
     * @param position
     */
    @Override
    public void onDetailShown(int position) {
        PrincipalFragment mainFragment = (PrincipalFragment) getSupportFragmentManager().findFragmentById(
                R.id.frgPrincipal);
        mainFragment.selectItem(position);
    }

    /***
     * onBackPressed: when we press back, if we are in the vertical orientation the previous work is not loaded
     */
    @Override
    public void onBackPressed() {
        if (!ConfigurationUtils.hasLandscapeOrientation(this)) {
            getSupportFragmentManager().popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        super.onBackPressed();
    }
}