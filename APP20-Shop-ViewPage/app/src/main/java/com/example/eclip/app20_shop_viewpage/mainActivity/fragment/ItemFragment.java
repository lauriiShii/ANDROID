package com.example.eclip.app20_shop_viewpage.mainActivity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.eclip.app20_shop_viewpage.R;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.DataBaseItemsShop;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.RepositoryImpItems;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.RepositoryItems;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;
import com.example.eclip.app20_shop_viewpage.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.eclip.app20_shop_viewpage.utils.Constantes.ARG_ITEM;


public class ItemFragment extends Fragment {


    @BindView(R.id.imgItem)
    ImageView imgItem;
    @BindView(R.id.lblManufacture)
    TextView lblManufacture;
    @BindView(R.id.RatingBar)
    ProgressBar RatingBar;
    @BindView(R.id.lblPrice)
    TextView lblPrice;
    Unbinder unbinder;

    private static Context mContext; // context obtained from the activity

    // item that loads the fragment. The viewPage always loads the left,
    // center and right but if the item is static the information is not
    // updated correctly so the item must be independent to each fragment,
    // for this we use the bundle in the newInstance
    private Item mItem;

    // repository of items in the store that will show the viewPage
    private RepositoryItems mData;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        mData = RepositoryImpItems.getInstance(DataBaseItemsShop.getInstance());
        mItem = getArguments().getParcelable(ARG_ITEM);
        return view;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showItem();
    }


    /***
     * we assign the values of the item that we are going to represent in the fragment
     */
    public void showItem() {
        ImageUtils.loadImg(mContext, imgItem, mItem.getSrc());
        lblManufacture.setText(mItem.getFactory());
        lblPrice.setText(String.format("%s €", mItem.getPrice()));
        RatingBar.setProgress(mItem.getValueFans());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /***
     * creates the instance of the fragment itself and passes through argument the item it should display
     * @param c context
     * @param item
     * @return this fragment
     */
    public static ItemFragment newInstance(Context c, Item item) {
        mContext = c;
        ItemFragment frg = new ItemFragment();
        Bundle argumentos = new Bundle();
        argumentos.putParcelable(ARG_ITEM, item);
        frg.setArguments(argumentos);
        return frg;
    }
}
