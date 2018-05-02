package fostash.motorsettings;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EditCircuitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_circuit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText compression = findViewById(R.id.compression);
        EditText rebound = findViewById(R.id.rebound);
        EditText preload = findViewById(R.id.preload);
        EditText pignon = findViewById(R.id.pignon);
        EditText crowm = findViewById(R.id.crown);

        try {
            String circuit_name = getIntent().getStringExtra("circuit_name");
            if (new File(circuit_name).exists()) {
                FileInputStream circuitNameIS = openFileInput(circuit_name);
                ObjectInputStream ois = new ObjectInputStream(circuitNameIS);

                List<SettingsData> settingsDataList = (List<SettingsData>) ois.readObject();

                System.out.println("dddd"+ settingsDataList.size());

                SettingsData settingsData = settingsDataList.get(0);

                compression.setText(settingsData.getCompression() + "");
                rebound.setText(settingsData.getRebound() + "");
                preload.setText(settingsData.getCompression() + "");
                pignon.setText(settingsData.getPignon() + "");
                crowm.setText(settingsData.getCrown() + "");

                ListView previousSettings = findViewById(R.id.previous_settings);
                SettingsAdapter settingsAdapter = new SettingsAdapter(this, R.layout.settings_adapter, settingsDataList);
                previousSettings.setAdapter(settingsAdapter);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (findViewById(R.id.settings_form).getVisibility() == View.VISIBLE) {
                try {
                    String circuit_name = getIntent().getStringExtra("circuit_name");
                    List<SettingsData> settingsDataList = new ArrayList<>();
                    if (new File(circuit_name).exists()) {
                        FileInputStream circuitNameIS = openFileInput(circuit_name);
                        ObjectInputStream ois = new ObjectInputStream(circuitNameIS);

                        settingsDataList = (List<SettingsData>) ois.readObject();
                    }

                    System.out.println("dddd"+ settingsDataList.size());

                    FileOutputStream circuitName = openFileOutput(circuit_name, MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream(circuitName);

                    settingsDataList.add(SettingsData.of(
                            Long.valueOf(compression.getText().toString()),
                            Long.valueOf(rebound.getText().toString()),
                            Long.valueOf(preload.getText().toString()),
                            Long.valueOf(pignon.getText().toString()),
                            Long.valueOf(crowm.getText().toString())
                    ));
                    oos.writeObject(settingsDataList);
                    oos.flush();
                    oos.close();
                    circuitName.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                findViewById(R.id.settings_form).setVisibility(View.GONE);
                findViewById(R.id.previous_settings).setVisibility(View.VISIBLE);
            } else if (findViewById(R.id.previous_settings).getVisibility() == View.VISIBLE) {
                findViewById(R.id.settings_form).setVisibility(View.VISIBLE);
                findViewById(R.id.previous_settings).setVisibility(View.GONE);
            }

        });
        /*fab.setOnClickListener(view -> {
            FileOutputStream circuitName = null;
            try {
                circuitName = getApplicationContext().openFileOutput(getIntent().getStringExtra("circuit_name"), MODE_PRIVATE);
                ObjectOutputStream ois = new ObjectOutputStream(circuitName);

                ois.writeObject(SettingsData.of(
                        Long.valueOf(compression.getText().toString()),
                        Long.valueOf(rebound.getText().toString()),
                        Long.valueOf(preload.getText().toString()),
                        Long.valueOf(pignon.getText().toString()),
                        Long.valueOf(crowm.getText().toString())
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/

    }
}
