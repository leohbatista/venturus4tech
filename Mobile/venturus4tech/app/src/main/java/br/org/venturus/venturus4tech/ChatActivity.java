package br.org.venturus.venturus4tech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    private TextView mNickname;
    private Button mVoltar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mNickname = (TextView) findViewById(R.id.textview_nickname);
        mNickname.setText(this.getIntent().getStringExtra("nickname"));
        mRecyclerView = (RecyclerView) findViewById(R.id.chat_list);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ChatAdapter();
        mRecyclerView.setAdapter(mAdapter);


    }
}
