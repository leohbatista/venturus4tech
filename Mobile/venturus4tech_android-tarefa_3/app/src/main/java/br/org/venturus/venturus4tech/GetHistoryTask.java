package br.org.venturus.venturus4tech;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by vntlab on 7/26/17.
 */

public class GetHistoryTask extends AsyncTask<Void,Void,JSONArray> {

    private ChatAdapter mAdapter;

    public GetHistoryTask(ChatAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    protected JSONArray doInBackground(Void... params) {
        JSONArray msgArray = null;

        try {
            URL url = new URL("http://172.20.6.37:3000");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            Scanner s = new Scanner(in).useDelimiter("\\A");

            String resultStting  = s.hasNext() ? s.next() : "";
            msgArray = new JSONArray(resultStting);

            urlConnection.disconnect();
        } catch (Exception e) {
            msgArray = new JSONArray();
        }

        return msgArray;
    }

    @Override
    protected void onPostExecute(JSONArray messages) {
        super.onPostExecute(messages);

        for (int i = 0; i < messages.length(); i++) {
            try {
                JSONObject message = messages.getJSONObject(i);
                mAdapter.addMsg(message);
            } catch (JSONException e) {

            }
        }
    }
}
