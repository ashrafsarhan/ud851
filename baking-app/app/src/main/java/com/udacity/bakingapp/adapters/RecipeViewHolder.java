package com.udacity.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.udacity.bakingapp.models.Recipe;
import com.udacity.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_recipeTitle)
    TextView recipeTitle;

    @BindView(R.id.iv_recipeImage)
    ImageView recipeImage;

    private RecyclerViewClickListener clickListener;

    RecipeViewHolder(View itemView, RecyclerViewClickListener mListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.clickListener = mListener;
    }

    void bindRecipe(Context context, Recipe recipe,int imageId) {
        recipeTitle.setText(recipe.getName());
        if (recipe.getImage() != null && !TextUtils.isEmpty(recipe.getImage())) {
            Glide.with(context)
                    .load(recipe.getImage())
                    .into(recipeImage);
        } else {
            Glide.with(context)
                    .load(imageId)
                    .into(recipeImage);
        }
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view,getAdapterPosition());
    }
}
