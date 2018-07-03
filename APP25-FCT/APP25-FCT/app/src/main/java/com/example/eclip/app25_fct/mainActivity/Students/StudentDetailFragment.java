package com.example.eclip.app25_fct.mainActivity.Students;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.eclip.app25_fct.R;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryCompanyImpl;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryStudentImpl;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryTeacherImpl;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisit;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisitImpl;
import com.example.eclip.app25_fct.bdd.local.BD;
import com.example.eclip.app25_fct.bdd.model.Company;
import com.example.eclip.app25_fct.bdd.model.Student;
import com.example.eclip.app25_fct.bdd.model.Teacher;
import com.example.eclip.app25_fct.bdd.model.Visit;
import com.example.eclip.app25_fct.databinding.FragmentStudentDetailBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static com.example.eclip.app25_fct.utils.Constantes.ARG_STUDENT;
import static com.example.eclip.app25_fct.utils.Constantes.TAG_HOUR;
import static com.example.eclip.app25_fct.utils.FragmentUtils.openBack;
import static com.example.eclip.app25_fct.utils.KeyBoardUtils.hideOffKeyBoard;

public class StudentDetailFragment extends Fragment implements DirectSelectionDialogFragment.Callback {

    // Class that manages the dataBinding between the
    // XML and the Activity
    private FragmentStudentDetailBinding mBinding;
    // Company that we use to update and insert by applying
    // the changes with dataBinding
    private Student mStudent;
    // Repository where all the management methods of the
    // business table of the database are located
    private RepositoryStudentImpl mRepositoryStudents;
    private RepositoryVisit mRepositoryVisit;
    private RepositoryCompanyImpl mRepositoryBusiness;
    private RepositoryTeacherImpl mRepositoryTeachers;
    SharedPreferences preferencias;
    // Variable que almacena el nombre del estiudiante en el que se hizo click en la lista para actualizarlo.
    // De esta forma podremos controlar que si se actualiza no se introduzca un nombre que ya existe pero si el suyo anterior.
    private String auxOldName;


    /***
     * Method that is triggered after the activity is created
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /***
     * Method that instances the fragment
     * @return
     */
    public static StudentDetailFragment newInstance() {
        StudentDetailFragment frg = new StudentDetailFragment();
        return frg;
    }

