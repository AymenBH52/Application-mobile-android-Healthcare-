package com.tekup.cabinetmedicale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {
    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        edname = findViewById(R.id.editTextLTBFullName);
        edaddress = findViewById(R.id.editTextLTBAddress);
        edcontact = findViewById(R.id.editTextLTBContactNumber);
        edpincode = findViewById(R.id.editTextLTBPinCode);
        btnBooking = findViewById(R.id.buttonLTBBooking);

        // We get the data(price,date and time) from the previous activity and store it in corresponding variables
        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        // When click on Booking button save the data in the database and go to HomeActivity
        btnBooking.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE );
            String username = sharedPreferences.getString("username", "").toString();
            Database db = new Database(getApplicationContext(), "healthcare", null, 1);
            // We add the data in the database and remove it from the cart
            db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString()), date.toString(),time.toString(),Float.parseFloat(price[1].toString()), "lab");
            db.removeCart(username, "lab");
            Toast.makeText(getApplicationContext(), "Your Booking is done Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
        });
    }
}

