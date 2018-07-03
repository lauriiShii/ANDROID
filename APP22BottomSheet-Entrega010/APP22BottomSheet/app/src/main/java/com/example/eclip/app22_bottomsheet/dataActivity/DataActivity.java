package com.example.eclip.app22_bottomsheet.dataActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.eclip.app22_bottomsheet.R;
import com.example.eclip.app22_bottomsheet.bdd.model.User;
import com.example.eclip.app22_bottomsheet.mainActivity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.eclip.app22_bottomsheet.utils.Constants.TAG_BOTTOMSHEET_FRAGMENT;

public class DataActivity extends AppCompatActivity {

    @BindView(R.id.txtName)
    EditText txtName; // User name
    @BindView(R.id.txtTlf)
    EditText txtTlf; // User phone
    @BindView(R.id.btnOpcion)
    Button btnOpcion; // Button for show menu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
        txtName.setText(User.getInstance().getName());
        txtTlf.setText(User.getInstance().getPhone());
    }

    /**
     * Instance and show the menu
     * @param name
     * @param phone
     */
    private void showBottomSheetDialogFragment(String name, String phone) {
        MenuBottomSheetDialogFragment dialogFragment = MenuBottomSheetDialogFragment.newInstance(
                name, phone);
        dialogFragment.show(getSupportFragmentManager(), TAG_BOTTOMSHEET_FRAGMENT);
    }


    /**
     * Show menu
     */
    @OnClick(R.id.btnOpcion)
    public void onViewClicked() {
        showBottomSheetDialogFragment(txtName.getText().toString(), txtTlf.getText().toString());
    }

    /**
     * The bottom button must do the same as the inheritance button. Therefore,
     * its function is canceled, assigning what it must do.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity( new Intent(this, MainActivity.class));
        finish();
    }
}
