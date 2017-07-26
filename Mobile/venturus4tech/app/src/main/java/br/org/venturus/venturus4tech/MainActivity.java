package br.org.venturus.venturus4tech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mEntrar;
    private EditText mNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNickname = (EditText) findViewById(R.id.edit_nickname);
        mEntrar = (Button) findViewById(R.id.button_enter);
        mEntrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.e("VNT","Clicou em Entrar");
                String nickname = mNickname.getText().toString();
                if(!nickname.trim().isEmpty()) {
                    openChatActivity(nickname);
                } else {
                    Toast.makeText(getApplicationContext(),"Digite seu nickname",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openChatActivity(String nickname) {
        Intent i = new Intent(this, ChatActivity.class);
        i.putExtra("nickname",nickname);
        startActivity(i);
    }
}