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
import com.isma.dell.radiusagenttask.WorkManager.FacilitiesWorkManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity implements MainContract.MainView{

    private MainContract.presenter presenter;//Presenter is used to trigger the event and perform set of operation

    RecyclerView recyclerView;
    Context context;
    AppCompatTextView tvFacilityTitle;//To display the facility type

    PeriodicWorkRequest periodicWorkRequest;//To make api call once in a day and update the local databse

    Constraints constraints;
    SharedPreferences sharedPreferences;//To store the user selected info
    String myPreferencess="mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        sharedPreferences=getSharedPreferences(myPreferencess,Context.MODE_PRIVATE);

        recyclerView=findViewById(R.id.rvFacility);
        tvFacilityTitle=findViewById(R.id.tvFacilityTitle);
        tvFacilityTitle.setText("Property Type");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            //constraint is used to define set of conditions before performing any functions,ex whether internet is require or not etc.
            constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
        }

         periodicWorkRequest =
                new PeriodicWorkRequest.Builder(FacilitiesWorkManager.class, 24, TimeUnit.HOURS)
                        .setConstraints(constraints)
                        .build();

        //Workmanager performs the operations in background based on the set of constraint define,it performs operation only once or periodically bsed on the request
        WorkManager.getInstance().
                enqueueUniquePeriodicWork("dbdata", ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest);




        presenter=new MainPresenter(this);
//        presenter.requestDataFromServer();

        //Fetching the data from db to populate the recycler view.
        presenter.requestDataFromDb("Property Type",null,null);




    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    //This listener provides arraylist in order to populate the recycler view
    @Override
    public void setDataToRecyclerView(List<FacilitiesTable> noticeArrayList) {

        FaciltyAdapter faciltyAdapter=new FaciltyAdapter(context,noticeArrayList,recyclerViewOnClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(faciltyAdapter);
        faciltyAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d("ERROROROR",throwable.getLocalizedMessage());

    }

    MainContract.RecyclerViewOnClick recyclerViewOnClick=new MainContract.RecyclerViewOnClick() {
        @Override
        public void onClick(FacilitiesTable facilityModel) {
            Intent intent=new Intent(context, NumOfRoomsActivity.class);
            intent.putExtra(Constant.FACILITY_ID,facilityModel.getFacilityId());
            intent.putExtra(Constant.OPTION_ID,facilityModel.getOptionId());

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(Constant.PROPERTY_NAME,facilityModel.getOptionName());
            editor.apply();

            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            finish();


        }
    };

    @Override
    protected void onStart() {
        super.onStart();

    }


}
