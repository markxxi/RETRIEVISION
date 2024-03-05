package com.example.retrievision;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button RLO = (Button) view.findViewById(R.id.reportLostObjectButton);
            RLO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ReportLostObjectActivity.class);
                    startActivity(intent);
                }
            });
            Button RFO = (Button) view.findViewById(R.id.reportFoundObjectButton);
            RFO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ReportFoundObjectActivity.class);
                    startActivity(intent);
                }
            });
        return view;
    }
}