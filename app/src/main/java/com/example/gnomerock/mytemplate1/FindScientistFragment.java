package com.example.gnomerock.mytemplate1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by gnomerock on 9/17/15.
 */
public class FindScientistFragment extends Fragment {

    public static FindScientistFragment newInstance() {
        FindScientistFragment fragment = new FindScientistFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_scientist, container, false);
        initializeDropList(view);


        Button clearButton = (Button) view.findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButton();
            }
        });

        Button findButton = (Button) view.findViewById(R.id.find_button);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findButton();
            }
        });


        return view;
    }


    public void initializeDropList(View view) {

        Spinner spinner1 = (Spinner) view.findViewById(R.id.organizations_name);
        Spinner spinner2 = (Spinner) view.findViewById(R.id.main_group);
        Spinner spinner3 = (Spinner) view.findViewById(R.id.sub_group);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.organizations, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.main_groups, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.sub_groups, android.R.layout.simple_spinner_item);


        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
    }


    public void clearButton() {
        View v = getView();
        TextView scientistName = (TextView) v.findViewById(R.id.scientist_name);
        TextView tag = (TextView) v.findViewById(R.id.tag);
        TextView keyword = (TextView) v.findViewById(R.id.keyword);

        scientistName.setText("");
        tag.setText("");
        keyword.setText("");

        Spinner spinner1 = (Spinner) v.findViewById(R.id.organizations_name);
        Spinner spinner2 = (Spinner) v.findViewById(R.id.main_group);
        Spinner spinner3 = (Spinner) v.findViewById(R.id.sub_group);

        spinner1.setSelection(0);
        spinner2.setSelection(0);
        spinner3.setSelection(0);


        Toast.makeText(getContext(), "CLEAR SUCCESS", Toast.LENGTH_SHORT).show();


    }

    public void findButton() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.i("REQUEST",request.httpUrl().toString());
                return chain.proceed(request);
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://6n0.me:5000")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        searchAPI search = retrofit.create(searchAPI.class);


        EditText name_form = (EditText) getView().findViewById(R.id.scientist_name);
        String name = name_form.getText().toString();
        Spinner org_form = (Spinner) getView().findViewById(R.id.organizations_name);
        String org = org_form.getSelectedItem().toString();


        Call<ArrayList<Scientist>> scientists = search.searchScientist(name,org);

        scientists.enqueue(new Callback<ArrayList<Scientist>>() {
            @Override
            public void onResponse(Response<ArrayList<Scientist>> response) {

                ArrayList<Scientist> scientistList = response.body();
                Intent intent = new Intent(getContext(),SearchResult.class);
                intent = intent.putParcelableArrayListExtra("scientists",scientistList);
                getActivity().startActivity(intent);


                Toast.makeText(getContext(), "FOUND: "+response.body().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.println(Log.ERROR, "GGWP", t.toString());
                Toast.makeText(getContext(), "FAILED...", Toast.LENGTH_SHORT).show();
            }
        });


    };


}
