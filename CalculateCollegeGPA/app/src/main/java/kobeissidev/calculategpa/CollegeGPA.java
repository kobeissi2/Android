package kobeissidev.calculategpa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CollegeGPA extends AppCompatActivity {
    private static final int[] classSpinnerIDs = {R.id.first, R.id.second, R.id.third, R.id.fourth, R.id.fifth, R.id.sixth};
    private static final int[] creditSpinnerIDs = {R.id.creditFirst, R.id.creditSecond, R.id.creditThird, R.id.creditFourth, R.id.creditFifth, R.id.creditSixth};
    private int numberOfClasses;
    private double[] scales;
    private double totalGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_gp);
        this.getSupportActionBar().hide();

        numberOfClasses = getIntent().getIntExtra("NumberOfClasses", 1);
        scales = getIntent().getDoubleArrayExtra("Scales");
        totalGPA = 0;
        setSpinners();

        Button calcButton = (Button) findViewById(R.id.calcButton);
        calculateButton(calcButton);
    }

    protected void calculateButton(Button calcButton) {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double qualityPoints = 0, totalCredits = 1;
                for (int spinnerID = 0; spinnerID < numberOfClasses; spinnerID++) {
                    Spinner mySpinner = (Spinner) findViewById(classSpinnerIDs[spinnerID]);
                    Spinner creditSpinner = (Spinner) findViewById(creditSpinnerIDs[spinnerID]);
                    if (mySpinner.getSelectedItem().toString() == "A") {
                        qualityPoints += creditAdd(creditSpinner, scales[0]);
                    } else if (mySpinner.getSelectedItem().toString() == "A-") {
                        qualityPoints += creditAdd(creditSpinner, scales[1]);
                    } else if (mySpinner.getSelectedItem().toString() == "B+") {
                        qualityPoints += creditAdd(creditSpinner, scales[2]);
                    } else if (mySpinner.getSelectedItem().toString() == "B") {
                        qualityPoints += creditAdd(creditSpinner, scales[3]);
                    } else if (mySpinner.getSelectedItem().toString() == "B-") {
                        qualityPoints += creditAdd(creditSpinner, scales[4]);
                    } else if (mySpinner.getSelectedItem().toString() == "C+") {
                        qualityPoints += creditAdd(creditSpinner, scales[5]);
                    } else if (mySpinner.getSelectedItem().toString() == "C") {
                        qualityPoints += creditAdd(creditSpinner, scales[6]);
                    } else if (mySpinner.getSelectedItem().toString() == "C-") {
                        qualityPoints += creditAdd(creditSpinner, scales[7]);
                    } else if (mySpinner.getSelectedItem().toString() == "D+") {
                        qualityPoints += creditAdd(creditSpinner, scales[8]);
                    } else if (mySpinner.getSelectedItem().toString() == "D") {
                        qualityPoints += creditAdd(creditSpinner, scales[9]);
                    } else if (mySpinner.getSelectedItem().toString() == "D-") {
                        qualityPoints += creditAdd(creditSpinner, scales[10]);
                    }
                }
/*
                for (int credit = 0; credit < numberOfClasses; credit++) {
                    Spinner tempSpinner = (Spinner) findViewById(creditSpinnerIDs[credit]);
                    totalCredits += Double.parseDouble(tempSpinner.toString());
                }


               totalGPA = qualityPoints / totalCredits;*/
                EditText textGPA = (EditText) findViewById(R.id.GPATotal);
                textGPA.setVisibility(View.VISIBLE);
                textGPA.setText("TODO");
            }
        });
    }

    protected double creditAdd(Spinner creditSpinner, double creditAmountNumber) {
        double qualityPoints = 0;
        if (creditSpinner.getSelectedItem().toString() == "5") {
            qualityPoints += (5.0 * creditAmountNumber);
        } else if (creditSpinner.getSelectedItem().toString() == "4") {
            qualityPoints += (4.0 * creditAmountNumber);
        } else if (creditSpinner.getSelectedItem().toString() == "3") {
            qualityPoints += (3.0 * creditAmountNumber);
        } else if (creditSpinner.getSelectedItem().toString() == "2") {
            qualityPoints += (2.0 * creditAmountNumber);
        } else if (creditSpinner.getSelectedItem().toString() == "1") {
            qualityPoints += (creditAmountNumber);
        }
        return qualityPoints;
    }

    protected void setSpinners() {
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

    protected void addToGradeSpinner(Spinner mySpinner) {
        String[] items = new String[]{"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    protected void addToCreditSpinner(Spinner mySpinner) {
        String[] items = new String[]{"5", "4", "3", "2", "1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}
