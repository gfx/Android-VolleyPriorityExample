package com.github.gfx.volleypriorityexample.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends Activity {

    String url = "http://headers.jsontest.com/";

    RequestQueue q = null;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        q = Volley.newRequestQueue(this);

        // NOTE: RequestQueue has 4 threads by default

        lowRequest();
        immediateRequest();
        lowRequest();
        highRequest();

        lowRequest();
        normalRequest();
        lowRequest();
        immediateRequest();

        lowRequest();
        highRequest();
        lowRequest();
        normalRequest();
    }

    private void immediateRequest() {
        final int id = ++i;
        q.add(new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("XXX", "IMMEDIATE " + id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("XXX", error);
            }
        }) {
            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        });
    }

    private void highRequest() {
        final int id = ++i;
        q.add(new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("XXX", "HIGH " + id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("XXX", error);
            }
        }) {
            @Override
            public Priority getPriority() {
                return Priority.HIGH;
            }
        });

    }

    private void normalRequest() {
        final int id = ++i;
        q.add(new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("XXX", "NORMAL " + id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("XXX", error);
            }
        }) {
            @Override
            public Priority getPriority() {
                return Priority.NORMAL;
            }
        });
    }

    private void lowRequest() {
        final int id = ++i;
        q.add(new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("XXX", "LOW " + id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("XXX", error);
            }
        }) {
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }
        });
    }
}
