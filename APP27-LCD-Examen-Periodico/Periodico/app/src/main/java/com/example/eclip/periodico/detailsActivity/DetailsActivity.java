package com.example.eclip.periodico.detailsActivity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.eclip.periodico.R;
import com.example.eclip.periodico.Utils.ImgUtils;
import com.example.eclip.periodico.bdd.local.BD;
import com.example.eclip.periodico.bdd.model.Periodico;
import com.example.eclip.periodico.bdd.repository.RepositoryPeriodico;
import com.example.eclip.periodico.bdd.repository.RepositoryPeriodicoImpl;
import com.example.eclip.periodico.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding mBinding;
    private Periodico mPeriodico;
    private RepositoryPeriodico mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPeriodico = new Periodico();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        mBinding.setDetalles(this);
        mBinding.setPeriodico(mPeriodico);
        setSupportActionBar(mBinding.toolbar);
        mRepository = RepositoryPeriodicoImpl.getInstance(BD.getInstance(this).getDb());
    }

    // Se almacena el nuevo periodico en la base de datos si se cumplen los requesitos
    public void savePeriodico(View view) {
        //Controlamos que el nombre y la url sea obligatorio
        if (!TextUtils.isEmpty(mBinding.txtName.getText()) && !TextUtils.isEmpty(mBinding.txtUrl.getText())) {
            mRepository.insert(mPeriodico);
            onBackPressed();
        } else
            Toast.makeText(this, R.string.error_campos_necesarios, Toast.LENGTH_SHORT).show();
    }

    // El texto del icono nunca puede quedarse vacio porq al ser un metodo que se ejecuta cada vez que se escribe algo en
    // el txtIcon para cambiar la imagen, picaso no puede cargar una url completamente vacia
    public void actualizarImg(CharSequence c, int start, int before, int count) {
        if (TextUtils.isEmpty(mBinding.txtIcon.getText()))
            mBinding.txtIcon.setText(R.string.no_url);
        ImgUtils.loadImgHeader(this, mBinding.imgHeader, mBinding.txtIcon.getText().toString());
    }
}
