package com.example.eclip.app25_fct.visitActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eclip.app25_fct.R;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisit;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisitImpl;
import com.example.eclip.app25_fct.bdd.local.BD;
import com.example.eclip.app25_fct.bdd.model.Student;
import com.example.eclip.app25_fct.bdd.model.Visit;
import com.example.eclip.app25_fct.databinding.ActivityVisitBinding;
import com.example.eclip.app25_fct.utils.Constantes;
import com.facebook.stetho.common.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.example.eclip.app25_fct.utils.Constantes.TAG_DATE;
import static com.example.eclip.app25_fct.utils.Constantes.TAG_TIME_END;
import static com.example.eclip.app25_fct.utils.Constantes.TAG_TIME_START;

public class VisitActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ActivityVisitBinding mBinding;
    private Visit mVisit;
    private Student mStudent;
    private RepositoryVisit mRepositoryVisit;
    SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_visit);
        mBinding.setDa(this);
        mRepositoryVisit = RepositoryVisitImpl.getInstance(BD.getInstance(this).getDb());
        setSupportActionBar(mBinding.toolbar);
        getIntentDataVisit();
        getIntentDataStudent();
        if(!TextUtils.isEmpty(mBinding.getVisit().getObserve())){
            mBinding.fab.setVisibility(View.INVISIBLE);
            mBinding.txtDay.setEnabled(false);
            mBinding.txtEndHour.setEnabled(false);
            mBinding.txtStartHour.setEnabled(false);
            mBinding.txtObservaciones.setEnabled(false);
        } else {
            mBinding.fab.setVisibility(View.VISIBLE);
            mBinding.txtDay.setEnabled(true);
            mBinding.txtEndHour.setEnabled(true);
            mBinding.txtStartHour.setEnabled(true);
            mBinding.txtObservaciones.setEnabled(true);
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mBinding.txtDay.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.of(year, month + 1, dayOfMonth, 1,1)));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (this.getSupportFragmentManager().findFragmentByTag(TAG_TIME_START) != null) {
            mBinding.txtStartHour.setText(DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.of(1,1,1,hourOfDay,minute)));
        } else {
            mBinding.txtEndHour.setText(DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.of(1,1,1,hourOfDay,minute)));
        }
    }

    public void selectionDay(View view) {
        new DatePickerDialogFragment().show(getSupportFragmentManager(), TAG_DATE);
    }

    public void selectionStart(View view) {
        new TimePickerDialogFragment().show(getSupportFragmentManager(), TAG_TIME_START);
    }

    public void selectionEnd(View view) {
        new TimePickerDialogFragment().show(getSupportFragmentManager(), TAG_TIME_END);
    }

    public void saveVisit(View view) {
        if (checkDay() && checkTimes()) {
            if(mStudent != null){
                mBinding.getVisit().setStudent(mStudent.getId());
                mBinding.getVisit().setTeacher(mStudent.getTeacher());
            }
            mRepositoryVisit.insert(mBinding.getVisit());
            if(!TextUtils.isEmpty(mBinding.getVisit().getObserve())) {
                newVisit();
            }
            onBackPressed();
        }
        else{
            Toast.makeText(this, R.string.error_hour, Toast.LENGTH_SHORT).show();
        }
    }

    private void newVisit(){
        preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        int days = Integer.parseInt(preferencias.getString(getString(R.string.pref_def_days), "15"));
        Visit visit = new Visit(mBinding.getVisit().getTeacher(), mBinding.getVisit().getStudent(), DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.parse(mBinding.getVisit().getDay(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).plusDays(days)));
        mRepositoryVisit.insert(visit);
    }

    private boolean checkDay() {
        return !TextUtils.isEmpty(mBinding.txtDay.getText());
    }

    private boolean checkTimes() {

        boolean result = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date start, end;

        if (!TextUtils.isEmpty(mBinding.txtStartHour.getText()) && !TextUtils.isEmpty(mBinding.txtEndHour.getText())) {
            try {
                start = dateFormat.parse(mBinding.txtStartHour.getText().toString());
                end = dateFormat.parse(mBinding.txtEndHour.getText().toString());
                result = start.before(end);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Constantes.VISIT) {
            getDataVisit(data);
        } else if (resultCode == RESULT_OK && requestCode == Constantes.STUDENT){
            getDataStudent(data);
        }
    }

    private void getDataStudent(Intent intent){
        if (intent != null) {
            if (intent.hasExtra(Constantes.OBJET_STUDENT)) {
                mStudent = intent.getParcelableExtra(Constantes.OBJET_STUDENT);
            }
        }
    }

    private void getIntentDataStudent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(Constantes.OBJET_STUDENT)) {
                mStudent = intent.getParcelableExtra(Constantes.OBJET_STUDENT);
            }
        }
    }

    private void getDataVisit(Intent intent) {
        if (intent != null) {
            if (intent.hasExtra(Constantes.OBJET_VISIT)) {
                mVisit = intent.getParcelableExtra(Constantes.OBJET_VISIT);
            }
            else {
                mVisit = new Visit();
            }
        }
        mBinding.setVisit(mVisit);
    }


    private void getIntentDataVisit() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(Constantes.OBJET_VISIT)) {
                mVisit = intent.getParcelableExtra(Constantes.OBJET_VISIT);
            }
            else {
                mVisit = new Visit();
            }
        }
        mBinding.setVisit(mVisit);
    }


    public static void startForResult(Activity activity, int requestCode, Visit u) {
        Intent intent = new Intent(activity, VisitActivity.class);
        intent.putExtra(Constantes.OBJET_VISIT, u);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startForResult(Activity activity, int requestCode, Student u) {
        Intent intent = new Intent(activity, VisitActivity.class);
        intent.putExtra(Constantes.OBJET_STUDENT, u);
        activity.startActivityForResult(intent, requestCode);
    }


}
