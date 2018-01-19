package com.thibault.dufour.channelmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener{
    private TextView txt_id,txt_mdp;
    private EditText etxt_id, etxt_mdp;
    private Button btn_valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        txt_id = (TextView) findViewById(R.id.textView_identifiant);
        txt_mdp = (TextView) findViewById(R.id.textView_mdp);
        etxt_id = (EditText) findViewById(R.id.editText_identifiant);
        etxt_mdp = (EditText) findViewById(R.id.editText_mdp);
        btn_valider = (Button) findViewById(R.id.button_valider);
        btn_valider.setOnClickListener(this);
        String result;


    }

    @Override
    public void onClick(View v) {
        HttpPostHandler connection = (HttpPostHandler) new HttpPostHandler().execute(etxt_id.getText().toString(),etxt_mdp.getText().toString());

        connection.addOnDownloadListener(new OnDownloadListener() {
            @Override
            public void onDownloadComplete(String downloadedContent) {
                Toast.makeText(getApplicationContext(),downloadedContent,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadError(String error) {

            }
        });
    }


}
