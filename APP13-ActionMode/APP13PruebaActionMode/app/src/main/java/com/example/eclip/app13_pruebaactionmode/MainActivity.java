package com.example.eclip.app13_pruebaactionmode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.GridView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.grdUsers)
    GridView grdUsers;
    private Adaptador mAdapter; //adapter for lstUser (listView)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inflateItemsUsers();
    }

    private void inflateItemsUsers() {
        mAdapter = new Adaptador(this, bdd.getData());
        grdUsers.setAdapter(mAdapter);

        grdUsers.setChoiceMode(grdUsers.CHOICE_MODE_MULTIPLE_MODAL);
        grdUsers.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                actionMode.setTitle(getString(R.string.oneofother, grdUsers.getCheckedItemCount(), grdUsers.getCount()));
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
                if (menuItem.getItemId() == R.id.mnuDelete) {
                    //deleteSelectedUsers(ListViewUtils.getSelectedItems(lstUsers, false));
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });
    }
}
