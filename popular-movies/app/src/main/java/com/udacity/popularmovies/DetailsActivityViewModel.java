package com.udacity.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.udacity.popularmovies.database.MovieRepository;
import com.udacity.popularmovies.models.Movie;

public class DetailsActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public DetailsActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }
    public boolean isFavorite(int movieId) {
        return movieRepository.isFavorite(movieId);
    }

    public void addMovieToFavorites(Movie movie) {
        movieRepository.addMovieToFavorites(movie);
    }

    public void removeMovieFromFavorites(Movie movie) {
        movieRepository.deleteFavoriteMovie(movie);
    }

    public void updatFavoriteMovie(int movieId, boolean isFavorite) {
        movieRepository.updateFavoriteMovie(movieId, isFavorite);
    }
}
