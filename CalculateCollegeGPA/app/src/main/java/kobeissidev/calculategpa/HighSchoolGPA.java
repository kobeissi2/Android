package kobeissidev.calculategpa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Locale;

@SuppressWarnings("ALL")
public class HighSchoolGPA extends Activity {
    private static final int[] classTypeID = {R.id.tocOne, R.id.tocTwo, R.id.tocThree, R.id.tocFour, R.id.tocFive, R.id.tocSix};
    private static final int[] hsGradeID = {R.id.gradeOne, R.id.gradeTwo, R.id.gradeThree, R.id.gradeFour, R.id.gradeFive, R.id.gradeSix};
    private int numberOfClasses;
    private double totalGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_school_gp);
        this.getActionBar().hide();

        numberOfClasses = getIntent().getIntExtra("NumberOfClasses", 1);
        totalGPA = 0;
        setSpinners();

        Button calcButton = (Button) findViewById(R.id.calcButtonHS);
        calculateButton(calcButton);
    }

    private void calculateButton(Button calcButton) {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalPoints = 0;
                for (int spinnerID = 0; spinnerID < numberOfClasses; spinnerID++) {
                    Spinner mySpinner = (Spinner) findViewById(hsGradeID[spinnerID]);
                    Spinner classTypeSpinner = (Spinner) findViewById(classTypeID[spinnerID]);
                    if (mySpinner.getSelectedItem().toString().equals("A+")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[0]);
                    } else if (mySpinner.getSelectedItem().toString().equals("A")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[1]);
                    } else if (mySpinner.getSelectedItem().toString().equals("A-")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[2]);
                    } else if (mySpinner.getSelectedItem().toString().equals("B+")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[3]);
                    } else if (mySpinner.getSelectedItem().toString().equals("B")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[4]);
                    } else if (mySpinner.getSelectedItem().toString().equals("B-")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[5]);
                    } else if (mySpinner.getSelectedItem().toString().equals("C+")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[6]);
                    } else if (mySpinner.getSelectedItem().toString().equals("C")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[7]);
                    } else if (mySpinner.getSelectedItem().toString().equals("C-")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[8]);
                    } else if (mySpinner.getSelectedItem().toString().equals("D+")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[9]);
                    } else if (mySpinner.getSelectedItem().toString().equals("D")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[10]);
                    } else if (mySpinner.getSelectedItem().toString().equals("D-")) {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[11]);
                    } else {
                        totalPoints += classTypeAdd(classTypeSpinner, MainActivity.scales[12]);
                    }
                }

                totalGPA = totalPoints / numberOfClasses;
                EditText textGPA = (EditText) findViewById(R.id.textGPAHS);
                textGPA.setVisibility(View.VISIBLE);
                textGPA.setText(String.format(Locale.US,"Your GPA is currently %.2f.", totalGPA));
            }
        });
    }

    private double classTypeAdd(Spinner classTypeSpinner, double gradeNumber) {
        double total = 0;
        if (classTypeSpinner.getSelectedItem().toString().equals("Regular")) {
            total += (gradeNumber);
        } else if (classTypeSpinner.getSelectedItem().toString().equals("AP/Honors")) {
            total += (0.5 + gradeNumber);
        } else if (classTypeSpinner.getSelectedItem().toString().equals("College Prep")) {
            total += (1 + gradeNumber);
        }
        return total;
    }

    private void setSpinners() {
        for (int id = 0; id < numberOfClasses; id++) {
            Spinner spinner = (Spinner) findViewById(hsGradeID[id]);
            spinner.setVisibility(View.VISIBLE);
            addToGradeSpinner(spinner);
        }
        for (int id = 0; id < numberOfClasses; id++) {
            Spinner spinner = (Spinner) findViewById(classTypeID[id]);
            spinner.setVisibility(View.VISIBLE);
            addToClassTypeSpinner(spinner);
        }
    }

    private void addToGradeSpinner(Spinner mySpinner) {
        String[] items = new String[]{"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    private void addToClassTypeSpinner(Spinner mySpinner) {
        String[] items = new String[]{"Regular", "AP/Honors", "College Prep"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}

