package com.android.lauracalvente.app10_gatitos.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.lauracalvente.app10_gatitos.R;
import com.android.lauracalvente.app10_gatitos.adapters.AdapterListViewUsers;
import com.android.lauracalvente.app10_gatitos.bdd.ColecctionUsers;
import com.android.lauracalvente.app10_gatitos.bdd.ColectionCat;
import com.android.lauracalvente.app10_gatitos.model.pojos.User;
import com.android.lauracalvente.app10_gatitos.utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

/**
 * Activity that shows the main screen
 *
 * This view is responsible for displaying a list of users that you can delete or add
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lstUsers)
    ListView lstUsers; //listView that shows the users of the application
    @BindView(R.id.lblEmptyView)
    TextView lblEmptyView;//text that appears when the listView is empty

    AdapterListViewUsers adapter; //adapter for lstUser (listView)
    static User user; //user needed to be able to undo their deletion from the listView

    /**
     * OnCreate: It is responsible for inflating the views of the "activity_main" layout
     * call "inflateItemsUser"
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inflateItemsUsers(); //we inflate the elements of the listview with its adapter
    }

    /**
     * InflateItemsUser: initializes the adapter, sets the text of the listView when it is
     * empty, and the adapter is associated with the listView
     */
    private void inflateItemsUsers() {
        adapter = new AdapterListViewUsers(this, ColecctionUsers.getUsers());
        lstUsers.setEmptyView(lblEmptyView);
        lstUsers.setAdapter(adapter);
    }

    /**
     * OnCreateOptionsMenu: inflates the menu for this activity, in this case the menu
     * that we inflate is "main_activity_menu"
     * @param menu
     * @return True to report that inflation has been completed
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    /**
     * OnOptionsItemSelected: It establishes that it is going to do the button of the menu
     * when pressing it, in this case it abre a new activity "ProfileActivity"
     * @param item
     * @return if you press a button in particular the action of starting the
     *         new activity is executed but it is informed that no
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mmu_new_profile) {
            addNewProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * RequestData: the profileActivity activity starts with a method of itself "startForResult"
     * that is in charge of generating the extra of the User
     * This method is called when you click on a user in the list and you need to load the user's data
     * @param position Is necessary to know which user has clicked and can reload your data in the new activity
     */
    private void requestData(int position) {
        ProfileActivity.startForResult(this, Constantes.USER, ColecctionUsers.bddUsers.get(position), position);
    }

    /**
     * OnItemClick:lisener de lstUser (listView), responsible for calling the activity ProfileActivity that will
     *  show the data of the item on which the user clicked
     * @param position Is passed to "requestData" since it is the index of the position where the user has
     *                 clicked and it is the form by which he knows which user in particular is
     */
    @OnItemClick(R.id.lstUsers)
    void onItemClick(int position) {requestData(position);}

    /**
     * OnViewClicked: lisener of the text that appears when the listView is empty, this ensures
     * that when you click on it, the ProfileActivity activity starts to add a new user to the list
     */
    @OnClick(R.id.lblEmptyView)
    public void onViewClicked() {addNewProfile();}

    /**
     * AddNewProfile: starts a new activity "ProfileActivity" without data, so that the last loaded
     * image of the users that were clicked on does not load, the cat is loaded by default.
     */
    private void addNewProfile(){
        ProfileActivity.cat = ColectionCat.bdd.get(Constantes.DEFAULT_CAT);
        startActivity(new Intent(this, ProfileActivity.class));
    }

    /**
     * OnItemLongClick: Lisener of the listView when a long click is made on an item, when this
     * happens the item is deleted, this is done by the "deleteUserSnackBar"
     * @param position of the user that has been clicked on, "deleteUserSnackBar" needs it to
     *                 know which user to remove
     * @return true to report that the action was executed
     */
    @OnItemLongClick(R.id.lstUsers)
    boolean onItemLongClick(int position) {
        deleteUserSnackbar(position);
        return true;
    }

    /**
     * deleteUserSnackbar: stores the user on whom it was pressed until the action ends, when this
     * method is started, a user is removed from a specific position that he recives, if the user
     * does not want to delete it he can press "undo" and he will be added to it again position
     * and the adapter is updated with "adapter.notifyDataSetChanged ()"
     * @param position used to know which user to delete or add in a specific position
     */
    private void deleteUserSnackbar(final int position){
        user = ColecctionUsers.bddUsers.get(position);
        ColecctionUsers.deleteUser(position);
        adapter.notifyDataSetChanged();
        Snackbar bar = Snackbar.make(lstUsers, R.string.main_activity_delete, Snackbar.LENGTH_LONG)
                .setAction(R.string.main_activity_undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ColecctionUsers.addUserPosition(position, user);
                        adapter.notifyDataSetChanged();
                    }
                });
        bar.show();
    }
}
