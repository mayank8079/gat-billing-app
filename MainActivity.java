package com.example.gstbilling;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText customerName, gstin, description, quantity, rate;
    TextView resultText;
    Button addButton, printButton;
    ListView listView;

    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    double totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerName = findViewById(R.id.customerName);
        gstin = findViewById(R.id.gstin);
        description = findViewById(R.id.description);
        quantity = findViewById(R.id.quantity);
        rate = findViewById(R.id.rate);
        resultText = findViewById(R.id.resultText);
        addButton = findViewById(R.id.addButton);
        printButton = findViewById(R.id.printButton);
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = description.getText().toString();
                int qty = Integer.parseInt(quantity.getText().toString());
                double rateVal = Double.parseDouble(rate.getText().toString());
                double amount = qty * rateVal;

                totalAmount += amount;
                itemList.add(desc + " - Qty: " + qty + ", Rate: ₹" + rateVal + ", Amount: ₹" + amount);
                adapter.notifyDataSetChanged();

                double cgst = totalAmount * 0.09;
                double sgst = totalAmount * 0.09;
                double grandTotal = totalAmount + cgst + sgst;

                resultText.setText("Total: ₹" + totalAmount + "\nCGST: ₹" + cgst + "\nSGST: ₹" + sgst + "\nGrand Total: ₹" + grandTotal);
            }
        });
    }
}
