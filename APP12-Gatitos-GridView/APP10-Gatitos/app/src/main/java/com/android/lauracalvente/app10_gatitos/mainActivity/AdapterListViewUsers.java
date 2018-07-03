package com.android.lauracalvente.app10_gatitos.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lauracalvente.app10_gatitos.R;
import com.android.lauracalvente.app10_gatitos.bdd.users.RepositoryImplUsers;
import com.android.lauracalvente.app10_gatitos.bdd.users.model.User;
import com.android.lauracalvente.app10_gatitos.profileActivity.ProfileActivity;
import com.android.lauracalvente.app10_gatitos.utils.Constantes;
import com.android.lauracalvente.app10_gatitos.utils.IntentUtils;
import com.android.lauracalvente.app10_gatitos.utils.ListViewUtils;
import com.android.lauracalvente.app10_gatitos.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User list screen adapter
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

class AdapterListViewUsers extends BaseAdapter {

    private List<User> mData; //Data shown by the adapter
    private final LayoutInflater mLayoutInflater; //layout to be inflated
    private Context context; // Context of the activity where the adapter is used
    private final GridView mGridView; // Gridview used in the activity

    /***
      * Constructor: is responsible for starting and inflating the listView or Gridview.
      * @param context context of the activity that calls
      * @param data array of the data that will load
      * @param mGridView element used to load the data.
      * Necessary for the process of deleting the items and putting them back
      */
    public AdapterListViewUsers(Context context, @NonNull ArrayList<User> data, GridView mGridView) {
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mGridView = mGridView;
    }

    /***
     * GetView: responsible for returning the state of the adapter, if it was never done it stores it for
     * first time
     * @param position scroll position
     * @param convertView view showing the content
     * @param parent view that handles the views that contain the content
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.user, parent, false);
            viewHolder = onCreateViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    /***
     * GetCount: number of items that the GridView has
     * @return
     */
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    /***
     * GetItem: With a position returns the object of that position
     * @param position of the item that was clicked
     * @return user
     */
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    /***
     * GetItemId: position of the item that has been clicked
     * @param position
     * @return position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /***
     * SetData: Necessary to update the adapter data externally.
     * @param newData
     */
    public void setData(List<User> newData) {
        this.mData = newData;
        this.notifyDataSetChanged();
    }

    /***
     * OnCreateViewHolder: Create the viewHolder
     * @param itemView item you are creating
     * @return the created item
     */
    private ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    /***
     * onBindViewHolder: management of the item
     * @param holder items to manage
     * @param position of the item to be managed
     */
    private void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    /**
     * Internal class View Holder: manages the elements in the user layout.
     *
     * @author Laura Calvente
     * @version 2017
     * @since 1.0
     */
    class ViewHolder {
        @BindView(R.id.imgProfile)
        ImageView imgProfile;
        @BindView(R.id.lblName)
        TextView lblName;
        @BindView(R.id.lblEmail)
        TextView lblEmail;
        @BindView(R.id.lblPhone)
        TextView lblPhone;
        @BindView(R.id.btnEdit)
        Button btnEdit;
        @BindView(R.id.btnDelete)
        Button btnDelete;
        @BindView(R.id.btnOptions)
        Button btnOptions;
        @BindView(R.id.lstItem)
        CardView lstItem;

        /***
         * Constructor: start the layout user components
         * @param view
         */
        ViewHolder(View view) {ButterKnife.bind(this, view);}

        /***
         * Bind: Management of a user
         * @param user
         */
        public void bind(final User user) {
            imgProfile.setImageResource(user.getCat().getSrc());
            lblName.setText(user.getName());
            lblEmail.setText(user.getEmail());
            lblPhone.setText(user.getPhone());
            btnDelete.setOnClickListener(view -> onDeleteUser(user));
            btnEdit.setOnClickListener(view -> requestData(user));
            btnOptions.setOnClickListener(view -> inflatePoupMenu(view,user));
        }

        /***
         * OnDeleteUser: Remove a specific item from the GridView.
         *
         * We store the position that we want to eliminate, with that position we check if that item
         * is selected or not and we store it. We create an array with the selected items.
         *
         * We delete the user through the viewModel and notify the adapter to update.
         *
         * We show the snackBar with the message and the option of undo. If you press undo we call the addUser (...).
         *
         * We delete the stored user as checkeado.
         *
         * We check if the one that is after the eliminated is checked or not and in case it did not select the next item
         * to maintain the truthfulness of the data facing the user interface and that it is not confusing.
         *
         * @param user the user clicked
         */
        private void onDeleteUser(User user){
            //Position of the user who is being struck
            int position = MainActivityViewModel.indexUser(user);
            //The user who wants to be removed is checked?
            boolean userDeketeIsChecked = mGridView.isItemChecked(position);
            // List of checked items
            List<Object> usersChecked = ListViewUtils.getSelectedItems(mGridView, true);
            //Remove the user
            MainActivityViewModel.deleteUser(user);
            //We update the adapter
            notifyDataSetChanged();
            //We show the snackBar
            Snackbar bar = Snackbar.make(btnDelete, R.string.main_activity_delete, Snackbar.LENGTH_LONG)
                    .setAction(R.string.main_activity_undo, view -> addUser(position, user, usersChecked, userDeketeIsChecked));
            bar.show();
            //Remove the deleted user from the array of checked users
            usersChecked.remove(user);
            //We check if the following one is checked or not to continue keeping it in its original state
            setCheckedUsersAfterDelete(usersChecked);
        }

