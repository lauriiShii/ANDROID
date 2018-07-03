package com.example.eclip.app20_shop_viewpage.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.eclip.app20_shop_viewpage.R;
import com.example.eclip.app20_shop_viewpage.bdd.Cest.RepositoryImpItemsCart;
import com.example.eclip.app20_shop_viewpage.bdd.Cest.RepositoryItemsCart;
import com.example.eclip.app20_shop_viewpage.shoppingCarActivity.ShoppingCarActivity;
import com.example.eclip.app20_shop_viewpage.bdd.Cest.DataBaseItemsCest;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.DataBaseItemsShop;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.RepositoryImpItems;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.RepositoryItems;
import com.example.eclip.app20_shop_viewpage.mainActivity.viewPage.PagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import me.relex.circleindicator.CircleIndicator;

import static com.example.eclip.app20_shop_viewpage.utils.FabUtils.openFab;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.ViewPage)
    ViewPager ViewPage;
    @BindView(R.id.Indicator)
    CircleIndicator Indicator;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private PagerAdapter adapter; // Adapter of the ViewPager
    static public FragmentManager gestor; // FragmentManager that manages each Fragment per Page
    private RepositoryItemsCart shoppingCar; // Item's collection of the shopping cart
    private RepositoryItems mData; // Item's collection of the shop


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gestor = getSupportFragmentManager();
        shoppingCar = RepositoryImpItemsCart.getInstance(DataBaseItemsCest.getInstance());
        mData = RepositoryImpItems.getInstance(DataBaseItemsShop.getInstance());
        loadPageView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mmuShop) {
            openShoppingCar();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /***
     * Starts the ShoppingCarActivity
     */
    private void openShoppingCar() {
        startActivity(new Intent(this, ShoppingCarActivity.class));
    }

    /***
     * Starts the ViewPager and sets each fragment with each page
     */
    private void loadPageView() {
        adapter = new PagerAdapter(gestor, this);
        ViewPage.setAdapter(adapter);
        Indicator.setViewPager(ViewPage);
        ViewPage.post(new Runnable() {
            @Override
            public void run() {
                ViewPage.setCurrentItem(0);
            }
        });
    }

    @OnPageChange(R.id.ViewPage)
    public void onPageSelected(int position) {
        openFab(this, fab);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        shoppingCar.addItem(mData.getItems().get(ViewPage.getCurrentItem()));
        //Toast.makeText(this, String.format("%s", shoppingCar.getItemsCest().get(0).getName()), Toast.LENGTH_SHORT).show();
    }
}
