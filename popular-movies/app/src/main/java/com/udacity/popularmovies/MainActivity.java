package com.udacity.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.popularmovies.adapters.MovieAdapter;
import com.udacity.popularmovies.service.MovieService;
import com.udacity.popularmovies.models.Movie;
import com.udacity.popularmovies.models.MovieResponse;
import com.udacity.popularmovies.utils.APIClient;
import com.udacity.abhijithsreekar.popularmovies.R;
import com.udacity.popularmovies.utils.MovieFilter;
import com.udacity.popularmovies.utils.NetworkUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.rv_main)
    public RecyclerView rvMain;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    private static Retrofit retrofit;
    private static String API_KEY;
    public List<Movie> movies;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.popular_movies);
        API_KEY = getResources().getString(R.string.API_KEY);

        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);
        getMovies(MovieFilter.POPULAR);
    }

    // TODO - Implement savedInstancestate and restoreInstanceState

    private void getMovies(MovieFilter movieFilter) {
        if (NetworkUtil.getInstance().isNetworkAvailable(this)) {
            if (retrofit == null) {
                retrofit = APIClient.getRetrofitInstance();
            }
            MovieService movieService = retrofit.create(MovieService.class);
            Call<MovieResponse> call = null;
            switch (movieFilter) {
                case POPULAR:
                    call = movieService.getPopularMovies(API_KEY, getResources().getString(R.string.LANGUAGE), currentPage);
                    break;
                case TOP_RATED:
                    call = movieService.getTopRatedMovies(API_KEY, getResources().getString(R.string.LANGUAGE), currentPage);
                    break;
            }
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    if (response.isSuccessful()) {
                        progressBar.setVisibility(View.INVISIBLE);
                        movies = response.body().getResults();
                        if (movies != null) {
                            generateMovieList(movies);
                            Log.d(TAG, "Number of popular movies received: " + movies.size());
                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.i("Connection Status", "Not available");
        }
    }

    private void generateMovieList(final List<Movie> results) {
        MovieAdapter adapter = new MovieAdapter(this, results, new MovieAdapter.MovieItemClickListener() {
            @Override
            public void onMovieItemClick(int clickedItemIndex) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("Movie", results.get(clickedItemIndex));
                startActivity(intent);
            }
        });

        initializeGridView(adapter);
    }

    private void initializeGridView(MovieAdapter adapter) {
        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(new GridLayoutManager(this, 2));
        rvMain.setAdapter(adapter);

        // TODO - Implement Pagination (onScrollListener)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.topRated:
                getMovies(MovieFilter.TOP_RATED);
                setTitle(R.string.toprated_movies);
                break;
            case R.id.popular:
                getMovies(MovieFilter.POPULAR);
                setTitle(R.string.popular_movies);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