        /***
         * SetCheckedUsersAfterDelete: Select again the items that were selected
         * @param usersCheck list of user items that are checked in the contextual action
         */
        private void setCheckedUsersAfterDelete(List<Object> usersCheck){
            for (int i = 0; i < usersCheck.size(); i++)
                mGridView.setItemChecked(MainActivityViewModel.indexUser((User) usersCheck.get(i)), true);
            notifyDataSetChanged();
        }

        /***
         * AddUser: In case of selecting "undo" in the snackBar we will add the item in its original state,
         * that is, if it is selected, it will remain selected, if not.
         *
         * First we deselect all the items ...
         *
         * We add the user in the position indicated
         *
         * We repaint items that were selected
         *
         * And finally we check if this item we have added was selected or not, in case if
         * we mark as selected.
         *
         * @param position of the user that has been pressed
         * @param user user that has been pressed
         * @param usersChecked array of selected
         * @param isUserDeleteChecked checking whether the user was selected or not
         */
        private void addUser(int position, User user, List<Object> usersChecked, boolean isUserDeleteChecked){
            // Items are deselected
            ListViewUtils.getSelectedItems (mGridView, true);
            // We add the user
            MainActivityViewModel.addUser (position, user);
            // Check the rest of the items that were selected
            setCheckedUsersAfterDelete (usersChecked);
            // If the one we added was selected then we selected it
            if (isUserDeleteChecked)
                mGridView.setItemChecked (position, true);
            // The adapter is recharged
            notifyDataSetChanged ();
        }

        /***
         * RequestData: The profile activity is opened with the user data that we have clicked on.
         * For that we spent an intent with the user and his position
         * @param user user clicked
         */
        private void requestData(User user) {
            ProfileActivity.startForResult((MainActivity)context, Constantes.USER, user, RepositoryImplUsers.indexUser(user));
        }

        /***
         * InflatePoupMenu: The poup menu of the item in which the option button is pressed is inflated.
         * @param v sight where inflates?
         * @param user user where clicked
         * @return we return true to indicate that we have changed the method
         */
        private boolean inflatePoupMenu(View v, User user) {
            PopupMenu popup = new PopupMenu(context, v);
            popup.getMenuInflater().inflate(R.menu.poup_main_activity_menu, popup.getMenu());
            validateItems(user, popup);
            popup.setOnMenuItemClickListener(item -> onMenuItemClick(item,user));
            popup.show();
            return true;
        }

        /***
         * OnMenuItemClick: According to the selected option, it will launch one intent or another.
         * @param item option of the menu on which the user clicked
         * @param user user from which the data is obtained
         * @return true that we have modified the method
         */
        public boolean onMenuItemClick(MenuItem item, User user) {
            switch (item.getItemId()) {
                case R.id.mmu_call:
                    putIntent(IntentUtils.newDialIntent(user.getPhone()));
                    break;
                case R.id.mmu_email:
                    putIntent(IntentUtils.sendEmail(new String[]{user.getEmail()}));
                    break;
                case R.id.mmu_map:
                    putIntent(IntentUtils.newSearchInMapIntent(user.getEmail()));
                    break;
                case R.id.mmu_web :
                    putIntent(IntentUtils.newWebSearchIntent(user.getWeb()));
                    break;
            }
            return true;
        }

        /***
         * ValidateItems: We check which options of the menu may be available and which not depending on the
         * data of each user.
         * @param user user from whom we take the data
         * @param popup menu that has been inflated
         */
        private void validateItems(User user, PopupMenu popup){
            validateItem(popup.getMenu().getItem(Constantes.INDEX_MMU_CALL), ValidationUtils.isValidPhone(user.getPhone()));
            validateItem(popup.getMenu().getItem(Constantes.INDEX_MMU_MAP), !TextUtils.isEmpty(user.getDireccion()));
            validateItem(popup.getMenu().getItem(Constantes.INDEX_MMU_EMAIL), ValidationUtils.isValidEmail(user.getEmail()));
            validateItem(popup.getMenu().getItem(Constantes.INDEX_MMU_WEB), ValidationUtils.isValidUrl(user.getWeb()));
        }

        /***
         * ValidateItem: Determines which options of the poup menu will be available or not
         * @param item option of the item to determine if it is clickable or not
         * @param validate indicates whether it is valid or not to be enabled
         */
        private void validateItem( MenuItem item, Boolean validate){
            if(validate)
                item.setEnabled(true);
            else
                item.setEnabled(false);
        }

        /***
         * PutIntent: Launches the item that is passed to the intergalactic space ,, :D ,, *.*
         * @param intent
         */
        private void putIntent(Intent intent) {
            Intent i = intent;
            if (IntentUtils.isActivityAvailable(context, i))
                context.startActivity(i);
        }
    }
}
