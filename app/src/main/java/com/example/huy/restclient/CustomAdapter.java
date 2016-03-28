package com.example.huy.restclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 51202 on 3/23/2016.
 */
public class CustomAdapter extends ArrayAdapter<User> {
    public ArrayList<User> persons_list;
    public CustomAdapter(Context context, ArrayList<User> persons) {
        super(context, 0, persons);
        persons_list = persons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User person = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.userlistview, parent, false);
        }
        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView username = (TextView) convertView.findViewById(R.id.username);
        TextView password = (TextView) convertView.findViewById(R.id.password);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView email = (TextView) convertView.findViewById(R.id.email);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        // Populate the data into the template view using the data object
        id.setText( "ID: " + String.valueOf(person.getId()) + " ;");
        username.setText("Username: " + person.getUsername() + " ;");
        email.setText("Email: " + person.getEmail() + " ;");
        password.setText( "Password: " + person.getPassword() + " ;");
        name.setText("Name: " + person.getName() + " ;");
        status.setText("Status: " + String.valueOf(person.getStatus()) + " ;");
        // Return the completed view to render on screen
        return convertView;
    }

}
