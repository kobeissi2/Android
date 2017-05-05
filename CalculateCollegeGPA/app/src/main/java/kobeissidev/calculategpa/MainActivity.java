package kobeissidev.calculategpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private int numberOfClasses = 1;
    protected static double[] scales;
    protected File path, file;
    protected String[] inputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        Button collegeGPA = (Button) findViewById(R.id.collegeButton);
        Button scaleButton = (Button) findViewById(R.id.scaleButton);

        path = this.getFilesDir();
        file = new File(path, "scales.txt");

        if (file.length() == 0) {
            scales = new double[]{4.0, 4.0, 3.7, 3.4, 3.0, 2.7, 2.4, 2.0, 1.7, 1.4, 1.0, 0.7, 0.0};
            inputs = new String[13];
            saveAll();
        } else {
            inputs = Load(file);
            scales = new double[13];

            for (int index = 0; index < scales.length; index++) {
                scales[index] = Double.parseDouble(inputs[index]);
            }
        }

        navigateToCollege(collegeGPA);
        navigateToSettings(scaleButton);
    }

    protected void navigateToCollege(Button collegeGPA) {
        collegeGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText classesText = (EditText) findViewById(R.id.classes);

                if (classesText.getText().length() == 0) {
                    classesText.setText("1");
                }

                numberOfClasses = Integer.parseInt(classesText.getText().toString());

                if (numberOfClasses > 6 || numberOfClasses < 1) {
                    numberOfClasses = 1;
                }

                Intent intent = new Intent(getBaseContext(), CollegeGPA.class);
                intent.putExtra("NumberOfClasses", numberOfClasses);
                startActivity(intent);
                saveAll();
            }
        });
    }

    protected void navigateToSettings(Button scaleButton) {
        scaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ChangeScale.class);
                startActivity(intent);
                saveAll();
            }
        });
    }

    public static void Save(File file, String[] data) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            try {
                for (int i = 0; i < data.length; i++) {
                    fos.write(data[i].getBytes());
                    if (i < data.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] Load(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl = 0;
        try {
            while ((test = br.readLine()) != null) {
                anzahl++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fis.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try {
            while ((line = br.readLine()) != null) {
                array[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    protected void saveAll() {
        for (int index = 0; index < scales.length; index++) {
            inputs[index] = String.valueOf(scales[index]);
        }
        Save(file, inputs);
    }
}
