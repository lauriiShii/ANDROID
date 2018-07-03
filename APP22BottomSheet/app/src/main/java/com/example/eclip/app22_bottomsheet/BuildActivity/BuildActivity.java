package com.example.eclip.app22_bottomsheet.BuildActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.eclip.app22_bottomsheet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuildActivity extends AppCompatActivity {

    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.btnOpcion)
    Button btnOpcion;
    private static final String TAG_BOTTOMSHEET_FRAGMENT = "TAG_BOTTOMSHEET_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build);
        ButterKnife.bind(this);
    }

    private void showBottomSheetDialogFragment(String name) {
        MenuBottomSheetDialogFragment dialogFragment = MenuBottomSheetDialogFragment.newInstance(
                name);
        dialogFragment.show(getSupportFragmentManager(), TAG_BOTTOMSHEET_FRAGMENT);
    }


    @OnClick(R.id.btnOpcion)
    public void onViewClicked() {

        showBottomSheetDialogFragment(txtName.getText().toString());
    }
}
