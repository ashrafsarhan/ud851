package com.udacity.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.R;
import com.udacity.popularmovies.models.Cast;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CreditsViewHolder> {

    private Context context;
    private List<Cast> castList;

    public CastAdapter(Context context, List<Cast> Casts) {
        this.context = context;
        this.castList = Casts;
    }

    @NonNull
    @Override
    public CreditsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CreditsViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_cast_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CreditsViewHolder holder, int position) {
        holder.bindCast(castList.get(position));
    }

    @Override
    public int getItemCount() {
        return castList != null ? castList.size() : 0;
    }

    class CreditsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cast_thumbnail)
        ImageView imageView;
        @BindView(R.id.cast_name)
        TextView name;

        CreditsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindCast(Cast cast) {
            if (cast.getCharacter() != null) {
                StringBuilder castName = new StringBuilder().append(cast.getName()).append(" (").append(cast.getCharacter()).append(")");
                name.setText(castName);
            } else {
                name.setText(cast.getName());
            }

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(context.getResources().getString(R.string.IMAGE_BASE_URL) + cast.getProfileUrl())
                    .placeholder((R.drawable.gradient_background))
                    .error(R.drawable.ic_user_place_holder)
                    .into(imageView);
        }
    }
}
