package com.example.mob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mob.entity.CustomerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CHANNEL_ID = "channel";
    private static NotificationManager notificationManager;
    private static final int NOTIFY_ID = 101;
    private  DbHandler databaseHelper;
    private static TextView textView;
    private static SQLiteDatabase db;
    private static Cursor userCursor;
    Button buDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DbHandler(this);
        //databaseHelper.addCustomer(new CustomerModel("Max","24","male","job!!","idcard","phone","address"));
        Button nat = findViewById(R.id.natificate);
        Button alert = findViewById(R.id.alert);
        buDatabase = findViewById(R.id.BD);
      textView = findViewById(R.id.textView);

        buDatabase.setOnClickListener(this);

        //  BD.setOnClickListener(view -> onGetDataBtnClick());

        /* API.setOnClickListener(view->{
            TextView textView = findViewById(R.id.textView);
            textView.setText("Loading");
            new Thread(() -> {
                try{
                    String content = getContent("https://jsonplaceholder.typicode.com/posts/1");


                    textView.post(() -> textView.setText(content));
                }
                catch (IOException ex){
                    textView.post(() -> {
                        textView.setText("Ошибка: " + ex.getMessage());
                        Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                    });
                }
            }).start();


        });*/
//        nat.setOnClickListener(view -> Spend(view));
//        alert.setOnClickListener(view -> Enter(view));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }


        Context context = getApplicationContext();
        Toast longToast = Toast.makeText(context, "Вам необходимо прочитать сообщение",Toast.LENGTH_LONG);
        longToast.show();

    }

    @Override
    public void onClick(View view) {
        if(view.getId()== buDatabase.getId()){
            getAllFromDataBase();



        }
    }

    private void getAllFromDataBase() {
       List<CustomerModel> list = databaseHelper.getAllCustomers();
        for (CustomerModel c :list) {
            textView.setText(textView.getText().toString()+ "\n"+c);
        }

    }
/*
    public void onGetDataBtnClick() {
        db = databaseHelper.open();
        userCursor = db.rawQuery("select * from users" , null);
        textView = findViewById(R.id.textView);
        textView.setText("");

        if (userCursor.moveToFirst()) {
            do {
                String id = userCursor.getString(0);
                String name = userCursor.getString(1);
                textView.append("id: " + id +"\nname: " + name + "\n");
            } while (userCursor.moveToNext());
        } else {
            textView.append("No data found");
        }
    }
    public void Spend(View view) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Уведомление")
                        .setContentText("Уведомление");

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    public void Enter(View view)
    {
            AlertDialog.Builder alt_builder = new AlertDialog.Builder(this);
            alt_builder.setMessage("Подключиться?")
                    .setCancelable(false)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, Second.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alert = alt_builder.create();
            alert.setTitle("Подтверждение входа");
            alert.show();


    }

    private String getContent(String path) throws IOException {
        BufferedReader reader=null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        try {
            URL url=new URL(path);
            connection =(HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.connect();
            stream = connection.getInputStream();
            reader= new BufferedReader(new InputStreamReader(stream));
            StringBuilder buf=new StringBuilder();
            String line;
            while ((line=reader.readLine()) != null) {
                buf.append(line).append("\n");
            }
            return(buf.toString());
        }
        finally {
            if (reader != null) {
                reader.close();
            }
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

 */
}