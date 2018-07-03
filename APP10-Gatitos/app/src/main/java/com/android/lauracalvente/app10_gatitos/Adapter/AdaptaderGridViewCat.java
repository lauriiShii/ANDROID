package com.android.lauracalvente.app10_gatitos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import com.android.lauracalvente.app10_gatitos.BDD.Colection;
import com.android.lauracalvente.app10_gatitos.Model.Pojos.Cat;
import com.android.lauracalvente.app10_gatitos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Laura on 13/12/2016.
 */

public class AdaptaderGridViewCat extends ArrayAdapter<Cat> {

    public ArrayList<Cat> Cats;

    public AdaptaderGridViewCat(Context context, ArrayList<Cat> personajes){
        super(context, R.layout.cat, personajes);
        this.Cats = personajes;
    }

    static class ViewHolder {
        @BindView(R.id.imgCat)
        ImageView imgCat;
        @BindView(R.id.nameCat)
        TextView nameCat;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public ImageView getImgCat() {
            return imgCat;
        }
        public TextView getNameCat() {
            return nameCat;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cat, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        onBindViewHolder(holder, position);
        return convertView;
    }

    private void onBindViewHolder(ViewHolder holder, int position){
        holder.getImgCat().setImageResource(Cats.get(position).getSrc());
        if(Cats.get(position).getSelect())
            holder.getImgCat().setAlpha(0.5f);
        holder.getNameCat().setText(Cats.get(position).getName());
    }

    public ArrayList<Cat> getCats(){
        return Cats;
    }

}
