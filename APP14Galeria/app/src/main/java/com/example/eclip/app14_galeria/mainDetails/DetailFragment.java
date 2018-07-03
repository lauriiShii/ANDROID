package com.example.eclip.app14_galeria.mainDetails;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eclip.app14_galeria.R;
import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;
import com.example.eclip.app14_galeria.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.eclip.app14_galeria.utils.Constantes.EXTRA_ITEM;
import static com.example.eclip.app14_galeria.utils.Constantes.EXTRA_POSITION;

/**
 *
 * Fragment in charge of showing the work of art and its name and author
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class DetailFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.imgImage)
    ImageView imgImage;
    @BindView(R.id.lblInformation)
    TextView lblInformation;

    private ArtWork mItem; // artwork to show
    private int mPosition; // position of the work of art
    private Callback mListener; // listener that is used for communication with activities
    private static Context context; // context that is loaded when instantiating this fragment

    /***
     * onDestroyView: override by default
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /***
     * Callback: interface that will be implemented in an activity thanks to CALLBACK
     */
    public interface Callback {
        void onDetailShown(int position);
    }

    /***
     * newInstance: new instance of this fragment. Receive the arguments of the intent
     * we will read in the OnCreate.
     *
     * @param c context of the actidiad where this fragment has been instantiated
     * @param item work of art that has to load
     * @param position position of the work of art that should show
     * @return fragment that must load the fragmentManagert of the application
     */
    public static DetailFragment newInstance(Context c, ArtWork item, int position) {
        context = c;
        DetailFragment fragment = new DetailFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(EXTRA_ITEM, item);
        arguments.putInt(EXTRA_POSITION, position);
        fragment.setArguments(arguments);
        return fragment;
    }

    /***
     * onCreate: load the arguments received from the intent
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        obtainArguments();
    }

    /***
     * obtainArguments: load the arguments received from the intent in the work of art at the
     * class level and the position at the class level, it is formed that any method of
     * this class can access this content
     */
    private void obtainArguments() {
        mItem = getArguments().getParcelable(EXTRA_ITEM);
        mPosition = getArguments().getInt(EXTRA_POSITION);
    }

    /***
     * onCreateView: Being inflates the layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /***
     * onAttach: if the activity where the fragemnto has been instantiated has not implemented the
     * CALLBACK methods, it will show the exception.
     * @param activity
     */
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (Callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + getString(R.string.no_CallBack));
        }
    }

    /***
     * onDetach: finaliza la llamada al fragmento
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /***
     * onActivityCreated: shows the content of the artwork
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showItem();
    }

    /***
     * showItem: using the data that was obtained from the intent we loaded the image
     * and the text of this fragment. If there is a call to the listener, the position
     * is passed so that the main activity can load the previous one if needed
     */
    private void showItem() {
        ImageUtils.loadImg(context, imgImage, mItem.getImage());
        lblInformation.setText(mItem.getName()+"\n"+mItem.getAuthor());
        // Notify activity (needed in case of landscape configuration).
        if (mListener != null) {
            mListener.onDetailShown(mPosition);
        }
    }
}
