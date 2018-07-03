package com.android.lauracalvente.app10_gatitos.mainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.TextView;

import com.android.lauracalvente.app10_gatitos.bdd.users.RepositoryImplUsers;
import com.android.lauracalvente.app10_gatitos.bdd.users.RepositoryUsers;
import com.android.lauracalvente.app10_gatitos.utils.Constantes;
import com.android.lauracalvente.app10_gatitos.R;
import com.android.lauracalvente.app10_gatitos.bdd.users.DataBaseUsers;
import com.android.lauracalvente.app10_gatitos.bdd.cats.ColectionCat;
import com.android.lauracalvente.app10_gatitos.bdd.users.model.User;
import com.android.lauracalvente.app10_gatitos.profileActivity.ProfileActivity;
import com.android.lauracalvente.app10_gatitos.utils.ListViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Activity that shows the main screen
 *
 * This view is responsible for displaying a list of users that you can delete or add
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity{

    @BindView(R.id.lstUsers)
    GridView lstUsers; //GridView that shows the users of the application
    @BindView(R.id.lblEmptyView)
    TextView lblEmptyView;//text that appears when the listView is empty

    private AdapterListViewUsers mAdapter; //adapter for lstUser (listView)
    private RepositoryUsers mRepositoryUsers; //users needed to be able to undo their deletion from the listView
    private static MainActivityViewModel mViewModel;

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
        createViewModel();
        inflateItemsUsers(); //we inflate the elements of the listview with its adapter
    }

    /***
     * createViewModel: We create the view model that serves to save the state
     */
    private void createViewModel (){
        mRepositoryUsers = RepositoryImplUsers.getInstance(DataBaseUsers.getInstance());
        mViewModel = ViewModelProviders.of(this, new MainActivityViewModelFactory(mRepositoryUsers)).get(
                MainActivityViewModel.class);
    }

    /**
     * InflateItemsUser: initializes the adapter, sets the text of the listView when it is
     * empty, and the adapter is associated with the listView
     */
    private void inflateItemsUsers() {
        lstUsers.setEmptyView(lblEmptyView);
        lstUsers.setChoiceMode(lstUsers.CHOICE_MODE_MULTIPLE_MODAL);
        lstUsers.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                actionMode.setTitle(getString(R.string.oneofother, lstUsers.getCheckedItemCount(), lstUsers.getCount()));
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.contextual_mzin_activity, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.mnuDelete)
                    deleteSelectedUsers(ListViewUtils.getSelectedItems(lstUsers, false));
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });

        mAdapter = new AdapterListViewUsers(this, mViewModel.getData(), lstUsers);
        lstUsers.setAdapter(mAdapter);
    }

    /**
     * OnClickEmpty: If the empty list will load the emptyView, when we click on this
     * it will open the activity of a new profile
     */
    @OnClick(R.id.lblEmptyView)
    void onClickEmpty(){
        addNewProfile();
    }

    /***
     * OnItemClick: load the data of the selected item in the profile activity
     * @param position of the selected item
     */
    @OnItemClick(R.id.lstUsers)
    void onItemClick(int position) {
        requestData(mViewModel.userIndex(position));
    }

    /***
     * RequestData: start the profile activity with the data of a user that is
     * passed as extra as the position
     * @param user clicked
     */
    private void requestData(User user) {
        ProfileActivity.startForResult(this, Constantes.USER, user, RepositoryImplUsers.indexUser(user));
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
     * AddNewProfile: starts a new activity "ProfileActivity" without data, so that the last loaded
     * image of the users that were clicked on does not load, the cat is loaded by default.
     */
    private void addNewProfile(){
        ProfileActivity.cat = ColectionCat.bdd.get(Constantes.DEFAULT_CAT);
        startActivity(new Intent(this, ProfileActivity.class));
    }

    /**
     * DeleteSelectedUsers: Remove the selected users in the action mode
     * @param selectedItems Selected items
     */
    private void deleteSelectedUsers(List<Object> selectedItems) {
        Snackbar bar = Snackbar.make(lstUsers, getString(R.string.delete_much_items, selectedItems.size()), Snackbar.LENGTH_LONG);
        bar.show();
        //No se por que y no tiene sentido pero poniendo un solo for con las dos sentencias no lo hace solo deselecciona un item ...
        for (Object user: selectedItems) {
            lstUsers.setItemChecked(mViewModel.indexUser((User)user), false);
        }
        for (Object user: selectedItems) {
            mViewModel.deleteUser((User)user);
        }
        mAdapter.setData(mViewModel.getData());

    }
}
