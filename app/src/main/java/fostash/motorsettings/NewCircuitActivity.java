package fostash.motorsettings;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NewCircuitActivity extends AppCompatActivity {

    private EditText circuitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_circuit);

        circuitName = findViewById(R.id.circuit_name);
        final FloatingActionButton save = findViewById(R.id.save_circuit);
        save.setOnClickListener(this::save);
    }

    private void save(View view) {
        try {
            final String circuiName = circuitName.getText().toString();
            final FileOutputStream fileOutputStream = openFileOutput(circuiName, MODE_PRIVATE);
            final ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(new ArrayList<SettingsData>());
            oos.flush();
            oos.close();
            fileOutputStream.close();
            final Intent editCircuitIntent = new Intent(getBaseContext(), EditCircuitActivity.class);
            editCircuitIntent.putExtra(Constants.CIRCUIT_NAME_PARAM, circuiName);
            startActivity(editCircuitIntent);
        } catch (FileNotFoundException e) {
            Snackbar.make(view, "Error when create file", Snackbar.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
