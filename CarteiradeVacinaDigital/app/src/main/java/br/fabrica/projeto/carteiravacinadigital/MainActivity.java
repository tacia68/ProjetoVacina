package br.fabrica.projeto.carteiravacinadigital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.style.ParagraphStyle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    private static final int STORAGE_CODE = 1000;
    TextView mTextEt;
    Button mSaveBtn;


    @Override
    protected void onCreate(Bundle saveIntanceState){

        super.onCreate(saveIntanceState);
        setContentView(R.layout.activity_main);

        mTextEt = findViewById(R.id.textEt);
        mSaveBtn = findViewById(R.id.saveBtn);

        mSaveBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT> Build.VERSION_CODES.M){

                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, STORAGE_CODE);
                    }
                    else {
                        savePdf();
                    }

                }
                else {
                    savePdf();
                }

            }
        });

    }

    private  void savePdf(){

        Document mDoc = new Document();

        String mFileName = new SimpleDateFormat("yyyuMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());

        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf;";

        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            mDoc.open();
            String mText = mTextEt.getText().toString();
            mDoc.add(new Paragraph(mText));
            mDoc.close();
            Toast.makeText(this, mFileName+".pdf\nis saved to\n" + mFileName, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    savePdf();
                }
            }
        }
    }
}