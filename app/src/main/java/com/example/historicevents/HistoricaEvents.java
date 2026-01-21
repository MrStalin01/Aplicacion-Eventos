package com.example.historicevents;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

        EventModel event = events.get(sosten.getBindingAdapterPosition());

        sosten.tvName.setText(event.getEventName());
        sosten.tvDate.setText(event.getEventDate());
        sosten.tvLocation.setText(event.getEventLocation());


        if (event.isCompleted()) {
            sosten.card.setCardBackgroundColor(context.getResources().getColor(R.color.Cortado));
        } else {
            sosten.card.setCardBackgroundColor(context.getResources().getColor(R.color.cafe));
        }

        sosten.itemView.setOnClickListener(v -> {
            int currentPos = sosten.getBindingAdapterPosition(); //ESto para anotar la posicion

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View alertPopUpView = inflater.inflate(R.layout.event_popup, null);

            final EditText etRespuesta = alertPopUpView.findViewById(R.id.eventLocationEt);
            //titulo a la ventana del evento
            builder.setTitle(event.getEventName())
                    .setView(alertPopUpView)
                    .setPositiveButton("Adelante", (dialog, which) -> {
                        String respuestaUsuario = etRespuesta.getText().toString().trim();
                        //guarda lo que el usuario escribio

                        if (respuestaUsuario.equalsIgnoreCase(event.getAnswer())) {

                            events.remove(currentPos);
                            notifyItemRangeChanged(currentPos, events.size());
                        } else {

                            event.setCompleted(true);
                            notifyItemChanged(currentPos);
                        }
                    })
                    .setNegativeButton("Volver", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class SostenDeVisitas extends RecyclerView.ViewHolder{
        TextView tvName, tvDate, tvLocation;

        CardView card;

        public SostenDeVisitas(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvEventName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            card = itemView.findViewById(R.id.eventCard);

        }
    }
}
