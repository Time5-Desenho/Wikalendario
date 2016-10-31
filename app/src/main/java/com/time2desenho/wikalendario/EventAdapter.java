package com.time2desenho.wikalendario;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventsViewHolder> {

    private Context context;
    private ArrayList<Event> events;
    private EventAdapter.EventOnClickListener eventOnClickListener;

    private final String TAG = "EventAdapter";

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public EventAdapter.EventOnClickListener getEventOnClickListener() {
        return eventOnClickListener;
    }

    public void setEventOnClickListener(EventAdapter.EventOnClickListener eventOnClickListener) {
        this.eventOnClickListener = eventOnClickListener;
    }

    public EventAdapter(Context context, ArrayList<Event> events, EventAdapter.EventOnClickListener eventOnClickListener){
        setContext(context);
        setEvents(events);
        setEventOnClickListener(eventOnClickListener);
    }

    @Override
    public int getItemCount(){
        int numberOfEvents;
        if(events != null){
            numberOfEvents = events.size();
        }else{
            numberOfEvents = 0;
        }

        return numberOfEvents;
    }

    @Override
    public void onBindViewHolder(final EventAdapter.EventsViewHolder viewHolder, final int position){
        Event event = events.get(position);

        String descriptionText = "Descrição: " + event.getEventDescription();
        Log.d(TAG, descriptionText);
        viewHolder.textViewDescription.setText(descriptionText);

        String titleText = event.getEventTitle();
        Log.d(TAG, titleText);
        viewHolder.textViewTitle.setText(titleText);

        if(eventOnClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    eventOnClickListener.onClickEvent(viewHolder.itemView, position);
                }
            } );
        }
    }

    @Override
    public EventAdapter.EventsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        //Inflating view and creating viewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_event, viewGroup, false);
        EventAdapter.EventsViewHolder eventsViewHolder = new EventAdapter.EventsViewHolder(view);

        return eventsViewHolder;
    }

    // Callback interface to show list events
    public interface EventOnClickListener{
        public void onClickEvent(View view, int position);
    }

    // ViewHolder that has reference to layout views
    public class EventsViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewDescription;
        private TextView textViewTitle;
        private CardView cardView;

        public EventsViewHolder(View view){
            super(view);

            textViewDescription = (TextView) view.findViewById(R.id.text_description);
            textViewTitle = (TextView) view.findViewById(R.id.text_title);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
