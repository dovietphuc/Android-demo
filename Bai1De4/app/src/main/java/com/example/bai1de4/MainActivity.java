package com.example.bai1de4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.bai1de4.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        binding.btnOrder.setOnClickListener(v -> {
            if(validate()){
                Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });

        binding.selectOrderDate.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(this);
            dialog.show();
            dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    binding.edtOrderDate.setText(i2 + "/" + (i1 + 1) + "/" + i);
                }
            });
        });

        binding.selectShipDate.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(this);
            dialog.show();
            dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    binding.edtShipDate.setText(i2 + "/" + (i1 + 1) + "/" + i);
                }
            });
        });
    }

    public boolean validate(){
        boolean isValid = true;

        if(binding.edtName.getText() == null ||
                TextUtils.isEmpty(binding.edtName.getText().toString())){
            isValid = false;
            binding.edtName.setError("Invalid");
        }

        if(binding.edtAddress.getText() == null ||
                TextUtils.isEmpty(binding.edtAddress.getText().toString())){
            isValid = false;
            binding.edtAddress.setError("Invalid");
        }

        if(binding.edtPhone.getText() == null ||
                TextUtils.isEmpty(binding.edtPhone.getText().toString())){
            isValid = false;
            binding.edtPhone.setError("Invalid");
        }

        if(binding.edtOrderDate.getText() == null ||
                TextUtils.isEmpty(binding.edtOrderDate.getText().toString())){
            isValid = false;
            binding.edtOrderDate.setError("Invalid");
        }
        if(binding.edtShipDate.getText() == null ||
                TextUtils.isEmpty(binding.edtShipDate.getText().toString())){
            isValid = false;
            binding.edtShipDate.setError("Invalid");
        }

        Date orderDate = new Date(binding.edtOrderDate.getText().toString());
        Date shipDate = new Date(binding.edtShipDate.getText().toString());

        long delta = shipDate.getTime() - orderDate.getTime();
        long twoDays = TimeUnit.DAYS.toDays(2);
        if(delta < twoDays) {
            isValid = false;
            binding.edtShipDate.setError("Invalid");
        }

        return isValid;
    }
}