package com.example.eclip.app25_fct.mainActivity.Students;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class DirectSelectionDialogFragment extends DialogFragment {

    private Callback mListener = null;

    @SuppressWarnings("UnusedParameters")
    public interface Callback {
        void onItemClick(DialogFragment dialog, int which);
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getTargetFragment().getContext());
        b.setTitle("Horario");
        b.setItems(new String[]{"In the morning", "In the afternoon", "Match turn"},
                (dialog, which) -> mListener.onItemClick(DirectSelectionDialogFragment.this, which));
        return b.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(getTargetFragment().getContext());
        try {
            mListener = (Callback) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Context: " + context.toString()
                    + " must implement DirectSelectionDialogFragment.Callback");
        }
    }
}
