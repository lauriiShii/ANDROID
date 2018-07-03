package com.example.eclip.app14_galeria.mainActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.eclip.app14_galeria.R;
import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eclip on 24/11/2017.
 */

public class AdapterArtWork extends BaseAdapter {

    private List<ArtWork> mData; //Data shown by the adapter
    private final LayoutInflater mLayoutInflater; //layout to be inflated
    private Context context; // Context of the activity where the adapter is used

    /***
     * Constructor: is responsible for starting and inflating the listView or Gridview.
           * @param context context of the activity that calls
           * @param data array of the data that will load
           * @param mGridView element used to load the data.
           * Necessary for the process of deleting the items and putting them back
           */
    public AdapterArtWork(Context context, @NonNull ArrayList<ArtWork> data ) {
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
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
            convertView = mLayoutInflater.inflate(R.layout.item, parent, false);
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
    public void setData(List<ArtWork> newData) {
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
        @BindView(R.id.lblName)
        TextView lblName;
        @BindView(R.id.lblAuthor)
        TextView lblAuthor;
        @BindView(R.id.lblYear)
        TextView lblYear;


        /***
         * Constructor: start the layout user components
         * @param view
         */
        ViewHolder(View view) {
            ButterKnife.bind(this, view);}

        /***
         * Bind: Management of a user
         * @param artWork
         */
        public void bind(final ArtWork artWork) {
            lblName.setText(artWork.getName());
            lblAuthor.setText(artWork.getAuthor());
            lblYear.setText(String.format("%d",artWork.getYear()));
        }
    }
}