    public static StudentDetailFragment newInstance(Student student) {
        StudentDetailFragment frg = new StudentDetailFragment();
        Bundle argumentos = new Bundle();
        argumentos.putParcelable(ARG_STUDENT, student);
        frg.setArguments(argumentos);
        return frg;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    /***
     * Databinding configuration
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentStudentDetailBinding.inflate(inflater, container, false);
        mBinding.setDa(this);
        mRepositoryVisit = RepositoryVisitImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mRepositoryStudents = RepositoryStudentImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mRepositoryBusiness = RepositoryCompanyImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mRepositoryTeachers = RepositoryTeacherImpl.getInstance(BD.getInstance(getActivity()).getDb());
        loadDataBusiness();
        loadDatateachers();
        getData();
        return mBinding.getRoot();
    }

    private void loadDataBusiness() {

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Company> business;
        business = (ArrayList<Company>) mRepositoryBusiness.loadAllBusinessNotLiveData();

        if (business != null) {
            for (int i = 0; i < business.size(); i++) {
                names.add(business.get(i).getName());
            }
        } else {
            names.add("none");
        }

        String compareValue = "some value";
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spnEmpresa.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mBinding.spnEmpresa.setSelection(spinnerPosition);
        }
    }

    private void loadDatateachers() {
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Teacher> teachers;
        teachers = (ArrayList<Teacher>) mRepositoryTeachers.loadAllTeachersNotLiveDeata();

        if (teachers != null) {
            for (int i = 0; i < teachers.size(); i++) {
                names.add(teachers.get(i).getName());
            }
        } else {
            names.add("none");
        }

        String compareValue = "some value";
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spnTutor.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mBinding.spnTutor.setSelection(spinnerPosition);
        }
    }


    private void getData() {
        if (getArguments() != null) {
            mStudent = getArguments().getParcelable(ARG_STUDENT);
            auxOldName = mStudent.getName();
            mBinding.spnTutor.setSelection(mStudent.getTeacher()-1);
            mBinding.spnEmpresa.setSelection(mStudent.getCompany()-1);
        } else {
            mStudent = new Student();
        }
        mBinding.setStudent(mStudent);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /***
     * Setups the view model
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Real-time update of txtName, manages the visibility of the fab.
     * If this field is empty the fab would be invisible since the name
     * of the company must always be filled in to be able to register it
     * in the app.
     *
     * @param c
     * @param start
     * @param before
     * @param count
     */
    public void onNameChanged(CharSequence c, int start, int before, int count) {
        if (TextUtils.isEmpty(mBinding.txtName.getText())) {
            mBinding.fab.setVisibility(View.INVISIBLE);
        } else {
            mBinding.fab.setVisibility(View.VISIBLE);
        }
    }


    /**
     * Method called from the xml that is in charge of updating or
     * creating a new company.
     *
     * @param view
     */
    public void fabSaveOnClick(View view) {

        //Comprobamos si se va a actualizar o a hacer uno nuevo, si el id es 0 es uno nuevo sino ya existe.
        if(mStudent.getId() == 0) {
            //Si queremos un nuevo estudiante el nombre no puede existir
            if (mRepositoryStudents.getStudentById(mBinding.txtName.getText().toString()) == null) {
                updateStudent(view);
            } else {
                Toast.makeText(getActivity(), R.string.error_unique_text, Toast.LENGTH_SHORT).show();
            }
        } else{
            //Comprobamos que el nombre introducido si pueda ser el suyo propio pero no uno que tenga otro estudiante
            if(mRepositoryStudents.getNameUpdate(mBinding.getStudent().getName(), auxOldName) == null) {
                updateStudent(view);
            } else {
                Toast.makeText(getActivity(), R.string.error_unique_text, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateStudent(View view){
        int idTeacher = mRepositoryTeachers.loadIdByName(mBinding.spnTutor.getSelectedItem().toString());
        int idBusiness = mRepositoryBusiness.loadIdByName(mBinding.spnEmpresa.getSelectedItem().toString());

        mBinding.getStudent().setCompany(idBusiness);
        mBinding.getStudent().setTeacher(idTeacher);
        mRepositoryStudents.insert(mBinding.getStudent());
        if(mStudent.getId() == 0){
            newVisit();
        }
        hideOffKeyBoard(view, getContext());
        openBack(getActivity());
    }

    private void newVisit(){
        preferencias = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int days = Integer.parseInt(preferencias.getString(getString(R.string.pref_def_days), "15"));
        int idTeacher = mRepositoryTeachers.loadIdByName(mBinding.spnTutor.getSelectedItem().toString());
        Visit visit = new Visit(idTeacher, mRepositoryStudents.getIdByName(mBinding.txtName.getText().toString()), DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().plusDays(days)));
        mRepositoryVisit.insert(visit);
    }

    @Override
    public void onItemClick(DialogFragment dialog, int which) {
        String valor = "";

        switch (which) {
            case 0:
                valor = "In the morning";
                break;
            case 1:
                valor = "In the afternoon";
                break;
            case 2:
                valor = "Match turn";
                break;
        }

        mBinding.txtHorario.setText(valor);
    }

    public void onClickHour(View view) {
        DirectSelectionDialogFragment dialog = new DirectSelectionDialogFragment();
        dialog.setTargetFragment(StudentDetailFragment.this, 0);
        dialog.show(StudentDetailFragment.this.getActivity().getSupportFragmentManager(),
                TAG_HOUR);
    }

}

