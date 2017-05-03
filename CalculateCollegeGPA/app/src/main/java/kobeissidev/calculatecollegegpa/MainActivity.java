package kobeissidev.calculatecollegegpa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        addToGradeSpinner((Spinner)findViewById(R.id.first));
        addToGradeSpinner((Spinner)findViewById(R.id.second));
        addToGradeSpinner((Spinner)findViewById(R.id.third));
        addToGradeSpinner((Spinner)findViewById(R.id.fourth));
        addToGradeSpinner((Spinner)findViewById(R.id.fifth));

        addToCreditSpinner((Spinner)findViewById(R.id.creditFirst));
        addToCreditSpinner((Spinner)findViewById(R.id.creditSecond));
        addToCreditSpinner((Spinner)findViewById(R.id.creditThird));
        addToCreditSpinner((Spinner)findViewById(R.id.creditFourth));
        addToCreditSpinner((Spinner)findViewById(R.id.creditFifth));
    }

    protected void addToGradeSpinner(Spinner mySpinner){
        String[] items = new String[]{"A", "A-", "B+","B", "B-", "C+","C", "C-", "D+","D", "D-", "F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    protected void addToCreditSpinner(Spinner mySpinner){
        String[] items = new String[]{"5", "4", "3","2", "1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}
