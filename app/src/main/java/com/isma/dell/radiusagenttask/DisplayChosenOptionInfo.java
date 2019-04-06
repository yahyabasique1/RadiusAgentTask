package com.isma.dell.radiusagenttask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.isma.dell.radiusagenttask.AppUtil.Constant;

public class DisplayChosenOptionInfo extends AppCompatActivity {
    AppCompatTextView tvNumOfRooms,tvPropertyType,tvOtherFacilties;
    AppCompatButton btnSubmit;
    SharedPreferences sharedPreferences;
    String myPreferencess="mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_chosen_option_info);
        initViews();

        sharedPreferences=getSharedPreferences(myPreferencess, Context.MODE_PRIVATE);

        String propertyName=sharedPreferences.getString(Constant.PROPERTY_NAME,"");
        String otherFacility=getIntent().getStringExtra(Constant.OTHER_FACILITY);
        String noOfRooms=sharedPreferences.getString(Constant.NO_OF_ROOMS,"");

        tvPropertyType.setText(propertyName);
        tvOtherFacilties.setText(otherFacility);
        tvNumOfRooms.setText(noOfRooms);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(DisplayChosenOptionInfo.this,OtherFacilitiesActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }


    private void initViews() {
        tvNumOfRooms=findViewById(R.id.tvNumberOfRooms);
        tvOtherFacilties=findViewById(R.id.tvOtherFacilties);
        tvPropertyType=findViewById(R.id.tvPropertyType);

        btnSubmit=findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DisplayChosenOptionInfo.this, "Thank you", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DisplayChosenOptionInfo.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();

            }
        });

    }
}
