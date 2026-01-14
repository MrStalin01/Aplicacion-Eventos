package com.example.historicevents;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<EventModel> eventosHistoricos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.historicEventsRecycler);
        fillTheModels();

        HistoricaEvents adaptador = new HistoricaEvents(this, eventosHistoricos);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void fillTheModels(){
        String[] eventNames = getResources().getStringArray(R.array.historic_events_names);
        String[] eventDates = getResources().getStringArray(R.array.historic_event_dates);
        String[] eventLocation = getResources().getStringArray(R.array.historic_events_lugares);

        for (int i = 0; i < eventNames.length; i++) {
            eventosHistoricos.add(new EventModel(eventNames[i], eventDates[i], eventLocation[i]));

        }
    }
}