package com.example.istarskakuharica;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecipeAdapter recipeAdapter;
    private ArrayList<Recipe> recipes;
    private ArrayList<Recipe> filteredRecipes;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        SearchView searchView = findViewById(R.id.searchView);
        RecyclerView recipeView = findViewById(R.id.recipesView);
        emptyView = findViewById(R.id.emptyView);

        recipeView.setLayoutManager(new GridLayoutManager(this, 2));

        recipes = new ArrayList<>();
        filteredRecipes = new ArrayList<>();
        recipes.add(new Recipe("Dagnje na buzaru", new String[]{"1."}, R.drawable.recipe_image_dagnje, "45 min", "blue"));
        recipes.add(new Recipe("Škampi na buzaru", new String[]{"2."}, R.drawable.recipe_image_skampi, "30 min", "blue"));
        recipes.add(new Recipe("Fuži s tartufima", new String[]{"3."}, R.drawable.recipe_image_fuzitartufi, "60 min", "green"));
        recipes.add(new Recipe("Maneštra", new String[]{"4."}, R.drawable.recipe_image_manestra, "50 min", "green"));
        recipes.add(new Recipe("Jota", new String[]{"5."}, R.drawable.recipe_image_jota, "40 min", "green"));
        recipes.add(new Recipe("Žgvacet", new String[]{"6."}, R.drawable.recipe_image_zgvacet, "40 min", "green"));

        recipeAdapter = new RecipeAdapter(recipes, recipe -> {
            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("recipeTitle", recipe.getTitle());
            intent.putExtra("recipeSteps", recipe.getSteps());
            startActivity(intent);
        });
        recipeView.setAdapter(recipeAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterRecipesByQuery(newText);
                return true;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void filterRecipesByQuery(String query) {
        filteredRecipes.clear();
        if (query.isEmpty()) {
            filteredRecipes.addAll(recipes);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Recipe recipe : recipes) {
                if (recipe.getTitle().toLowerCase().contains(lowerCaseQuery)) {
                    filteredRecipes.add(recipe);
                }
            }
        }
        recipeAdapter.updateRecipes(filteredRecipes);

        if (filteredRecipes.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }
    }
}
