package com.android.lauracalvente.app10_gatitos.selectAvatarActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.android.lauracalvente.app10_gatitos.utils.Constantes;
import com.android.lauracalvente.app10_gatitos.R;
import com.android.lauracalvente.app10_gatitos.bdd.cats.ColectionCat;
import com.android.lauracalvente.app10_gatitos.profileActivity.ProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 *
 * Activity that shows the possible cats to use as an avatar
 *
 * The cats are represented with a gridview, when you put an item you return to the
 * parent activity that receives a cat for extra, this establishes it as an avatar image
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class SelectAvatarActivity extends AppCompatActivity {

    @BindView(R.id.grtCats)
    GridView grtCats;

    AdaptaderGridViewCat adapter; //adapter for grtCats (GridView)

    /**
     * OnCreate: It is responsible for inflating the views of the "activity_select_avatar" layout
     * call "inflateItemsCats"
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        ButterKnife.bind(this);
        inflateItemsCats();
    }

    /**
     * InflateItemsCats: initializes the adapter and the adapter is associated with
     * the listView
     */
    private void inflateItemsCats() {
        adapter = new AdaptaderGridViewCat(this, ColectionCat.getCats());
        grtCats.setAdapter(adapter);
    }

    /**
     * StartForResul: tmethod called from the Profile Activity that is responsible for initiating
     * the current activity with an attempt
     * @param activity where it is called
     * @param requestCode of the intent
     */
    public static void startForResult(ProfileActivity activity, int requestCode) {
        Intent intent = new Intent(activity, SelectAvatarActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * CreateResult: returns an extra cat with the selected avatar information
     * @param position indicates which cat is exactly the one you have selected
     */
    private void createResult(int position) {
        Intent result = new Intent();
        result.putExtra(Constantes.OBJET_CAT, ColectionCat.bdd.get(position));
        this.setResult(RESULT_OK, result);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Up == Back in order not to create a new instance of ProfileActivity when going up.
        onBackPressed();
        return true;
    }

    /**
     * OnItemClick: ends this activity by returning an extra with the data of the selected avatar
     * @param position CreateResult" needs the position to know which cat is the selected one
     */
    @OnItemClick(R.id.grtCats)
    void onItemClick(int position) {
        createResult(position);
        finish();
    }

}
