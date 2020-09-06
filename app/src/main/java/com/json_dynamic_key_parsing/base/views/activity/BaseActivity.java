package com.json_dynamic_key_parsing.base.views.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.si_test.R;

public class BaseActivity extends AppCompatActivity {
    public AppCompatImageView imgBackArrow;
    public TextView textToolbarTitle;
    public ConstraintLayout progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResId) {
        View view;
        View parentView = getLayoutInflater().inflate(R.layout.base_activity, null);
        FrameLayout frameLayout = parentView.findViewById(R.id.layout_container);
        imgBackArrow = parentView.findViewById(R.id.img_back_arrow);
        textToolbarTitle = parentView.findViewById(R.id.text_toolbar_title);
        progressBar = parentView.findViewById(R.id.layout_progress_bar);

        view = getLayoutInflater().inflate(layoutResId, frameLayout, false);

        frameLayout.addView(view);
        setContentView(parentView);

        imgBackArrow.setOnClickListener(view1 -> onBackPressed());
    }

    public void setProgressBar(boolean isShow){
        try {
            if (progressBar == null) return;
            if (isShow && progressBar.getVisibility() == View.GONE) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.animate().alpha(1);
            } else if (!isShow && progressBar.getVisibility() == View.VISIBLE) {
                progressBar.animate().alpha(0).withEndAction(() -> {
                    if (progressBar != null)
                        progressBar.setVisibility(View.GONE);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setToolbarTitleName(String team1, String team2){
        textToolbarTitle.setText(team1+" vs "+team2);
    }
}
