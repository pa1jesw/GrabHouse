package com.fcproject.grabhouce;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import static com.fcproject.grabhouce.R.id.card_view;


/**
 * A simple {@link Fragment} subclass.
 */
public class purchaseFragment extends Fragment {



    public static Fragment newInstance() {
        return new purchaseFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_purchase,container,false);
        RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecylerViewAdapter());
        return view;

    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private TextView mTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);


        }
        public RecyclerViewHolder(LayoutInflater inflater,ViewGroup container)
        {
            super(inflater.inflate(R.layout.card_view,container,false));
            mCardView=itemView.findViewById(R.id.card_view);
            mTextView=itemView.findViewById(R.id.text_holder);

        }
    }
    protected class RecylerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>
    {

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            return new RecyclerViewHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}
