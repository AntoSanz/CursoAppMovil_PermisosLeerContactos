package com.example.permisosleercontactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtContactos = findViewById(R.id.txtregistro);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            }
        }
    }

    public void leerContactos(View view) {
        String sColumnas[] = {ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.TIMES_CONTACTED};
        Cursor cursorContactos = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, sColumnas, null, null, ContactsContract.Contacts.DISPLAY_NAME);

        StringBuffer datos = new StringBuffer();

        while (cursorContactos.moveToNext()) {
            //cursorContactos.moveToFirst();
            String nombrecontacto = cursorContactos.getString(cursorContactos.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
            int numero = cursorContactos.getInt(cursorContactos.getColumnIndexOrThrow(ContactsContract.Contacts.TIMES_CONTACTED));
            datos.append(nombrecontacto);
            datos.append(", Numero de llamadas ->");
            datos.append(numero);
            datos.append("\n");
        }


        cursorContactos.close();
        txtContactos.setText(datos);
    }
}