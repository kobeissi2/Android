package kobeissidev.calculategpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private int numberOfClasses = 1;
    private double[] scales = {4.0,3.7,3.4,3.0,2.7,2.4,2.0,1.7,1.4,1.0,0.7};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        Button collegeGPA = (Button) findViewById(R.id.collegeButton);
        Button scaleButton = (Button) findViewById(R.id.scaleButton);
        navigateToCollege(collegeGPA);
        navigateToSettings(scaleButton);
    }

    protected void navigateToCollege(Button collegeGPA) {
        collegeGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText classesText = (EditText) findViewById(R.id.classes);
                numberOfClasses = Integer.parseInt(classesText.getText().toString());

                if (numberOfClasses > 6 || numberOfClasses < 1) {
                    numberOfClasses = 1;
                }

                Intent intent = new Intent(getBaseContext(), CollegeGPA.class);
                intent.putExtra("NumberOfClasses", numberOfClasses);
                intent.putExtra("Scales", scales);
                startActivity(intent);
            }
        });
    }

    protected void navigateToSettings(Button scaleButton) {
        scaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ChangeScale.class));
            }
        });
    }
}
