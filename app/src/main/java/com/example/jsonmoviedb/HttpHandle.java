package com.example.jsonmoviedb;

import static androidx.fragment.app.FragmentManager.TAG;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandle {
    private static final String TAF = HttpHandle.class.getSimpleName(); // Gunakan TAF sebagai TAG log

    public HttpHandle() {
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAF, "MalformedURLException: " + e.getMessage()); // Ganti TAG dengan TAF
        } catch (ProtocolException e) {
            Log.e(TAF, "ProtocolException: " + e.getMessage()); // Ganti TAG dengan TAF
        } catch (IOException e) {
            Log.e(TAF, "IOException: " + e.getMessage()); // Ganti TAG dengan TAF
        } catch (Exception e) {
            Log.e(TAF, "Exception: " + e.getMessage()); // Ganti TAG dengan TAF
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
