package com.android.lauracalvente.app10_gatitos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.android.lauracalvente.app10_gatitos.Adapter.AdaptaderGridViewCat;
import com.android.lauracalvente.app10_gatitos.BDD.Colection;
import com.android.lauracalvente.app10_gatitos.Utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class SelectAvatarActivity extends AppCompatActivity {

    @BindView(R.id.grtCats)
    GridView grtCats;
    AdaptaderGridViewCat adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        ButterKnife.bind(this);
        inflateItemsCats();
    }

    //Inflate gridview the cats
    private void inflateItemsCats() {
        adaptador = new AdaptaderGridViewCat(this, Colection.getCats());
        grtCats.setAdapter(adaptador);
    }

    //The call comes to this activity from the main activity
    public static void startForResult(MainActivity activity,int requestCode) {
        Intent intent = new Intent(activity, SelectAvatarActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    //we return the selected cat in the gridView
    private void createResult(int position) {
        Intent result = new Intent();
        result.putExtra(String.format("%s", Constantes.CAT), Colection.bdd.get(position));
        this.setResult(RESULT_OK, result);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Up == Back in order not to create a new instance of MainActivity when going up.
        onBackPressed();
        return true;
    }

    //when selecting an avatar we return to the main activity
    @OnItemClick(R.id.grtCats)
    void onItemClick(int position) {
        createResult(position);
        changeSeleted(position);
        finish();
    }

    private void changeSeleted(int position) {
        for(int i = 0; Colection.bdd.size() > i ; i++) {
            if (i == position)
                Colection.bdd.get(i).setSelect(true);
            else
                Colection.bdd.get(i).setSelect(false);
        }
    }
}
