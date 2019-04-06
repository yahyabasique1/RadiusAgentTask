package com.isma.dell.radiusagenttask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class OtherFacilitiesActivity extends AppCompatActivity implements MainContract.MainView{
    private MainContract.presenter presenter;
    RecyclerView recyclerView;
    AppCompatTextView tvFacilityTitle;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String facilityId=getIntent().getStringExtra(Constant.FACILITY_ID);
        String optionId=getIntent().getStringExtra(Constant.OPTION_ID);



        context=this;
        recyclerView=findViewById(R.id.rvFacility);
        tvFacilityTitle=findViewById(R.id.tvFacilityTitle);
        tvFacilityTitle.setText("Other Facilities");

        presenter=new MainPresenter(this);
//        presenter.requestDataFromServer();
        presenter.requestDataFromDb("Other facilities",facilityId,optionId);




    }


    @Override
    public void setDataToRecyclerView(List<FacilitiesTable> noticeArrayList) {
        FaciltyAdapter faciltyAdapter=new FaciltyAdapter(context,noticeArrayList, recyclerViewOnClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(faciltyAdapter);
        faciltyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(OtherFacilitiesActivity.this,NumOfRoomsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }


    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d("ERROROROR",throwable.getLocalizedMessage());

    }

    MainContract.RecyclerViewOnClick recyclerViewOnClick=new MainContract.RecyclerViewOnClick() {
        @Override
        public void onClick(FacilitiesTable facilityModel) {
            Intent intent=new Intent(context, DisplayChosenOptionInfo.class);
            intent.putExtra(Constant.OTHER_FACILITY,facilityModel.getOptionName());
            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            finish();


        }
    };


}
