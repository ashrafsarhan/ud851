package com.udacity.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.popularmovies.models.Movie;
import com.udacity.abhijithsreekar.popularmovies.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_details_moviePoster)
    ImageView moviePoster;

    @BindView(R.id.tv_details_MovieTitle)
    TextView movieTitle;

    @BindView(R.id.tv_details_Language_val)
    TextView movieLanguage;

    @BindView(R.id.tv_details_plot)
    TextView moviePlot;

    @BindView(R.id.tv_details_releaseDate_val)
    TextView movieReleaseDate;

    @BindView(R.id.tv_details_voteAverage_val)
    TextView movieVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();

        ButterKnife.bind(this);
        bindSelectedMovieData();
    }

    private void bindSelectedMovieData() {
        Intent intent = getIntent();
        Movie selectedMovie = intent.getParcelableExtra("Movie");

        movieTitle.setText(selectedMovie.getTitle());
        movieLanguage.setText(selectedMovie.getOriginalLanguage());
        moviePlot.setText(selectedMovie.getOverview());
        movieReleaseDate.setText(selectedMovie.getReleaseDate());
        movieVoteAverage.setText(String.format("%s", selectedMovie.getVoteAverage()));

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load(this.getResources().getString(R.string.IMAGE_BASE_URL) + selectedMovie.getBackdropPath())
                .placeholder((R.drawable.gradient_background))
                .error(R.drawable.ic_launcher_background)
                .into(moviePoster);
    }
}
