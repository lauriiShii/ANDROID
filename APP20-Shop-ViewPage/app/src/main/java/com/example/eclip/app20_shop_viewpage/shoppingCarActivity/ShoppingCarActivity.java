package com.example.eclip.app20_shop_viewpage.shoppingCarActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eclip.app20_shop_viewpage.R;
import com.example.eclip.app20_shop_viewpage.bdd.Cest.DataBaseItemsCest;
import com.example.eclip.app20_shop_viewpage.bdd.Cest.RepositoryImpItemsCart;
import com.example.eclip.app20_shop_viewpage.bdd.Cest.RepositoryItemsCart;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingCarActivity extends AppCompatActivity {

    @BindView(R.id.lstItems)
    RecyclerView lstItems;
    @BindView(R.id.lblEmptyView)
    TextView lblEmptyView;


    private static TextView lblTotal; // TextView that it's static because we need to access to it's infomation from the ShoppingCartAdapter
    private static FloatingActionButton fab; // FAB that it's static because we need to access to it's infomation from the ShoppingCartAdapter

    private ShoppingCartAdapter mAdapter; // lstItems's adapter
    private LinearLayoutManager mLayoutManager; // lstItems's LayoutManager. That makes the lstItems as a ListView, and not like a GridView
    private RepositoryItemsCart mRepository; // Shopping cart items's collection
    private static ShoppingCarActivityViewModel mViewModel; // ShoppingCartActivity's ViewModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        ButterKnife.bind(this);
        fab = findViewById(R.id.fab);
        lblTotal = findViewById(R.id.lblTotal);
        createViewModel();
        setupRecyclerView();
        calculatePrice();
        lstIsEmpty();
    }

    /***
     * Creates the activity's ViewModel
     */
    private void createViewModel() {
        mRepository = RepositoryImpItemsCart.getInstance(DataBaseItemsCest.getInstance());
        mViewModel = ViewModelProviders.of(this, new ShoppingCarActivityViewModelFactory(mRepository)).get(
                ShoppingCarActivityViewModel.class);
    }

    /***
     * Setups the lstItems as a ListView
     */
    private void setupRecyclerView() {
        lstItems.setHasFixedSize(true);
        mAdapter = new ShoppingCartAdapter(new ArrayList<Item>(dameOrden()));
        lstItems.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        lstItems.setLayoutManager(mLayoutManager);
        lstItems.setItemAnimator(new DefaultItemAnimator());
    }

    /***
     * Sort the Shopping Cart's collection by Product's name
     * @return
     */
    private TreeSet dameOrden() {
        TreeSet<Item> itemsView = new TreeSet<Item>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.getName().equals(o2.getName())) {
                    return 0;
                } else
                    return o1.getName().compareTo(o2.getName());
            }
        });

        itemsView.addAll(mViewModel.getData());

        return itemsView;
    }

    /***
     * If the Shopping Cart's collection is empty, shows de EmptyView and hides the FAB
     */
    private void lstIsEmpty() {
        if (mViewModel.getData().isEmpty()) {
            lstItems.setVisibility(View.GONE);
            lblEmptyView.setVisibility(View.VISIBLE);
            fab.setVisibility(View.GONE);
            lblTotal.setVisibility(View.GONE);
        } else {
            lstItems.setVisibility(View.VISIBLE);
            lblEmptyView.setVisibility(View.GONE);
            fab.setVisibility(View.VISIBLE);
            lblTotal.setVisibility(View.VISIBLE);
        }
    }

    /***
     * Updates the total price
     */
    public static void calculatePrice() {
        float priceFinal = 0.0f;
        for (int i = 0; i < mViewModel.getData().size(); i++) {
            priceFinal += mViewModel.getData().get(i).getPrice();
        }
        lblTotal.setText(String.format("Total: %.2f € ", priceFinal));
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        mViewModel.getData().clear();
        mAdapter.notifyDataSetChanged();
        lstIsEmpty();
        Toast.makeText(this, R.string.compra, Toast.LENGTH_SHORT).show();
    }

    /***
     * Updates the FAB's visivility
     */
    public static void CheckFab(){
        if(mViewModel.getData().size() == 0) {
            fab.setVisibility(View.GONE);
        }
        else {
            fab.setVisibility(View.VISIBLE);
        }

    }
}
