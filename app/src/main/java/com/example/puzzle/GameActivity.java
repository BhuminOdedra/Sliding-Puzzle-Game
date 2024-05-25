package com.example.puzzle;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    GridView gridView;
    ArrayAdapter<String> adapter;
    ArrayList<String> tileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridView = findViewById(R.id.grid);
        for (int i = 1; i < 16; i++) {
            tileList.add(String.valueOf(i));
        }
        tileList.add(""); 

        Collections.shuffle(tileList);
        adapter = new ArrayAdapter<String>(this, R.layout.tile, tileList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tileView = (TextView) super.getView(position, convertView, parent);
                tileView.setBackgroundResource(R.drawable.tile_background);
                return tileView;
            }
        };

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moveTile(position);
            }
        });

        
        Button exitButton = findViewById(R.id.exitGameButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); 
            }
        });
    }

    private void moveTile(int position) {
        int emptyPos = tileList.indexOf("");
        
        if ((position == emptyPos - 1 && position % 4 != 3) || 
                (position == emptyPos + 1 && position % 4 != 0) || 
                (position == emptyPos - 4) || 
                (position == emptyPos + 4)) { 
            Collections.swap(tileList, position, emptyPos);
            adapter.notifyDataSetChanged(); 
        }
    }
}
