package com.example.istarskakuharica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final List<Recipe> filteredRecipes;
    private final OnRecipeClickListener onRecipeClickListener;

    public interface OnRecipeClickListener {
        void onRecipeClick(Recipe recipe);
    }

    public RecipeAdapter(ArrayList<Recipe> recipes, OnRecipeClickListener onRecipeClickListener) {
        this.filteredRecipes = new ArrayList<>(recipes);
        this.onRecipeClickListener = onRecipeClickListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = filteredRecipes.get(position);
        holder.recipeTitle.setText(recipe.getTitle());
        holder.preparationTime.setText(recipe.getPreparation());
        holder.recipeImage.setImageResource(recipe.getImage());
        holder.itemView.setOnClickListener(v -> onRecipeClickListener.onRecipeClick(recipe));

        if (recipe.getBackground().equals("green")) {
            holder.itemView.setBackgroundResource(R.drawable.button_rounded_green);
            holder.starBackground.setBackgroundResource(R.drawable.rounded_favourite_background_green);
        } else if (recipe.getBackground().equals("blue")) {
            holder.itemView.setBackgroundResource(R.drawable.button_rounded_blue);
            holder.starBackground.setBackgroundResource(R.drawable.rounded_favourite_background_blue);
        }

        holder.favouriteIcon.setOnClickListener(v -> {
            recipe.toggleFavourite();
            notifyItemChanged(position);
        });

        holder.favouriteIcon.setImageResource(recipe.getFavourite() ? R.drawable.icon_favourite_full : R.drawable.icon_favourite_empty);
    }

    @Override
    public int getItemCount() {
        return filteredRecipes.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeImage;
        TextView recipeTitle;
        TextView preparationTime;
        ImageView favouriteIcon;
        View starBackground;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
            preparationTime = itemView.findViewById(R.id.preparationTime);
            favouriteIcon = itemView.findViewById(R.id.favoriteIcon);
            starBackground = itemView.findViewById(R.id.starBackground);
        }
    }

    public void updateRecipes(ArrayList<Recipe> newRecipes) {
        this.filteredRecipes.clear();
        this.filteredRecipes.addAll(newRecipes);
        notifyDataSetChanged();
    }
}
