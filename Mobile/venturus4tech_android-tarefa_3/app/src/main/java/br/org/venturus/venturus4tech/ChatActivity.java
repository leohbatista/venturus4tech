package br.org.venturus.venturus4tech;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.socket.emitter.Emitter;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button mSendButton;
    private EditText mMessage;

    private String mNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mNickname = this.getIntent().getStringExtra("nickname");

        mRecyclerView = (RecyclerView) findViewById(R.id.chat_recycler_view);
        mSendButton = (Button) findViewById(R.id.send_button);
        mMessage = (EditText) findViewById(R.id.msg_input);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ChatAdapter();
        mRecyclerView.setAdapter(mAdapter);

        GetHistoryTask task = new GetHistoryTask(mAdapter);
        task.execute();

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgtxt = mMessage.getText().toString().trim();

                if(!msgtxt.isEmpty()) {
                    JSONObject message = new JSONObject();
                    try {
                        message.put("author", mNickname);
                        message.put("message", msgtxt);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    SocketManager.getInstance().getSocket().emit("messages",message);
                    mMessage.setText("");
                }
            }
        });

        SocketManager.getInstance().getSocket().on("messages", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                final JSONObject message = (JSONObject) args[0];
                ChatActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addMsg(message);
                        playSound();
                        mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount()-1);
                    }
                });
            }
        });
    }

    private void playSound() {
        MediaPlayer mp = MediaPlayer.create(this,R.raw.et_voila);
    }
}
