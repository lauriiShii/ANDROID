package com.example.eclip.periodico.mainActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eclip.periodico.R;
import com.example.eclip.periodico.Utils.ImgUtils;
import com.example.eclip.periodico.Utils.IntentUtils;
import com.example.eclip.periodico.Utils.ValidationUtils;
import com.example.eclip.periodico.bdd.local.BD;
import com.example.eclip.periodico.bdd.model.Periodico;
import com.example.eclip.periodico.bdd.repository.RepositoryPeriodico;
import com.example.eclip.periodico.bdd.repository.RepositoryPeriodicoImpl;
import com.example.eclip.periodico.databinding.PeriodicoBinding;

import java.util.ArrayList;

/**
 * Created by eclip on 06/03/2018.
 */

public class PeriodicoAdapter extends RecyclerView.Adapter<PeriodicoAdapter.ViewHolder> {

    private ArrayList<Periodico> items;
    AppCompatActivity mContext;
    private FragmentManager mManager;
    private RepositoryPeriodico mRepository;
    private Periodico auxPeriodico;
    private int auxPosition;
    SharedPreferences preferencias;
    boolean borrar;
    boolean noCero;

    public PeriodicoAdapter(ArrayList<Periodico> items, AppCompatActivity c) {
        mRepository = RepositoryPeriodicoImpl.getInstance(BD.getInstance(mContext).getDb());
        mContext = c;
        this.items = items;
        setupPreferene();
    }

    // Se obtienen los valores de la hoja de preferencias
    public void setupPreferene(){
        preferencias = PreferenceManager.getDefaultSharedPreferences(mContext);
        borrar = preferencias.getBoolean(mContext.getString(R.string.pref_borrar), true);
        noCero = preferencias.getBoolean(mContext.getString(R.string.pref_espacio), true);
    }

    @Override
    public PeriodicoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PeriodicoBinding itemBinding = PeriodicoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new PeriodicoAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(PeriodicoAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        int num = 0;
        if (items != null)
            num = items.size();
        return num;
    }

    // Accion que se realiza con el dismiss
    public void removeItem(int position, View view) {
        auxPeriodico = items.get(position);
        auxPosition = position;
        mRepository.delete(items.get(position));
        notifyDataSetChanged();
        if(borrar) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(R.string.eliminar_mensaje)
                    .setNegativeButton("NO", ((dialog, id1) -> {
                        mRepository.insert(auxPeriodico);
                        notifyItemChanged(auxPosition);
                    }))
                    .setPositiveButton("SI", (dialog, id2) -> {
                        // Closes the dialog
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void swapItems(int from, int to) {
    }

    // Clase interna ViewHolder necesaria para mostrar los items
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final PeriodicoBinding binding;
        private Periodico mPeriodico;

        ViewHolder(PeriodicoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Configuración de los datos a mostrar por pantalla en cada item
        public void bind(final Periodico item) {
            mPeriodico = item;
            binding.setPeriodico(item);
            binding.setAs(this);
            //Se decide si se muestra espacios o 0
            if(noCero && binding.getPeriodico().getVisitas() == 0)
                ImgUtils.loadImg(mContext, binding.imgContador, " ");
            else
                ImgUtils.loadImg(mContext, binding.imgContador, String.format("%s",item.getVisitas()));
            //Mostramos imagen del nombre
            ImgUtils.loadImgName(mContext, binding.lblName, mPeriodico.getName());
            binding.executePendingBindings();
        }

        // Cuando se hace click en el item se añade uno al contador de visitas y se lanza el intent
        public void onClickListener(View view) {
            binding.getPeriodico().setVisitas(binding.getPeriodico().getVisitas()+1);
            Periodico aux = binding.getPeriodico();
            mRepository.insert(aux);
            putIntent(ValidationUtils.isValidUrl(mPeriodico.getUrl()), IntentUtils.newWebSearchIntent(mPeriodico.getUrl()));
        }

        // Se encarga de abrir el navegador con la URL del periodico si es posible
        private void putIntent(Boolean valid, Intent intent) {
            if (valid) {
                Intent i = intent;
                if (IntentUtils.isActivityAvailable(mContext.getApplicationContext(), i))
                    mContext.startActivity(i);
            }
            else
                Toast.makeText(mContext, R.string.no_web, Toast.LENGTH_SHORT).show();
        }
    }
}
