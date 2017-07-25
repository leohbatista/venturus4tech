package br.org.venturus.venturus4tech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    private TextView tvNickname;
    private Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tvNickname = (TextView) findViewById(R.id.textview_nickname);
        tvNickname.setText(this.getIntent().getStringExtra("nickname"));
        btVoltar = (Button) findViewById(R.id.button_voltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
