package com.nicolasfanin.IUASampleApp.activities;

import android.content.Context;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nicolasfanin.IUASampleApp.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFileActivity extends AppCompatActivity {

    private Button saveFileButton;
    private Button readFileButton;
    private EditText fileEditText;

    private static final String IUA_APP_FILE_NAME = "iua_app_file";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_file);

        saveFileButton = findViewById(R.id.save_file_button);
        readFileButton = findViewById(R.id.read_file_button);
        fileEditText = findViewById(R.id.file_edit_text);

        saveFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataInFile();
            }
        });

        readFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileData();
            }
        });
    }

    public void saveDataInFile() {
        File myFile = new File(getBaseContext().getFilesDir(), IUA_APP_FILE_NAME);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));
            writer.write(fileEditText.getText().toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFileData() {
        File myFile = new File(getBaseContext().getFilesDir(), IUA_APP_FILE_NAME);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));

            String line = reader.readLine();
            while (line != null) {
                stringBuffer.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileEditText.setText(stringBuffer.toString());
    }
}
