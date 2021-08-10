package sg.edu.rp.c346.id19013886.l12_oursingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etName, etDescription, etSquarekm;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar rbStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etSquarekm = (EditText) findViewById(R.id.etSquarekm);
        rbStars = findViewById(R.id.rbStars);

        Intent i = getIntent();
        final Island currentIsland = (Island) i.getSerializableExtra("island");

        etID.setText(currentIsland.getId()+"");
        etName.setText(currentIsland.getName());
        etDescription.setText(currentIsland.getDescription());
        etSquarekm.setText(currentIsland.getSquarekm()+"");
        rbStars.setRating(currentIsland.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentIsland.setName(etName.getText().toString().trim());
                currentIsland.setDescription(etDescription.getText().toString().trim());
                int squarekm = 0;
                try {
                    squarekm = Integer.valueOf(etSquarekm.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid square km", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentIsland.setSquarekm(squarekm);

                /*int selectedRB = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedRB);
                currentSong.setStars(Integer.parseInt(rb.getText().toString()));
                int result = dbh.updateSong(currentSong);*/

                currentIsland.setStars((int) rbStars.getRating());
                int result = dbh.updateIsland(currentIsland);

                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Island updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the island\n" + currentIsland.getName());
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        int result = dbh.deleteIsland(currentIsland.getId());
                        if (result>0){
                            Toast.makeText(ThirdActivity.this, "Island deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myBuilder.setNegativeButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();;

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                myBuilder.setNegativeButton("Do Not Discard", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

    }
}