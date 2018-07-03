package com.example.eclip.app13_pruebaactionmode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User list screen adapter
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

class Adaptador extends BaseAdapter {

    private List<User> mData;
    private final LayoutInflater mLayoutInflater;
    private Context context;

    public Adaptador(Context context, @NonNull ArrayList<User> data) {
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.user, parent, false);
            viewHolder = onCreateViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    private ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<User> newData) {
        this.mData = newData;
        this.notifyDataSetChanged();
    }

    private void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    class ViewHolder {
        @BindView(R.id.lblNombre)
        TextView lblNombre;

        ViewHolder(View view) {ButterKnife.bind(this, view);}

        //Los onClick hay que hacerlos aqui porq necesitan el user, y haciendo el onclick fuera con
        //butterkanife no podria obtener el usuario a no ser que lo declararamos una variable del adaptador
        //y cada vez que se haga click en un item actualizarlo.
        public void bind(final User user) {
            lblNombre.setText(user.getNombre());
        }

        @OnClick(R.id.lblNombre)
        void onclicklblnombre(){
            Toast.makeText(context, "Se hace click",Toast.LENGTH_SHORT).show();
        }
    }
}
