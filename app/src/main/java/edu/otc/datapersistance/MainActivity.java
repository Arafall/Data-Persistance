package edu.otc.datapersistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.btn_file);
        button.setOnClickListener(view -> {
            try {
                writeToInternalFile("todofile");
                textView.setText(readFromInternalFile("todofile"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void writeToInternalFile(String path) throws FileNotFoundException {
        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
        FileOutputStream outputStream = openFileOutput(path, Context.MODE_PRIVATE);
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("Study for Algebra exam");
        writer.println("Wash the car");
        writer.println("Volunteer at the hospital");
        writer.close();
    }

    private String readFromInternalFile(String path) throws FileNotFoundException {
        FileInputStream inputStream = openFileInput(path);
        StringBuilder strBuilder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ( (line = reader.readLine()) != null) {
                strBuilder.append(line).append(lineSeparator);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return strBuilder.toString();
    }
}