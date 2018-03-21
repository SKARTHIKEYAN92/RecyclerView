package com.gsk.www.recyclerview1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   // private static final String URL_DATA ="https://api.stackexchange.com/docs/users";
    private static final String URL_DATA ="http://citibikenyc.com/stations/json";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager (new LinearLayoutManager (this));

        listItems = new ArrayList <> ();

        loadRecyclerViewData();
        }
        private void loadRecyclerViewData()
        {
            final ProgressDialog pd = new ProgressDialog (this);
            pd.setMessage("Loading Data....");
            pd.show ();

            StringRequest sr = new StringRequest (Request.Method.GET, URL_DATA, new Response.Listener <String> () {
                @Override
                public void onResponse(String s) {

                    try {
                        JSONObject jsonObject =new JSONObject (s);
                        JSONArray array = jsonObject.getJSONArray ("stationBeanList");

                        for(int i=0;i<array.length ();i++)
                        {
                            JSONObject o = array.getJSONObject (i);
                            ListItem item = new ListItem (
                                   // o.getString ("display_name"),
                                    //o.getString ("reputation"),

                                    //o.getString ("profile_image"),
                                        o.getString ("city"),
                                        o.getString ("postalCode"),
                                        o.getString ("location")

                            );

                            listItems.add(item);
                        }

                        adapter=new MyAdapter (listItems,getApplicationContext () );
                        recyclerView.setAdapter (adapter);


                    } catch (JSONException e) {
                        e.printStackTrace ();
                    }


                }
            },
                    new Response.ErrorListener () {
                @Override
                public void onErrorResponse(VolleyError error) {

                    //pd.dismiss ();
                    //ProgressDialog p=new ProgressDialog ( pd);
                    //p.dismiss();
                   // Toast.makeText (getApplicationContext ()Toast.LENGTH_LONG).show ();

                }
            });

            RequestQueue rq = Volley.newRequestQueue (this);
            rq.add (sr);
        }

    }



