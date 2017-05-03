package kobeissidev.calculategpa;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import kobeissidev.calculatecollegegpa.R;

public class CollegeGPA extends AppCompatActivity {
    private static final int[] classSpinnerIDs = {R.id.first, R.id.second, R.id.third, R.id.fourth, R.id.fifth, R.id.sixth};
    private static final int[] creditSpinnerIDs = {R.id.creditFirst, R.id.creditSecond, R.id.creditThird, R.id.creditFourth, R.id.creditFifth, R.id.creditSixth};
    private int numberOfClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_gp);
        this.getSupportActionBar().hide();

        numberOfClasses = getIntent().getIntExtra("NumberOfClasses", 1);
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
