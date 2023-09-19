package com.example.bai2de1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream inputStream = getAssets().open("data_de_1_2.json");
            String data = convertStreamToString(inputStream);
            inputStream.close();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray datas = jsonObject.getJSONArray("data");
            ArrayList<Message> messages = new ArrayList<Message>();
            for (int i = 0; i < datas.length(); i++) {
                Message message = new Message();
                JSONObject object = datas.getJSONObject(i);
                message.id = object.getInt("id");
                message.message = object.getString("message");
                messages.add(message);
            }

            RecyclerView recyclerView = findViewById(R.id.rcv);
            MessageAdapter adapter = new MessageAdapter();
            adapter.data = messages;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            Button send = findViewById(R.id.btn_send);
            EditText edtMessage = findViewById(R.id.edt_message);
            send.setOnClickListener(v -> {
                if(edtMessage.getText() == null
                        || edtMessage.getText()
                        .toString().trim().length() == 0) {
                    return;
                }

                Message message = new Message();
                message.id = 0;
                message.message = edtMessage.getText().toString();
                messages.add(message);
                adapter.notifyItemChanged(messages.size() - 1);

                edtMessage.setText("");
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}