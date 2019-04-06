package com.isma.dell.radiusagenttask.RoomDb;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.isma.dell.radiusagenttask.ResponsePojo.Exclusion;


@Database(entities = {Exclusion.class,FacilitiesTable.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase mINSTANCE;

    public abstract ExclusionDao userDao();
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE IF NOT EXISTS 'facilities_table' (`name` TEXT NOT NULL, PRIMARY KEY(`name`))");

            // Since we didn't alter the table, there's nothing else to do here.
        }
    };


    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };



    public static AppDatabase getAppDatabase(Context context) {
        if (mINSTANCE == null) {
            mINSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "demo-database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return mINSTANCE;
    }

    public static void destroyInstance() {
        mINSTANCE = null;
    }
}