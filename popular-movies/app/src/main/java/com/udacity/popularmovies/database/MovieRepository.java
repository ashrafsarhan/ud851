package com.udacity.popularmovies.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.udacity.popularmovies.models.Movie;
import com.udacity.popularmovies.utils.AppExecutors;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private AppExecutors appExecutors;

    private LiveData<List<Movie>> movies;

    public MovieRepository(Application application) {
        movieDao = MovieDatabase.getInstance(application).movieDao();
        movies = movieDao.loadAllFavoriteMovies();

        appExecutors = AppExecutors.getExecutorInstance();
    }

    public LiveData<List<Movie>> loadAllFavoriteMovies() {
        return movies;
    }

    public boolean isFavorite(int movieId) {
        return movieDao.isFavorite(movieId);
    }

    public void updateFavoriteMovie(int movieId, boolean isFavorite) {
        appExecutors.getDiskIO().execute(() -> {
            movieDao.updateFavoriteMovie(movieId, isFavorite);
        });
    }

    public void addMovieToFavorites(Movie movie) {
        appExecutors.getDiskIO().execute(() -> movieDao.insertFavoriteMovie(movie));
    }

    public void deleteFavoriteMovie(Movie movie) {
        appExecutors.getDiskIO().execute(() -> movieDao.deleteFavoriteMovie(movie));
    }
}
