package com.example.eclip.app20_shop_viewpage.shoppingCarActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.eclip.app20_shop_viewpage.R;
import com.example.eclip.app20_shop_viewpage.bdd.Cest.DataBaseItemsCest;
import com.example.eclip.app20_shop_viewpage.bdd.Cest.RepositoryImpItemsCart;
import com.example.eclip.app20_shop_viewpage.bdd.Cest.RepositoryItemsCart;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eclip on 19/01/2018.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private ArrayList<Item> items; // Item's collection

    /***
     * Constructor
     * @param items Item's coleection
     */
    public ShoppingCartAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_car, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /***
     * Class ViewHolder that contais the layout of each row
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lblNameItem)
        TextView lblNameItem;
        @BindView(R.id.btnSum)
        Button btnSum;
        @BindView(R.id.lblCantItem)
        TextView lblCantItem;
        @BindView(R.id.btnRes)
        Button btnRes;
        @BindView(R.id.lblPrice)
        TextView lblPrice;

        private int amount; // Amount of units
        private float price; // Price of each unit
        private RepositoryItemsCart mRepository; // Shopping Cart's collection

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
        public void bind(final Item item) {
            mRepository = RepositoryImpItemsCart.getInstance(DataBaseItemsCest.getInstance());
            amount = 0;
            price = 0;

            for (int i = 0; i < mRepository.getItemsCest().size(); i++) {
                if (mRepository.getItemsCest().get(i).getName().equals(item.getName())) {
                    amount++;
                    price += mRepository.getItemsCest().get(i).getPrice();
                }
            }
            lblNameItem.setText(item.getName());
            lblCantItem.setText(String.format("%s", amount));
            lblPrice.setText(String.format("%.2f € ", price));

            btnRes.setOnClickListener(view -> deleteItem(item));
            btnSum.setOnClickListener(view -> sumItem(item));
        }

        /***
         * Decrease the amount of units for this item
         * @param item
         */
        private void deleteItem(Item item){
            mRepository.deleteItem(item);
            loadView();
        }

        /***
         * Increase the amount of unit for this item
         * @param item
         */
        private void sumItem(Item item){
            mRepository.addItem(item);
            loadView();
        }

        /***
         * Load the view
         */
        private void loadView(){
            ShoppingCarActivity.CheckFab();
            ShoppingCarActivity.calculatePrice();
            notifyDataSetChanged();
        }
    }
}