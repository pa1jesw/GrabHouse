package com.fcproject.grabhouce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class rentFragment extends Fragment {


    public rentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rent, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rent_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new rentFragment.RecylerViewAdapter());
        return view;

    }
    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private TextView mTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);


        }
        public RecyclerViewHolder(LayoutInflater inflater,ViewGroup container)
        {
            super(inflater.inflate(R.layout.card_view_rent,container,false));
            mCardView=itemView.findViewById(R.id.cv_rent);
            mTextView=itemView.findViewById(R.id.text_holder1);

        }
    }
    private class RecylerViewAdapter extends RecyclerView.Adapter<rentFragment.RecyclerViewHolder>
    {

        @Override
        public rentFragment.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            return new rentFragment.RecyclerViewHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
}
