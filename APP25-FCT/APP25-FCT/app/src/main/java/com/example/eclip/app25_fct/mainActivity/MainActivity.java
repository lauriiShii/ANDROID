package com.example.eclip.app25_fct.mainActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.eclip.app25_fct.R;
import com.example.eclip.app25_fct.bdd.local.BD;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryCompany;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryCompanyImpl;
import com.example.eclip.app25_fct.databinding.ActivityMainBinding;
import com.example.eclip.app25_fct.preference.PreferenceActivity;

import static com.example.eclip.app25_fct.utils.FragmentUtils.openBack;
import static com.example.eclip.app25_fct.utils.FragmentUtils.openCompanyDetail;
import static com.example.eclip.app25_fct.utils.FragmentUtils.openCompanyList;
import static com.example.eclip.app25_fct.utils.FragmentUtils.openStudentDetail;
import static com.example.eclip.app25_fct.utils.FragmentUtils.openStudentList;
import static com.example.eclip.app25_fct.utils.FragmentUtils.openVisitList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Conexión entre el XML y el .java
    ActivityMainBinding mLayoutBinding;
    // Conexión con los datos de las preferencias
    SharedPreferences preferencias;
    // Nombre del layout a mostrar según lo escogido por el usuario
    String layout;
    // Nos da todas las empresas sin LiveData
    RepositoryCompany mRepositoryCompany;

    /**
     * Se enlaza el XML con el .java, se instancia el repositorio, se configura el drawer
     * y se configuran las preferencias.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mRepositoryCompany = RepositoryCompanyImpl.getInstance(BD.getInstance(this).getDb());;
        setupDrawer();
        setupPreferene();
    }

    /**
     * Depende de la preferencia almacenada cargara como primer fragmento uno u otro,
     * por defecto carga el de las proximas visitas.
     */
    private void setupPreferene(){
        preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        layout = preferencias.getString(getString(R.string.pref_init_view_key), getString(R.string.preference1));
        if(layout.equals(getString(R.string.preference1))) {
            openVisitList(this);
        } else if (layout.equals(getString(R.string.preference2))) {
            openStudentList(this);
        } else if (layout.equals(getString(R.string.preference3))) {
            openCompanyList(this);
        }
    }

    /**
     * Configura la toolbar y el drawer.
     */
    private void setupDrawer(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mLayoutBinding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mLayoutBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mLayoutBinding.navView.setNavigationItemSelectedListener(this);
    }

    /**
     * Cuando se da al boton de atras primero se comprueba si el drawer esta abierto o no,
     * si esta abierto lo cierra sino vuelve al fragmento de lista anterior y sino sale de la app.
     */
    @Override
    public void onBackPressed() {
        if (mLayoutBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mLayoutBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 0){
            super.onBackPressed();
        } else{
            openBack(this);
        }
    }

    /**
     * Infla el menú.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Establece que hacer cuando se hace clic en la opción del menú.
     * En este caso se abre la pantalla de preferencias.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, PreferenceActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Se establece que hacer según la opcion pulsada del drawer.
     * Si se selecciona un nuevo estudiante y no existen compañias se le inforamará
     * al usuario de que hay que crear primero una compañia.
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mmu_list_business) {
            openCompanyList(this);
        } else if (id == R.id.mmu_new_company) {
            openCompanyDetail(this);
        } else if(id == R.id.mmu_list_students){
            openStudentList(this);
        } else if(id == R.id.mmu_new_student){
            openNewStudent();
        } else if (id == R.id.mmu_about){
            myData();
        } else if (id == R.id.mmu_list_visit){
            openVisitList(this);
        } else{
            startActivity(new Intent(this, PreferenceActivity.class));
        }

        mLayoutBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Alerta que sale cuando se intenta crear un estudiante y no hay ninguna compañia
     */
    private void openNewStudent(){
        if(mRepositoryCompany.loadAllBusinessNotLiveData().size() != 0) {
            openStudentDetail(this);
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.mush_company)
                    .setCancelable(false)
                    .setPositiveButton(R.string.okey, (dialog, id1) -> {
                        // Closes the dialog
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    /**
     * Mensaje que sale cuando se pulsa info.
     */
    private void myData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.my_data)
                .setCancelable(false)
                .setPositiveButton(R.string.okey, (dialog, id1) -> {
                    // Closes the dialog
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
