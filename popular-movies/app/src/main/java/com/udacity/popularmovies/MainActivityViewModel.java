package com.udacity.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.udacity.popularmovies.database.MovieRepository;
import com.udacity.popularmovies.models.Movie;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();
    private LiveData<List<Movie>> favMovies;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        MovieRepository movieRepository = new MovieRepository(application);
        Log.d(TAG, "Retrieving tasks from database via ViewModel");
        favMovies = movieRepository.loadAllFavoriteMovies();
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return favMovies;
    }
}
