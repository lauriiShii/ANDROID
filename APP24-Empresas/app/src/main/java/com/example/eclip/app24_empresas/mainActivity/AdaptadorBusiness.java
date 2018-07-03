package com.example.eclip.app24_empresas.mainActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.eclip.app24_empresas.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by eclip on 13/02/2018.
 */

public class AdaptadorBusiness extends RecyclerView.Adapter<AdaptadorBusiness.ViewHolder> {

    private ArrayList<String> items; // Item's collection

    /***
     * Constructor
     * @param items Item's coleection
     */
    public AdaptadorBusiness(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.company, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        int num = 0;
        if(!items.equals(null))
            num = items.size();
        return num;
    }

    /***
     * Class ViewHolder that contais the layout of each row
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgCompany)
        CircleImageView imgCompany;
        @BindView(R.id.lblName)
        TextView lblName;

        /***
         * Constructor
         * @param view
         */
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /***
         * Setups the row with the item's info
         * @param item
         */
        public void bind(final String item) {
            String l = item;
            lblName.setText(item);
        }
    }
}
