package com.example.eclip.app25_fct.mainActivity.Business;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eclip.app25_fct.R;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryCompanyImpl;
import com.example.eclip.app25_fct.bdd.local.BD;
import com.example.eclip.app25_fct.bdd.model.Company;
import com.example.eclip.app25_fct.databinding.FragmentBusinessDetailBinding;

import static com.example.eclip.app25_fct.utils.Constantes.ARG_COMPANY;
import static com.example.eclip.app25_fct.utils.FragmentUtils.openBack;
import static com.example.eclip.app25_fct.utils.KeyBoardUtils.hideOffKeyBoard;

public class BusinessDetailFragment extends Fragment {

    // Conexión entre el XML y el .java
    FragmentBusinessDetailBinding mBinding;
    // Objeto compañia que vamos oonstruyendo con dataBinding poco a poco
    Company mCompany;
    // Repositorio de empresas que nos da toda la información que necesitamos saber sobre ellas
    RepositoryCompanyImpl mRepository;
    // Nombre antiguo de la empresa que se conserva ya que es necesario para
    // actualizar el nombre
    private String auxOldName;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Crea una instancia de este fragmento.
     * @return
     */
    public static BusinessDetailFragment newInstance() {
        BusinessDetailFragment frg = new BusinessDetailFragment();
        return frg;
    }

    /**
     * Crea una instancia de este fragmento y obtiene la inforamción de la empresa.
     * @param company
     * @return
     */
    public static BusinessDetailFragment newInstance(Company company) {
        BusinessDetailFragment frg = new BusinessDetailFragment();
        Bundle argumentos = new Bundle();
        argumentos.putParcelable(ARG_COMPANY, company);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentBusinessDetailBinding.inflate(inflater, container, false);
        mBinding.setDa(this);
        mRepository = RepositoryCompanyImpl.getInstance(BD.getInstance(getActivity()).getDb());
        getData();
        return mBinding.getRoot();
    }

    private void getData() {
        if (getArguments() != null) {
            mCompany = getArguments().getParcelable(ARG_COMPANY);
            auxOldName = mCompany.getName();
        } else {
            mCompany = new Company();
        }
        mBinding.setCompany(mCompany);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void onPictureChanged(CharSequence c, int start, int before, int count) {
        if (TextUtils.isEmpty(mBinding.txtPicture.getText())) {
            mBinding.txtPicture.setText("URL EMPTY");
        }
    }


    public void onNameChanged(CharSequence c, int start, int before, int count) {
        if (TextUtils.isEmpty(mBinding.txtNameCompany.getText())) {
            mBinding.fab.setVisibility(View.INVISIBLE);
        } else {
            mBinding.fab.setVisibility(View.VISIBLE);
        }
    }

    public void fabSaveOnClick(View view) {

        //Comprobamos si se va a actualizar o a hacer uno nuevo, si el id es 0 es uno nuevo sino ya existe.
        if(mCompany.getId() == 0) {
            //Si queremos un nuevo estudiante el nombre no puede existir
            if (mRepository.getCompanyById(mBinding.txtNameCompany.getText().toString()) == null) {
                updateStudent(view);
            } else {
                Toast.makeText(getActivity(), R.string.error_unique_text, Toast.LENGTH_SHORT).show();
            }
        } else{
            //Comprobamos que el nombre introducido si pueda ser el suyo propio pero no uno que tenga otro estudiante
            if(mRepository.getCompanyUpdate(mBinding.getCompany().getName(), auxOldName) == null) {
                updateStudent(view);
            } else {
                Toast.makeText(getActivity(), R.string.error_unique_text, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateStudent(View view){
        mRepository.insert(mBinding.getCompany());
        hideOffKeyBoard(view, getContext());
        openBack(getActivity());
    }

}