package com.example.istarskakuharica;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        TextView recipeTitle = findViewById(R.id.recipeTitle);
        TextView recipeSteps = findViewById(R.id.recipeSteps);

        String recipeName = getIntent().getStringExtra("recipeTitle");
        String[] steps = getIntent().getStringArrayExtra("recipeSteps");

        recipeTitle.setText(recipeName);
        StringBuilder stepsText = new StringBuilder();
        assert steps != null;
        for (String step : steps) {
            stepsText.append("- ").append(step).append("\n");
        }
        recipeSteps.setText(stepsText.toString());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}