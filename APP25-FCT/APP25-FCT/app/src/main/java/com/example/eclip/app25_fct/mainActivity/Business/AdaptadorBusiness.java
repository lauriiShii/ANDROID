package com.example.eclip.app25_fct.mainActivity.Business;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.eclip.app25_fct.R;
import com.example.eclip.app25_fct.bdd.model.Company;
import com.example.eclip.app25_fct.databinding.CompanyBinding;
import com.example.eclip.app25_fct.mainActivity.MainActivity;
import com.example.eclip.app25_fct.utils.Constantes;
import com.example.eclip.app25_fct.utils.FragmentUtils;
import com.example.eclip.app25_fct.utils.ImgUtils;

import java.util.ArrayList;

import static com.example.eclip.app25_fct.utils.FragmentUtils.openCompanyDetail;

/**
 * Created by eclip on 13/02/2018.
 */

public class AdaptadorBusiness extends RecyclerView.Adapter<AdaptadorBusiness.ViewHolder> {

    // Lista de Objetos a mostrar, en este caso compañias
    private ArrayList<Company> items;
    // Contexto de la actividad padre, en este caso MainActivity
    Context mContext;
    // Gestor de fragmentos
    FragmentManager mManager;

    /***
     * Constructor, recibe la lista de items y el contexto.
     * @param items
     */
    public AdaptadorBusiness(ArrayList<Company> items, Context c) {
        mContext = c;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         CompanyBinding itemBinding = CompanyBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        int num = 0;
        // Es curioso, si ejecutas en el movil debe ser asi ...
        // Si ejecutas en el emulador debe ser !items.equals(null)
        if (items != null)
            num = items.size();
        return num;
    }

    /***
     * Clase interna ViewHolder.
     * Esta da la información a cada item del recycleView.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Class that is responsible for managing the dataBinding between the XML
        // Company and this RecycleView adapter
        private final CompanyBinding binding;
        // Company whose name and image are displayed using dataBinding
        private Company mCompany;

        /**
         * Constructor que recibe el dataBinding para poder utilizarlo con el XML
         * @param binding
         */
        ViewHolder(CompanyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /***
         * Se define la información de cada item de la lista.
         * @param item
         */
        public void bind(final Company item) {
            mCompany = item;
            binding.setCompany(item);
            binding.setAb(this);
            ImgUtils.loadImg(mContext, binding.imgCompany, item.getPicture(), item.getName());
            binding.executePendingBindings();
        }

        /**
         * Método llamado desde el XML, este se ejecuta cuando se hace click en el item de la lista
         * y llama a un método encargado de abrir los detalles de la compañia.
         * @param view
         */
        public void onClickCompanyListener(View view) {
            openCompanyDetail(mContext, mCompany);
        }
    }
}
