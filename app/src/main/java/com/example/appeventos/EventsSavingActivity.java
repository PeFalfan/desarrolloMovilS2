package com.example.appeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appeventos.models.EventModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventsSavingActivity extends AppCompatActivity {

    CalendarView calendarView;
    Calendar calendar;
    Button addDateBtn, deleteAllBtn;
    String selectedDate;

    ListView eventsListView;
    ArrayAdapter<EventModel> eventsAdapter;
    ArrayAdapter<String> adapterForTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_events_saving);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        calendarView = findViewById(R.id.calendar_view);
        calendar = Calendar.getInstance();
        addDateBtn = findViewById(R.id.add_date_btn);
        selectedDate = "";
        eventsListView = findViewById(R.id.events_list_view);

//        events = loadEventsList();
        List<EventModel> events = new ArrayList<>();
        EventModel newEvent = new EventModel();
        newEvent.setTitle("Cumpleaños  Radahn");
        newEvent.setId(1);
        newEvent.setDateToRemember(new Date());

        EventModel newEvent2 = new EventModel();
        newEvent2.setTitle("Cumpleaños  Miquella");
        newEvent2.setId(2);
        newEvent2.setDateToRemember(new Date());

        events.add(newEvent);
        events.add(newEvent2);

        List<String> titles = new ArrayList<>();
        titles.add("Cumpleaños  Radahn");
        titles.add("Cumpleaños  Miquella");

        adapterForTitles = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);

        eventsListView.setAdapter(adapterForTitles);

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = titles.get(i);
                Toast.makeText(EventsSavingActivity.this, title, Toast.LENGTH_SHORT).show();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selectedDate = day + "/" + (month+1) + "/" + year;
                Toast.makeText(EventsSavingActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
            }
        });

        addDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // aqui navegaremos a otra lista, con el formulario correspondiente para el evento.
                // enviaremos la fecha seleccionada como parametro.
                Intent intent = new Intent(EventsSavingActivity.this, EventRegisterActivity.class);
                Bundle extras = new Bundle();
                extras.putString("selectedDate", selectedDate);
                intent.putExtras(extras);
                EventsSavingActivity.this.startActivity(intent);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO: Necesito metodo para cargar los eventos desde la base de datos y mapearlos a nuestro listview de eventos
    public List<EventModel> loadEventsList(){
        List<EventModel> events = new ArrayList<>();
        EventModel newEvent = new EventModel();
        newEvent.setTitle("Cumpleaños  Radahn");
        newEvent.setId(1);
        newEvent.setDateToRemember(new Date());

        EventModel newEvent2 = new EventModel();
        newEvent2.setTitle("Cumpleaños  Miquella");
        newEvent2.setId(2);
        newEvent2.setDateToRemember(new Date());

        events.add(newEvent);
        events.add(newEvent2);

        return  events;
    }

}