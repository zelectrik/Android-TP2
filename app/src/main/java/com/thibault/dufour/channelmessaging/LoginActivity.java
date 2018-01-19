package com.thibault.dufour.channelmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
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
    }
}
