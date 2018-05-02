package fostash.motorsettings;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditCircuitActivity extends AppCompatActivity {

    //private ObjectInputStream ois;
    //private ObjectOutputStream oos;
    private List<SettingsData> settingsDataList;
    private SettingsAdapter settingsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_circuit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText compression = findViewById(R.id.compression);
        final EditText rebound = findViewById(R.id.rebound);
        final EditText preload = findViewById(R.id.preload);
        final EditText pignon = findViewById(R.id.pignon);
        final EditText crowm = findViewById(R.id.crown);
        this.settingsDataList = new ArrayList<>();

        ListView previousSettings = findViewById(R.id.previous_settings);
        settingsAdapter = new SettingsAdapter(this, R.layout.settings_adapter, settingsDataList);
        previousSettings.setAdapter(settingsAdapter);
        settingsAdapter.sort((el1, el2) -> el2.getDate().compareTo(el1.getDate()));

        try {
            String circuit_name = getIntent().getStringExtra("circuit_name");
            System.out.println("circuit name " + circuit_name);

            FileInputStream circuitNameIS = openFileInput(circuit_name);
            ObjectInputStream ois = new ObjectInputStream(circuitNameIS);

            settingsDataList.addAll((List<SettingsData>) ois.readObject());

            if (!settingsDataList.isEmpty()) {
                SettingsData settingsData = settingsDataList.get(0);

                compression.setText(String.valueOf(settingsData.getCompression()));
                rebound.setText(String.valueOf(settingsData.getRebound()));
                preload.setText(String.valueOf(settingsData.getCompression()));
                pignon.setText(String.valueOf(settingsData.getPignon()));
                crowm.setText(String.valueOf(settingsData.getCrown()));


            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (findViewById(R.id.settings_form).getVisibility() == View.VISIBLE) {
                try {
                    String circuit_name = getIntent().getStringExtra("circuit_name");
                    System.out.println("save data for " + circuit_name);

                    settingsDataList.add(SettingsData.of(
                            Long.valueOf(compression.getText().toString()),
                            Long.valueOf(rebound.getText().toString()),
                            Long.valueOf(preload.getText().toString()),
                            Long.valueOf(pignon.getText().toString()),
                            Long.valueOf(crowm.getText().toString()),
                            new Date()
                    ));

                    compression.setText("");
                    rebound.setText("");
                    preload.setText("");
                    pignon.setText("");
                    crowm.setText("");

                    FileOutputStream circuitName = openFileOutput(circuit_name, MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream(circuitName);
                    oos.writeObject(settingsDataList);
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                settingsAdapter.notifyDataSetChanged();

                findViewById(R.id.settings_form).setVisibility(View.GONE);
                findViewById(R.id.previous_settings).setVisibility(View.VISIBLE);
            } else if (findViewById(R.id.previous_settings).getVisibility() == View.VISIBLE) {
                findViewById(R.id.settings_form).setVisibility(View.VISIBLE);
                findViewById(R.id.previous_settings).setVisibility(View.GONE);
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*String circuit_name = getIntent().getStringExtra("circuit_name");
        System.out.println("circuit name " + circuit_name);

        try {
            FileInputStream circuitNameIS = openFileInput(circuit_name);
            ObjectInputStream ois = new ObjectInputStream(circuitNameIS);

            settingsDataList.addAll((List<SettingsData>) ois.readObject());
            settingsAdapter.notifyDataSetChanged();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*try {
            ois.close();
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
