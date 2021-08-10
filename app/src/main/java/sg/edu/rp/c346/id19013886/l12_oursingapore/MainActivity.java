package sg.edu.rp.c346.id19013886.l12_oursingapore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etDescription, etSquarekm;
    Button btnInsert, btnShowList;
    RatingBar rbStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_main));

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etSquarekm = findViewById(R.id.etSquarekm);
        btnInsert = findViewById(R.id.btnInsertIsland);
        btnShowList = findViewById(R.id.btnShowList);
        rbStars = findViewById(R.id.rbStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (name.length() == 0 || description.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String squarekm_str = etSquarekm.getText().toString().trim();
                int squarekm = Integer.valueOf(squarekm_str);
                int stars = (int) rbStars.getRating();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long result = dbh.insertIsland(name, description, squarekm, stars);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Island inserted", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etDescription.setText("");
                    etSquarekm.setText("");
                    rbStars.setRating(0);
                } else {
                    Toast.makeText(MainActivity.this, "Insert failed", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }

    /*private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radio1:
                stars = 1;
                break;
            case R.id.radio2:
                stars = 2;
                break;
            case R.id.radio3:
                stars = 3;
                break;
            case R.id.radio4:
                stars = 4;
                break;
            case R.id.radio5:
                stars = 5;
                break;
        }
        return stars;
    }*/
}