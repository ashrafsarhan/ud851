package com.udacity.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.R;
import com.udacity.popularmovies.models.Movie;

class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Context mContext;

    @BindView(R.id.tvMovieTitle)
    TextView movieTitle;

    @BindView(R.id.ivMovieImage)
    ImageView coverImage;

    @BindView(R.id.tvReleaseDate)
    TextView releaseDate;

    private RecyclerViewClickListener clickListener;

    MovieViewHolder(View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
        this.clickListener = listener;
    }

    void bindMovie(Movie movie) {
        movieTitle.setText(movie.getOriginalTitle());
        releaseDate.setText(movie.getReleaseDate());
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(new OkHttp3Downloader(mContext));
        builder.build().load(mContext.getResources().getString(R.string.IMAGE_BASE_URL) + movie.getPosterPath())
                .placeholder((R.drawable.gradient_background))
                .error(R.drawable.ic_launcher_background)
                .into(coverImage);
    }

    @Override
    public void onClick(View v) {
        clickListener.onClick(v, getAdapterPosition());
    }
}
