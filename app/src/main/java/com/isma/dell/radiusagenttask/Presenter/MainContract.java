package com.isma.dell.radiusagenttask.Presenter;

import com.isma.dell.radiusagenttask.ResponsePojo.DataModel;
import com.isma.dell.radiusagenttask.RoomDb.FacilitiesTable;

import java.util.List;

public class MainContract {


   public interface MainView {

        void setDataToRecyclerView(List<FacilitiesTable> noticeArrayList);

        void onResponseFailure(Throwable throwable);

    }

    public interface GetFacilityIntractor {

      public   interface OnFinishedListener {
            void onFinished(DataModel dataModel);
            void onFailure(Throwable t);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }

    public interface GetFacilityDataSource{
       void getFacilitiesArrayList(List<FacilitiesTable> dataModel);
        List<FacilitiesTable> fetchRowFromDb(String facilityType, String facilityId, String optionId);
        void storeDataInDb(DataModel dataModel);



    }

    public interface RecyclerViewOnClick{
       void onClick(FacilitiesTable facilitiesTable);
    }

  public   interface presenter{

        void onDestroy();

        void requestDataFromServer();
      void requestDataFromDb(String faciltyType, String facilityId, String optionId);


    }

}
