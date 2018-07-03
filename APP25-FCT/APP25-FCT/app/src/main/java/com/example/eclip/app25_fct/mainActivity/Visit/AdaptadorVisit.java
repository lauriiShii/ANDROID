package com.example.eclip.app25_fct.mainActivity.Visit;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.eclip.app25_fct.bdd.repositories.RepositoryStudent;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryStudentImpl;
import com.example.eclip.app25_fct.bdd.local.BD;
import com.example.eclip.app25_fct.bdd.model.Visit;
import com.example.eclip.app25_fct.databinding.VisitBinding;
import com.example.eclip.app25_fct.mainActivity.MainActivity;
import com.example.eclip.app25_fct.utils.Constantes;
import com.example.eclip.app25_fct.visitActivity.VisitActivity;

import java.util.ArrayList;

import static com.example.eclip.app25_fct.utils.FragmentUtils.openStudentView;
import static com.example.eclip.app25_fct.utils.ImgUtils.loadImgVisit;

/**
 * Created by eclip on 13/02/2018.
 */

public class AdaptadorVisit extends RecyclerView.Adapter<AdaptadorVisit.ViewHolder> {

    // list of items to show in the RecyclerView
    private ArrayList<Visit> items;
    // MainActivity context
    Context mContext;
    // Fragment Manager
    FragmentManager mManager;
    RepositoryStudent mRepositoryStudent;

    /***
     * Builder
     * @param items
     */
    public AdaptadorVisit(ArrayList<Visit> items, Context c) {
        mContext = c;
        this.items = items;
        mRepositoryStudent = RepositoryStudentImpl.getInstance(BD.getInstance(mContext).getDb());
    }

    @Override
    public AdaptadorVisit.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VisitBinding itemBinding = VisitBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new AdaptadorVisit.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(AdaptadorVisit.ViewHolder holder, int position) {
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
     * Class ViewHolder that contais the layout of each row
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Class that is responsible for managing the dataBinding between the XML
        // Company and this RecycleView adapter
        private final VisitBinding binding;
        // Company whose name and image are displayed using dataBinding
        private Visit mVisit;

        /**
         * Builder
         * @param binding
         */
        ViewHolder(VisitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /***
         * Setups the row with the item's info
         * @param item
         */
        public void bind(final Visit item) {
            mVisit = item;
            binding.setVisit(item);
            binding.setVi(this);
            binding.lblName.setText(mRepositoryStudent.getNameById(mVisit.getStudent()));
            loadImgVisit(mContext, binding.imgVisit, mVisit.getObserve());
            binding.executePendingBindings();
        }

        /**
         * Method called from the XML with dataBinding that is responsible
         * for opening the DetailActivity loading the data of the company
         * on which the user has clicked.
         * @param view
         */
        public void onClickListener(View view) {
            VisitActivity.startForResult((MainActivity)mContext, Constantes.VISIT, mVisit);
        }
    }
}