package com.example.mdlugosch.barcodeminiprojekt;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    // Definieren der Objektvariablen die später mit den UI-Elementen in Beziehung gesetzt werden
    private TextView barcodeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Objektvariable auf UI-Element Referenzieren lassen
        barcodeTextView = findViewById(R.id.barcodetextview);

        // Erlaubnis vom Benutzer zur nutzung der Kamera einhiolen.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
    }

    public void ScanButton(View view) {
        // Scan Objekt erstellen das auf die Scanbibliothek zugreift.
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Auslesen des Ereignisses
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        // Liefert das Ereigniss Daten?
        if(intentResult != null) {
            // Hat der ausgelesene Code Inhalt?
            if(intentResult.getContents() == null) {
                barcodeTextView.setText("Bitte drücken Sie den Scan-Button und halten Sie die Kamera über den QR-Code");
            } else {
                barcodeTextView.setText(intentResult.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
