package com.example.eclip.app22_bottomsheet.pictureActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.eclip.app22_bottomsheet.R;
import com.example.eclip.app22_bottomsheet.bdd.model.User;
import com.example.eclip.app22_bottomsheet.utils.ImgUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureActivity extends AppCompatActivity {

    @BindView(R.id.lblName)
    TextView lblName; // User name
    @BindView(R.id.lblTlf)
    TextView lblTlf; // User phone
    @BindView(R.id.imgDetail)
    ImageView imgDetail; // Botton action up or down
    @BindView(R.id.lblImg)
    TextView lblImg; // User image name
    @BindView(R.id.rlPanel)
    RelativeLayout rlPanel; // RelativeLayout menu
    @BindView(R.id.imgUser)
    ImageView imgUser; // User image profile
    private BottomSheetBehavior<RelativeLayout> bsb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        RelativeLayout rlPanel = findViewById(R.id.rlPanel);
        bsb = BottomSheetBehavior.from(rlPanel);
        setupBottomSheet();
        ImgUtils.loadImg(this, imgUser, User.getInstance().getImagen());
        lblImg.setText(User.getInstance().getImagen());
        lblName.setText(User.getInstance().getName());
        lblTlf.setText(User.getInstance().getPhone());
        imgDetail.setOnClickListener(view -> expandOrCollapseBottomSheet());
    }

    /**
     * Configuration menu.
     */
    private void setupBottomSheet() {
        //bsb.setPeekHeight(getResources().getDimensionPixelSize(R.dimen
        // .bottomSheet_peekHeight));
        bsb.setHideable(false);
        bsb.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        imgDetail.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                    case BottomSheetBehavior.STATE_HIDDEN:
                        imgDetail.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                        break;
                    default:
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    /**
     * movement for menu.
     */
    private void expandOrCollapseBottomSheet() {
        if (bsb.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bsb.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (bsb.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }
}
