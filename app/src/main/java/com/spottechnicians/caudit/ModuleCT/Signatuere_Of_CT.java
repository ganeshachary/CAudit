package com.spottechnicians.caudit.ModuleCT;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.spottechnicians.caudit.R;
import com.spottechnicians.caudit.models.Visit;

public class Signatuere_Of_CT extends AppCompatActivity {

    EditText etName, EtSignature;
    Button btnSignature, btnSave, btnUpload;
    ImageView ivSignature;
    Visit visit;
    Bitmap bitmap[] = new Bitmap[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signatuere__of__ct);
        ivSignature = (ImageView) findViewById(R.id.ivSignature);
        //visit=getIntent().getExtras().getParcelable("Visit2");
        //bitmap=(Bitmap[]) getIntent().getParcelableArrayExtra("Bitmap");
        //bitmap=visit.getBitmap();
        //ivSignature.setVisibility(View.VISIBLE);
        //ivSignature.setImageBitmap(bitmap[0]);

    }


    public void onSignaturePressed(View view) {
        ivSignature.setVisibility(View.GONE);
        Intent intent = new Intent(this, SignatureCanvas.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                byte[] byteArray = data.getByteArrayExtra("signature");
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                ivSignature.setVisibility(View.VISIBLE);
                ivSignature.setImageBitmap(bmp);
            }
            if (resultCode == RESULT_CANCELED) {
                //Toast.makeText(this,"result was not ok",Toast.LENGTH_SHORT).show();
            }

        }
    }

}
