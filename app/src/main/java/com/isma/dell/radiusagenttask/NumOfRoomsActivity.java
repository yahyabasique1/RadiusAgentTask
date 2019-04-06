package com.isma.dell.radiusagenttask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import com.isma.dell.radiusagenttask.Adapter.FaciltyAdapter;
import com.isma.dell.radiusagenttask.AppUtil.Constant;
import com.isma.dell.radiusagenttask.Presenter.MainContract;
import com.isma.dell.radiusagenttask.Presenter.MainPresenter;
import com.isma.dell.radiusagenttask.RoomDb.FacilitiesTable;

import java.util.List;

public class NumOfRoomsActivity extends AppCompatActivity implements MainContract.MainView{
    private MainContract.presenter presenter;
    RecyclerView recyclerView;
    Context context;
    AppCompatTextView tvFacilityTitle;
    private ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    String myPreferencess="mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String facilityId=getIntent().getStringExtra(Constant.FACILITY_ID);
        String optionId=getIntent().getStringExtra(Constant.OPTION_ID);

        sharedPreferences=getSharedPreferences(myPreferencess,Context.MODE_PRIVATE);



        context=this;
        recyclerView=findViewById(R.id.rvFacility);
        tvFacilityTitle=findViewById(R.id.tvFacilityTitle);
        tvFacilityTitle.setText("No Of Rooms");


        presenter=new MainPresenter(this);
//        presenter.requestDataFromServer();
        presenter.requestDataFromDb("Number of Rooms",facilityId,optionId);


    }


    MainContract.RecyclerViewOnClick recyclerViewOnClick=new MainContract.RecyclerViewOnClick() {
        @Override
        public void onClick(FacilitiesTable facilityModel) {
            Intent intent=new Intent(NumOfRoomsActivity.this, OtherFacilitiesActivity.class);
            intent.putExtra(Constant.FACILITY_ID,facilityModel.getFacilityId());
            intent.putExtra(Constant.OPTION_ID,facilityModel.getOptionId());
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(Constant.NO_OF_ROOMS,facilityModel.getOptionName());
            editor.apply();

            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            finish();

        }
    };

    @Override
    public void setDataToRecyclerView(List<FacilitiesTable> noticeArrayList) {
        FaciltyAdapter faciltyAdapter=new FaciltyAdapter(context,noticeArrayList, recyclerViewOnClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(faciltyAdapter);
        faciltyAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d("ERROROROR",throwable.getLocalizedMessage());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(NumOfRoomsActivity.this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }


}
