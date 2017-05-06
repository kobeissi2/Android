package kobeissidev.calculategpa;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

@SuppressWarnings("ALL")
public class ChangeScale extends Activity {
    private EditText[] texts;
    private Button saveButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_scale);
        //this.getActionBar().hide();

        saveButton = (Button) findViewById(R.id.saveButton);
        resetButton = (Button) findViewById(R.id.defaultButton);
        texts = new EditText[]{(EditText) findViewById(R.id.aPlus), (EditText) findViewById(R.id.a), (EditText) findViewById(R.id.aMinus),
                (EditText) findViewById(R.id.bPlus), (EditText) findViewById(R.id.b), (EditText) findViewById(R.id.bMinus),
                (EditText) findViewById(R.id.cPlus), (EditText) findViewById(R.id.c), (EditText) findViewById(R.id.cMinus),
                (EditText) findViewById(R.id.dPlus), (EditText) findViewById(R.id.d), (EditText) findViewById(R.id.dMinus), (EditText) findViewById(R.id.F)};

        fillScaleBoxes();
        changeScales();
        resetToDefault();
    }

    private void fillScaleBoxes() {
        for (int index = 0; index < MainActivity.scales.length; index++) {
            texts[index].setText(Double.toString(MainActivity.scales[index]));
        }
    }

    private void changeScales() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int index = 0; index < MainActivity.scales.length; index++) {
                    MainActivity.scales[index] = Double.parseDouble(texts[index].getText().toString());
                }
                finish();
            }
        });
    }

    private void resetToDefault(){
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.scales = new double[]{4.0, 4.0, 3.7, 3.4, 3.0, 2.7, 2.4, 2.0, 1.7, 1.4, 1.0, 0.7, 0.0};
                fillScaleBoxes();
            }
        });
    }
}