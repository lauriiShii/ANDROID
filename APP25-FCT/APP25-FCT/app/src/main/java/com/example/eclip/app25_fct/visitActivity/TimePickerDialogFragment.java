package com.example.eclip.app25_fct.visitActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by carlo on 28/02/2018.
 */

public class TimePickerDialogFragment
        extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener listener;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        return new TimePickerDialog(this.getActivity(),
                listener, calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE), true);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            listener = (TimePickerDialog.OnTimeSetListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TimePickerDialog.OnTimeSetListener");
        }
    }

}