package kobeissidev.calculategpa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Locale;
import java.util.Objects;

@SuppressWarnings("ALL")
public class CollegeGPA extends AppCompatActivity {
    private static final int[] classSpinnerIDs = {R.id.first, R.id.second, R.id.third, R.id.fourth, R.id.fifth, R.id.sixth};
    private static final int[] creditSpinnerIDs = {R.id.creditFirst, R.id.creditSecond, R.id.creditThird, R.id.creditFourth, R.id.creditFifth, R.id.creditSixth};
    private int numberOfClasses;
    private double totalGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_gp);
        this.getSupportActionBar().hide();

        numberOfClasses = getIntent().getIntExtra("NumberOfClasses", 1);
        totalGPA = 0;
        setSpinners();

        Button calcButton = (Button) findViewById(R.id.calcButton);
        calculateButton(calcButton);
    }

    private void calculateButton(Button calcButton) {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double qualityPoints = 0, totalCredits = 0;
                for (int spinnerID = 0; spinnerID < numberOfClasses; spinnerID++) {
                    Spinner mySpinner = (Spinner) findViewById(classSpinnerIDs[spinnerID]);
                    Spinner creditSpinner = (Spinner) findViewById(creditSpinnerIDs[spinnerID]);
                    if (mySpinner.getSelectedItem().toString().equals("A+")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[0]);
                    } else if (mySpinner.getSelectedItem().toString().equals("A")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[1]);
                    } else if (mySpinner.getSelectedItem().toString().equals("A-")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[2]);
                    } else if (mySpinner.getSelectedItem().toString().equals("B+")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[3]);
                    } else if (mySpinner.getSelectedItem().toString().equals("B")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[4]);
                    } else if (mySpinner.getSelectedItem().toString().equals("B-")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[5]);
                    } else if (mySpinner.getSelectedItem().toString().equals("C+")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[6]);
                    } else if (mySpinner.getSelectedItem().toString().equals("C")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[7]);
                    } else if (mySpinner.getSelectedItem().toString().equals("C-")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[8]);
                    } else if (mySpinner.getSelectedItem().toString().equals("D+")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[9]);
                    } else if (mySpinner.getSelectedItem().toString().equals("D")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[10]);
                    } else if (mySpinner.getSelectedItem().toString().equals("D-")) {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[11]);
                    } else {
                        qualityPoints += creditAdd(creditSpinner, MainActivity.scales[12]);
                    }
                }
                for (int credit = 0; credit < numberOfClasses; credit++) {
                    Spinner tempSpinner = (Spinner) findViewById(creditSpinnerIDs[credit]);
                    totalCredits += Double.parseDouble(tempSpinner.getSelectedItem().toString());
                }
                totalGPA = qualityPoints / totalCredits;
                EditText textGPA = (EditText) findViewById(R.id.GPATotal);
                textGPA.setVisibility(View.VISIBLE);
                textGPA.setText(String.format(Locale.US, "Your GPA is currently %.2f.", totalGPA));
            }
        });
    }

    private double creditAdd(Spinner creditSpinner, double creditAmountNumber) {
        double qualityPoints = 0;
        if (Objects.equals(creditSpinner.getSelectedItem().toString(), "5")) {
            qualityPoints += (5.0 * creditAmountNumber);
        } else if (Objects.equals(creditSpinner.getSelectedItem().toString(), "4")) {
            qualityPoints += (4.0 * creditAmountNumber);
        } else if (Objects.equals(creditSpinner.getSelectedItem().toString(), "3")) {
            qualityPoints += (3.0 * creditAmountNumber);
        } else if (Objects.equals(creditSpinner.getSelectedItem().toString(), "2")) {
            qualityPoints += (2.0 * creditAmountNumber);
        } else if (Objects.equals(creditSpinner.getSelectedItem().toString(), "1")) {
            qualityPoints += (creditAmountNumber);
        }
        return qualityPoints;
    }

    private void setSpinners() {
        for (int id = 0; id < numberOfClasses; id++) {
            Spinner spinner = (Spinner) findViewById(classSpinnerIDs[id]);
            spinner.setVisibility(View.VISIBLE);
            addToGradeSpinner(spinner);
        }
        for (int id = 0; id < numberOfClasses; id++) {
            Spinner spinner = (Spinner) findViewById(creditSpinnerIDs[id]);
            spinner.setVisibility(View.VISIBLE);
            addToCreditSpinner(spinner);
        }
    }

    private void addToGradeSpinner(Spinner mySpinner) {
        String[] items = new String[]{"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    private void addToCreditSpinner(Spinner mySpinner) {
        String[] items = new String[]{"5", "4", "3", "2", "1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}
