package com.example.historicevents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
public class HistoricaEvents extends RecyclerView.Adapter<HistoricaEvents.SostenDeVisitas>{
    Context context;
    ArrayList<EventModel> events;

    public HistoricaEvents(Context context, ArrayList<EventModel> events) {
        this.context = context;
        this.events = events;
    }


    @NonNull
    @Override
    public HistoricaEvents.SostenDeVisitas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cv_row, parent, false);
        return new HistoricaEvents.SostenDeVisitas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricaEvents.SostenDeVisitas sosten, int position) {
        EventModel event = events.get(position);

        sosten.tvName.setText(event.getEventName());
        sosten.tvDate.setText(event.getEventDate());
        sosten.tvLocation.setText(event.getEventLocation());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class SostenDeVisitas extends RecyclerView.ViewHolder{
        TextView tvName, tvDate, tvLocation;
        public SostenDeVisitas(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvEventName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvLocation = itemView.findViewById(R.id.tvLocation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), tvName.getText(), Toast.LENGTH_LONG);
                }
            });
        }
    }
}
