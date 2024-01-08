package com.tekup.cabinetmedicale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);
        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContactNumber);
        edpincode = findViewById(R.id.editTextBMBPinCode);
        btnBooking = findViewById(R.id.buttonBMBBooking);
        Intent intent = getIntent();

        // Convert the extra string we received to a String array by splitting it using ":" as a delimiter
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");


        btnBooking.setOnClickListener(v -> {
            // Get the username from the shared preferences
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE );
            String username = sharedPreferences.getString("username", "").toString();

            // Add the order to the database
            Database db = new Database(getApplicationContext(), "healthcare", null, 1);
            db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString()), date.toString(),"",Float.parseFloat(price[1].toString()), "medicine");

            // Remove the order from the cart since it's confirmed
            db.removeCart(username, "medicine");
            Toast.makeText(getApplicationContext(), "Your Booking is done Successfully", Toast.LENGTH_SHORT).show();
            // Go back to the home activity
            startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
        });
    }
}