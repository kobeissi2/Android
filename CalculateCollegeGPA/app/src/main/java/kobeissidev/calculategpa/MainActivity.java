package kobeissidev.calculategpa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import kobeissidev.calculatecollegegpa.R;


public class MainActivity extends AppCompatActivity {
    private int numberOfClasses=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        Button collegeGPA = (Button)findViewById(R.id.collegeButton);
        navigateToCollege(collegeGPA);
    }

    protected void navigateToCollege(Button collegeGPA){
        collegeGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText classesText = (EditText) findViewById(R.id.classes) ;
                numberOfClasses=Integer.parseInt(classesText.getText().toString());

                if(numberOfClasses>6 || numberOfClasses<1)
                {
                    numberOfClasses=1;
                }

                Intent intent= new Intent(getBaseContext(),CollegeGPA.class);
                intent.putExtra("NumberOfClasses",numberOfClasses);
                startActivity(intent);
            }
        });
    }
}
