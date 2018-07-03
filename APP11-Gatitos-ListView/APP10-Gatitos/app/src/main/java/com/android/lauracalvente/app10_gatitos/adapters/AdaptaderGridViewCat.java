package com.android.lauracalvente.app10_gatitos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import com.android.lauracalvente.app10_gatitos.R;
import com.android.lauracalvente.app10_gatitos.model.pojos.Cat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Avatar selection screen adapter
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class AdaptaderGridViewCat extends ArrayAdapter<Cat> {

    public ArrayList<Cat> cats;

    public AdaptaderGridViewCat(Context context, ArrayList<Cat> cats){
        super(context, R.layout.cat, cats);
        this.cats = cats;
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
        holder.getImgCat().setImageResource(cats.get(position).getSrc());
        if(cats.get(position).getSelect())
            holder.getImgCat().setAlpha(0.5f);
        holder.getNameCat().setText(cats.get(position).getName());
    }

}
