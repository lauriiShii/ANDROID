package com.example.eclip.app25_fct.mainActivity.Students;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.eclip.app25_fct.R;
import com.example.eclip.app25_fct.bdd.local.BD;
import com.example.eclip.app25_fct.bdd.model.Student;
import com.example.eclip.app25_fct.bdd.model.Visit;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryStudent;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryStudentImpl;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisit;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisitImpl;
import com.example.eclip.app25_fct.databinding.StudentBinding;
import com.example.eclip.app25_fct.mainActivity.MainActivity;
import com.example.eclip.app25_fct.utils.ImgUtils;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.eclip.app25_fct.utils.FragmentUtils.openStudentView;

/**
 * Created by eclip on 13/02/2018.
 */

public class AdaptadorStudent extends RecyclerView.Adapter<AdaptadorStudent.ViewHolder> {

    // list of items to show in the RecyclerView
    private ArrayList<Student> items;
    // MainActivity context
    Context mContext;
    // Fragment Manager
    private FragmentManager mManager;
    private RepositoryStudent repositoryStudent;
    private RepositoryVisit repositoryVisit;
    Student studentDelete;

    /***
     * Builder
     * @param items
     */
    public AdaptadorStudent(ArrayList<Student> items, Context c) {
        repositoryStudent = RepositoryStudentImpl.getInstance(BD.getInstance(mContext).getDb());
        repositoryVisit = RepositoryVisitImpl.getInstance(BD.getInstance(mContext).getDb());
        mContext = c;
        this.items = items;
    }

    @Override
    public AdaptadorStudent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StudentBinding itemBinding = StudentBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new AdaptadorStudent.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(AdaptadorStudent.ViewHolder holder, int position) {
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

    // Elimina un elemento de la lista.
    public void removeItem(int position, View view) {
        Student user = items.get(position);
        ArrayList<Visit> visits = (ArrayList<Visit>) repositoryVisit.loadAllVisitNotLiveData(user.getId());
        repositoryStudent.delete( items.get(position));
        for(int i = 0; i < visits.size(); i++){
            repositoryVisit.delete(visits.get(i));
        }
        notifyDataSetChanged();
        Snackbar bar = Snackbar.make(view, mContext.getString(R.string.message), Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        repositoryStudent.insert(user);
                        for(int i = 0; i < visits.size(); i++){
                            repositoryVisit.insert(visits.get(i));
                        }
                        notifyDataSetChanged();
                    }
                });
        bar.show();
    }

    // Intercambia dos elementos de la lista.
    public void swapItems(int from, int to) {
        // Se realiza el intercambio.
        Collections.swap(items, from, to);
        // Se notifica el movimiento.
        notifyItemMoved(from, to);
    }

    /***
     * Class ViewHolder that contais the layout of each row
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Class that is responsible for managing the dataBinding between the XML
        // Company and this RecycleView adapter
        private final StudentBinding binding;
        // Company whose name and image are displayed using dataBinding
        private Student mStudent;

        /**
         * Builder
         * @param binding
         */
        ViewHolder(StudentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /***
         * Setups the row with the item's info
         * @param item
         */
        public void bind(final Student item) {
            mStudent = item;
            binding.setStudent(item);
            binding.setAs(this);
            ImgUtils.loadImg(mContext, binding.imgStudent, item.getName());
            binding.executePendingBindings();
        }

        /**
         * Method called from the XML with dataBinding that is responsible
         * for opening the DetailActivity loading the data of the company
         * on which the user has clicked.
         * @param view
         */
        public void onClickStudentListener(View view) {
            openStudentView(mContext, mStudent);
        }
    }
}
