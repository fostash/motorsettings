package fostash.motorsettings;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

    private EditText compression;
    private EditText rebound;
    private EditText preload;
    private EditText pignon;
    private EditText crown;

    private List<SettingsData> settingsDataList;
    private SettingsAdapter settingsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_circuit);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initInputText();
        setButtonEvent();

        if (readSettingsHist()) {
            createListAdapter();
        } else {
            findViewById(R.id.settings_form).setVisibility(View.VISIBLE);
            findViewById(R.id.previous_settings).setVisibility(View.GONE);
            findViewById(R.id.save_settings).setVisibility(View.VISIBLE);
            findViewById(R.id.new_settings).setVisibility(View.GONE);
        }
    }

    private void createListAdapter() {
        settingsAdapter = new SettingsAdapter(this, R.layout.settings_adapter, settingsDataList);
        settingsAdapter.sort((el1, el2) -> el2.getDate().compareTo(el1.getDate()));
        final ListView previousSettings = findViewById(R.id.previous_settings);
        previousSettings.setAdapter(settingsAdapter);

    }

    private void setInputTextValue() {
        final SettingsData settingsData = settingsDataList.get(0);

        compression.setText(String.valueOf(settingsData.getCompression()));
        rebound.setText(String.valueOf(settingsData.getRebound()));
        preload.setText(String.valueOf(settingsData.getCompression()));
        pignon.setText(String.valueOf(settingsData.getPignon()));
        crown.setText(String.valueOf(settingsData.getCrown()));
    }

    private boolean readSettingsHist() {
        this.settingsDataList = new ArrayList<>();
        final String circuitNameParam = getIntent().getStringExtra(Constants.CIRCUIT_NAME_PARAM);
        try {
            Log.i(this.getClass().getName(), "read setting for circuit name param " + circuitNameParam);

            final FileInputStream circuitNameIS = openFileInput(circuitNameParam);
            final ObjectInputStream ois = new ObjectInputStream(circuitNameIS);

            settingsDataList.addAll((List<SettingsData>) ois.readObject());

            ois.close();
            circuitNameIS.close();

        } catch (IOException | ClassNotFoundException e) {
            Snackbar.make(findViewById(R.id.previous_settings), "error reading setting for circuit name param " + circuitNameParam, Snackbar.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        return !settingsDataList.isEmpty();
    }

    private void initInputText() {
        compression = findViewById(R.id.compression);
        rebound = findViewById(R.id.rebound);
        preload = findViewById(R.id.preload);
        pignon = findViewById(R.id.pignon);
        crown = findViewById(R.id.crown);
    }

    private void setButtonEvent() {
        findViewById(R.id.new_settings).setOnClickListener(this::splitView);
        findViewById(R.id.save_settings).setOnClickListener(this::saveSettings);
    }

    private void splitView(View view) {
        setInputTextValue();
        findViewById(R.id.settings_form).setVisibility(View.VISIBLE);
        findViewById(R.id.previous_settings).setVisibility(View.GONE);
        findViewById(R.id.save_settings).setVisibility(View.VISIBLE);
        findViewById(R.id.new_settings).setVisibility(View.GONE);
    }

    private void saveSettings(View view) {
        final String circuitNameParam = getIntent().getStringExtra(Constants.CIRCUIT_NAME_PARAM);
        try {
            Log.i(this.getClass().getSimpleName(), "save settings for circuit " + circuitNameParam);

            settingsDataList.add(SettingsData.of(
                    compression.getText().toString(),
                    rebound.getText().toString(),
                    preload.getText().toString(),
                    pignon.getText().toString(),
                    crown.getText().toString(),
                    new Date()
            ));

            compression.setText("");
            rebound.setText("");
            preload.setText("");
            pignon.setText("");
            crown.setText("");

            final FileOutputStream circuitNameFile = openFileOutput(circuitNameParam, MODE_PRIVATE);
            final ObjectOutputStream oos = new ObjectOutputStream(circuitNameFile);
            oos.writeObject(settingsDataList);
            oos.flush();
            oos.close();

            settingsAdapter.sort((el1, el2) -> el2.getDate().compareTo(el1.getDate()));
            settingsAdapter.notifyDataSetChanged();

            findViewById(R.id.settings_form).setVisibility(View.GONE);
            findViewById(R.id.previous_settings).setVisibility(View.VISIBLE);
            findViewById(R.id.save_settings).setVisibility(View.GONE);
            findViewById(R.id.new_settings).setVisibility(View.VISIBLE);
        } catch (IOException e) {
            Snackbar.make(view, "Error saving settings for circuit " + circuitNameParam, Snackbar.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (findViewById(R.id.settings_form).getVisibility() == View.VISIBLE && !settingsDataList.isEmpty()) {
            findViewById(R.id.settings_form).setVisibility(View.GONE);
            findViewById(R.id.previous_settings).setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}
