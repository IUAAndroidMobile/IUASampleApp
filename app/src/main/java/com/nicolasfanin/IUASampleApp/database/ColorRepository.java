package com.nicolasfanin.IUASampleApp.database;

import android.app.Application;
import android.os.AsyncTask;

import com.nicolasfanin.IUASampleApp.data.Color;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ColorRepository {

    private ColorDao colorDao;
    private List<Color> allColors;

    public ColorRepository(Application application) {
        IUARoomDatabase database = IUARoomDatabase.getDatabase(application);
        colorDao = database.colorDao();
    }

    public List<Color> getAllColors() {
        try {
            allColors = new getColorsAsyncTask(colorDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allColors;
    }

    public void insert(Color color) {
        new insertColorsAsyncTask(colorDao).execute(color);
    }

    private static class insertColorsAsyncTask extends AsyncTask<Color, Void, Void> {

        private ColorDao asyncTaskColorDao;

        insertColorsAsyncTask(ColorDao colorDao) {
            asyncTaskColorDao = colorDao;
        }

        @Override
        protected Void doInBackground(Color... colors) {
            asyncTaskColorDao.insert(colors[0]);
            return null;
        }
    }

    private static class getColorsAsyncTask extends AsyncTask<Void, Void, List<Color>> {
        private ColorDao asyncTaskColorDao;

        getColorsAsyncTask(ColorDao colorDao) {
            asyncTaskColorDao = colorDao;
        }

        @Override
        protected List<Color> doInBackground(Void... voids) {
            return asyncTaskColorDao.getAllColorsOrdered();
        }
    }
}
