package com.example.eclip.examen_11_12_2017.main.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eclip.examen_11_12_2017.R;
import com.example.eclip.examen_11_12_2017.main.model.Mensaje;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMensajesAdapter extends BaseAdapter {

    private List<Mensaje> mData;
    private final LayoutInflater mLayoutInflater;
    private Context context;

    public ListMensajesAdapter(Context context, ArrayList<Mensaje> data) {
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.mensaje, parent, false);
            viewHolder = onCreateViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
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

    public void setData(List<Mensaje> newData) {
        this.mData = newData;
        this.notifyDataSetChanged();
    }

    private ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    private void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    class ViewHolder {
        @BindView(R.id.imgFavorito)
        ImageView imgFavorito;
        @BindView(R.id.lblHora)
        TextView lblHora;
        @BindView(R.id.lblMensaje)
        TextView lblMensaje;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bind(final Mensaje mensaje) {
            if(mensaje.isFavorito())
                imgFavorito.setVisibility(View.VISIBLE);
            else
                imgFavorito.setVisibility(View.INVISIBLE);
            lblMensaje.setText(mensaje.getMensaje());
            lblHora.setText(mensaje.getHora());
        }
    }

}
