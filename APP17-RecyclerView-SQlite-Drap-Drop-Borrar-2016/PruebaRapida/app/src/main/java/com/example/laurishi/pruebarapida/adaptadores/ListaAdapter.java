package com.example.laurishi.pruebarapida.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laurishi.pruebarapida.R;
import com.example.laurishi.pruebarapida.base_de_datos.Dao;
import com.example.laurishi.pruebarapida.modelos.Coche;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Creado por Laura Calvente Dominguez, el 16/05/2017.
 */

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View view, Coche coche, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, Coche coche, int position);
    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private final SparseBooleanArray objetosSelecionados = new SparseBooleanArray();
    public ArrayList<Coche> datos;

    public ListaAdapter(ArrayList<Coche> datos) {
        setHasStableIds(true);
        this.datos = datos;
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Se infla la especificación XML para obtener la vista correspondiente al ítem.
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        // Se crea el contenedor de subvistas de la vista del ítem.
        final ViewHolder viewHolder = new ViewHolder(itemView);

        // Cuando se hace click sobre el elemento.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    // Se informa al listener.
                    onItemClickListener.onItemClick(view, datos.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClickListener != null) {
                    // Se informa al listener.
                    onItemLongClickListener.onItemLongClick(view,
                            datos.get(viewHolder.getAdapterPosition()),
                            viewHolder.getAdapterPosition());
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Se crea y retorna el contenedor de subvistas de la vista correspondiente al ítem.
        return viewHolder;
    }

    // Establece el listener a informar cuando se hace click sobre un ítem.
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // Establece el listener a informar cuando se hace click largo sobre un ítem.
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String id = String.format("%d", datos.get(position).id);

        context = holder.imgCoche.getContext();

        Picasso.with(context).load("http://lorempixel.com/400/300/city/").into(holder.getImgCoche());
        holder.getLblItemMarca().setText(datos.get(position).marca);
        holder.getLblItemModelo().setText(datos.get(position).modelo);
        holder.getLblItemFecha().setText(datos.get(position).fecha_matriculacion);
    }

    // Añade un elemento al adaptador.
    public void addItem(Coche coche, int position) {
        datos.add(position, coche);
        notifyItemInserted(position);
    }

    // Elimina un elemento del adaptador.
    public void remove(Coche coche) {
        Dao.getInstance(context).deleteItem(coche.id);

        int position = datos.indexOf(coche);
        datos.remove(position);

        notifyItemRemoved(position);

    }

    // Intercambia los elementos indicados.
    public void moveItem(int start, int end) {
        int max = Math.max(start, end);
        int min = Math.min(start, end);
        if (min >= 0 && max < datos.size()) {
            Coche coche = datos.remove(min);
            datos.add(max, coche);
            notifyItemMoved(min, max);
        }
    }

    @Override
    public void onItemMove(int from, int to) {
        // Se realiza el intercambio.
        Collections.swap(datos, from, to);
        // Se intercambia también el estado de selección de from y to.
        boolean fromSelected = objetosSelecionados.get(from, false);
        boolean toSelected = objetosSelecionados.get(to, false);
        if (fromSelected) {
            objetosSelecionados.delete(from);
        }
        if (toSelected) {
            objetosSelecionados.delete(to);
        }
        if (fromSelected) {
            objetosSelecionados.put(to, true);
        }
        if (toSelected) {
            objetosSelecionados.put(from, true);
        }

        notifyItemMoved(from, to);

    }

    @Override
    public void onItemDismiss(int position) {
        remove(datos.get(position));
        notifyItemRemoved(position);
    }

    // Retorna el id de un elemento. Necesario para drag & drop.
    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= datos.size()) {
            return -1;
        }
        return datos.get(position).id;
    }

    // Retorna la posición del item con dicho id. Necesario para drag & drop.
    public int getPositionForId(long id) {
        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgCoche)
        ImageView imgCoche;
        @BindView(R.id.lblItemMarca)
        TextView lblItemMarca;
        @BindView(R.id.lblItemModelo)
        TextView lblItemModelo;
        @BindView(R.id.lblItemFecha)
        TextView lblItemFecha;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }

        public ImageView getImgCoche() {
            return imgCoche;
        }

        public void setImgCoche(ImageView imgCoche) {
            this.imgCoche = imgCoche;
        }

        public TextView getLblItemMarca() {
            return lblItemMarca;
        }

        public void setLblItemMarca(TextView lblItemMarca) {
            this.lblItemMarca = lblItemMarca;
        }

        public TextView getLblItemModelo() {
            return lblItemModelo;
        }

        public void setLblItemModelo(TextView lblItemModelo) {
            this.lblItemModelo = lblItemModelo;
        }

        public TextView getLblItemFecha() {
            return lblItemFecha;
        }

        public void setLblItemFecha(TextView lblItemFecha) {
            this.lblItemFecha = lblItemFecha;
        }
    }
}
