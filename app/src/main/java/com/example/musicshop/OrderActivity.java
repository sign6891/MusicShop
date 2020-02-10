package com.example.musicshop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"sign6891@gmail.com"};
    String subject = "Order from Music Shop";
    String emailText;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Смена надписи заголовка в активити
        setTitle(R.string.app_name2);
        intentActivity();

    }

    public void intentActivity() {
        Intent orderIntentOrderActivity = getIntent();
        String userName = orderIntentOrderActivity.getStringExtra("userNameIntent");
        String goodsName = orderIntentOrderActivity.getStringExtra("goodsNameIntent");
        int quantity = orderIntentOrderActivity.getIntExtra("quantityIntent", 0);
        double orderPrice = orderIntentOrderActivity.getDoubleExtra("orderPriceIntent", 0);
        double price = orderIntentOrderActivity.getDoubleExtra("priceIntent", 0);
        TextView tvUserName = findViewById(R.id.order_user_name);
        TextView tvGoodsName = findViewById(R.id.goods_name);
        TextView tvQuantity = findViewById(R.id.quantity);
        TextView tvPrice = findViewById(R.id.price);
        TextView tvOrderPrice = findViewById(R.id.order_price);

        tvUserName.setText(userName);
        tvGoodsName.setText(goodsName);
        tvQuantity.setText("" + quantity);
        tvPrice.setText("" + price);
        tvOrderPrice.setText("" + orderPrice);
        emailText = "Customer name: " + userName + "\n" + "Goods name: " + goodsName + "\n"
                + "Quantity: " + quantity + "\n" + "Price: " + price + "\n" + "Order Price: " + orderPrice;
    }

    public void submitOrder(View view) {
        //Отправка конкретно через email
        /*Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/

        //Отправка через любое приложение
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, emailText);
        sendIntent.setType("text/plain");

        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }

    }
}
