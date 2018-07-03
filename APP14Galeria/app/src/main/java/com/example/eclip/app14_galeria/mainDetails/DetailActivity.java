package com.example.eclip.app14_galeria.mainDetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eclip.app14_galeria.R;
import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;
import com.example.eclip.app14_galeria.utils.ConfigurationUtils;
import com.example.eclip.app14_galeria.utils.FragmentUtils;

import static com.example.eclip.app14_galeria.utils.Constantes.EXTRA_ITEM;
import static com.example.eclip.app14_galeria.utils.Constantes.EXTRA_POSITION;
import static com.example.eclip.app14_galeria.utils.Constantes.TAG_DETAIL_FRAGMENT;

/**
 *
 * This activity loads the detailFragment only when the orientation is vertical,
 * for it receives a work through an intent, it is read with parcelable and we
 * load it in the corresponding views of the layout.
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class DetailActivity extends AppCompatActivity implements DetailFragment.Callback {

    /***
     * onCreate: load the fragment details
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details);
        loadFragmentDetail();
    }

    /***
     * loadFragmentDetail: if we are in the vertical orientation we load the fragment details in
     * this activity, the fragment must show the work of art that receives by extra in the intent,
     * for it it is used parcelable.
     */
    private void loadFragmentDetail(){
        if (ConfigurationUtils.hasLandscapeOrientation(this)) {
            // Not posible in landscape orientation.
            finish();
        } else {
            if (getSupportFragmentManager().findFragmentById(R.id.frgDetail) == null) {
                FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.frgDetail,
                        DetailFragment.newInstance(this, (ArtWork) getIntent().getParcelableExtra(EXTRA_ITEM), getIntent().getIntExtra(EXTRA_POSITION, 0)), TAG_DETAIL_FRAGMENT);
            }
        }
    }

    /***
     * onDetailShown: It does not load because in this activity it does not have
     * function, this method only loads in the activity Princiapal
     * @param position
     */
    @Override
    public void onDetailShown(int position) {
        // Do nothing.
    }

    /**
     * onSupportNavigateUp: when you press back it works like any activity
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /***
     * start: method that is called when we want to create a new instance of
     * this activity so that this activity can receive the work and its position as an extra
     * @param context
     * @param item
     * @param position
     */
    public static void start(Context context, ArtWork item, int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_ITEM, item);
        intent.putExtra(EXTRA_POSITION, position);
        context.startActivity(intent);
    }
}
