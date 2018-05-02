package fostash.motorsettings;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class NewCircuitActivity extends AppCompatActivity {

    private EditText circuitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_circuit);

        circuitName = findViewById(R.id.circuit_name);
        FloatingActionButton save = findViewById(R.id.save_circuit);
        save.setOnClickListener(view -> {
            try {
                FileOutputStream fileOutputStream = openFileOutput(circuitName.getText().toString(), MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
                List<SettingsData> settingsDataList = new ArrayList<>();
                oos.writeObject(settingsDataList);
                oos.flush();
                oos.close();
                fileOutputStream.close();
                Intent intent = new Intent(getBaseContext(), EditCircuitActivity.class);
                intent.putExtra("circuit_name", circuitName.getText().toString());
                startActivity(intent);
            } catch (FileNotFoundException e) {
                Snackbar.make(view, "Error when create file", Snackbar.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
