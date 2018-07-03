package com.android.lauracalvente.app4_poketri.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.lauracalvente.app4_poketri.Fragment.scoresFragment;
import com.android.lauracalvente.app4_poketri.Pojos.Player;
import com.android.lauracalvente.app4_poketri.R;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterListView extends ArrayAdapter<Player> {

    ArrayList<Player> players;
    Typeface fuente;

    //Constructor
    public MyAdapterListView(Context contexto, ArrayList<Player> players, Typeface fuente) {
        super(contexto, R.layout.fila, players);
        this.players = players;
        this.fuente = fuente;
    }

    //Clase estatica contenedora de vista de fila
    static class ViewHolder {
        @BindView(R.id.lblName)
        TextView lblName;
        @BindView(R.id.lblScore)
        TextView lblScore;
        @BindView(R.id.lblTime)
        TextView lblTime;

        ViewHolder(View itemView, Typeface fuente) {
            ButterKnife.bind(this, itemView);

            lblName.setTypeface(fuente);
            lblScore.setTypeface(fuente);
            lblTime.setTypeface(fuente);
        }

        public TextView getLblNombre() {
            return lblName;
        }
        public TextView getLblPuntuacion() {
            return lblScore;
        }
        public TextView getLblFecha() {
            return lblTime;
        }
    }

    //Metodo que construye y retorna la vista a usar para mostar una fila- elemento de la lista
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fila, parent, false);
            holder = new ViewHolder(convertView, fuente);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        onBindViewHolder(holder, position);

        return convertView;
    }

    // Cuando se deben escribir los datos en la vista del elemento.
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getLblNombre().setText(players.get(position).name);
        holder.getLblPuntuacion().setText(players.get(position).score);
        holder.getLblFecha().setText(players.get(position).time);
    }

    public ArrayList<Player> getListaJugadores() {
        return players;
    }


}

