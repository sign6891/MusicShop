package com.example.musicshop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText nameEditText;
    private ImageView imageViewGuitar;
    private TextView textViewQuantity;
    private TextView textViewCurrency;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonAddToCart;
    private int countQuantity = 0;
    private Spinner spinner;
    private ArrayList spinnerList;
    private ArrayAdapter spinnerAdapter;
    private String goodsName;
    private HashMap guitarMap;
    private double price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.editText);
        textViewQuantity = findViewById(R.id.textViewQuantity);
        textViewCurrency = findViewById(R.id.textViewCurrency);
        imageViewGuitar = findViewById(R.id.imageViewGuitar);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonAddToCart = findViewById(R.id.buttonAddToCart);
        createSpinner();
        createMap();
    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerList = new ArrayList();
        spinnerList.add("guitar");
        spinnerList.add("drums");
        spinnerList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        guitarMap = new HashMap();
        guitarMap.put("guitar", 750.0);
        guitarMap.put("drums", 498.0);
        guitarMap.put("keyboard", 975.0);
    }



    @SuppressLint("SetTextI18n")
    public void setClickPlus(View view) {
        ++countQuantity;
        textViewQuantity.setText(Integer.toString(countQuantity));
        textViewCurrency.setText("" + price * countQuantity);
    }

    @SuppressLint("SetTextI18n")
    public void setClickMinus(View view) {
        if (countQuantity > 0) {
            --countQuantity;
            textViewQuantity.setText("" + countQuantity);
            textViewCurrency.setText("" + price * countQuantity);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) guitarMap.get(goodsName);
        textViewCurrency.setText("" + price * countQuantity);
        switch (goodsName) {
            case "guitar":
                imageViewGuitar.setImageResource(R.drawable.guitar2);
                break;
            case "drums":
                imageViewGuitar.setImageDrawable(getDrawable(R.drawable.drum));
                break;
            case "keyboard":
                imageViewGuitar.setImageDrawable(getDrawable(R.drawable.keyboard));
                break;
            default:
                imageViewGuitar.setImageResource(R.drawable.guitar2);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setClickAddToCart(View view) {
        Order order = new Order();
        order.userName = nameEditText.getText().toString();
        Log.d("printUserName", order.userName);
        order.goodsName = this.goodsName;
        order.quantity = countQuantity;
        order.price = this.price;
        order.orderPrice = countQuantity * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameIntent", order.userName);
        orderIntent.putExtra("goodsNameIntent", order.goodsName);
        orderIntent.putExtra("quantityIntent", order.quantity);
        orderIntent.putExtra("priceIntent", order.price);
        orderIntent.putExtra("orderPriceIntent", order.orderPrice);
        startActivity(orderIntent);

    }
}
