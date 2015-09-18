package com.example.gnomerock.mytemplate1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gnomerock on 9/17/15.
 */
public class SearchResult extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_result);


        //Get Scientist List from Main Activity
        Intent intent = getIntent();
        ArrayList<Scientist> scientists = intent.getParcelableArrayListExtra("scientists");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new myAdapter(scientists);
        mRecyclerView.setAdapter(mAdapter);


    }


    public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

        private ArrayList<Scientist> mScientists;

        public myAdapter(ArrayList<Scientist> scientists) {

            mScientists = scientists;
        }

        public  class ViewHolder extends RecyclerView.ViewHolder{
            public TextView sci_name;
            public TextView sci_email;


            public ViewHolder(View itemView) {
                super(itemView);
                sci_name = (TextView) itemView.findViewById(R.id.scientist_name);
                sci_email = (TextView) itemView.findViewById(R.id.scientist_email);
            }
        }



        @Override
        public myAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view  = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.result_item,parent,false);

            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Scientist scientist = mScientists.get(position);
            holder.sci_name.setText(scientist.name);
            holder.sci_email.setText(scientist.email);
        }

        @Override
        public int getItemCount() {
            return mScientists.size();
        }
    }


}
