package com.android.lauracalvente.app10_gatitos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lauracalvente.app10_gatitos.R;
import com.android.lauracalvente.app10_gatitos.model.pojos.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * User list screen adapter
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class AdapterListViewUsers extends ArrayAdapter<User> {

    public ArrayList<User> users;

    public AdapterListViewUsers(Context context, ArrayList<User> users) {
        super(context, R.layout.user, users);
        this.users = users;
    }

    static class ViewHolder {
        @BindView(R.id.imgProfile)
        ImageView imgProfile;
        @BindView(R.id.lblName)
        TextView lblName;
        @BindView(R.id.lblEmail)
        TextView lblEmail;
        @BindView(R.id.lblPhone)
        TextView lblPhone;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public ImageView getImgProfile() {return imgProfile;}
        public TextView getLblName() {return lblName;}
        public TextView getLblEmail() {return lblEmail;}
        public TextView getLblPhone() {return lblPhone;}
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user, parent, false);
            holder = new AdapterListViewUsers.ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (AdapterListViewUsers.ViewHolder) convertView.getTag();

        onBindViewHolder(holder, position);
        return convertView;
    }

    private void onBindViewHolder(AdapterListViewUsers.ViewHolder holder, int position) {
        holder.getImgProfile().setImageResource(users.get(position).getCat().getSrc());
        holder.getLblName().setText(users.get(position).getName());
        holder.getLblEmail().setText(users.get(position).getEmail());
        holder.getLblPhone().setText(users.get(position).getPhone());
    }


}
