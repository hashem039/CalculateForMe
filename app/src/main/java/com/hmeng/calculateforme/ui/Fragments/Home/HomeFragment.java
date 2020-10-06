package com.hmeng.calculateforme.ui.Fragments.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hmeng.calculateforme.Data.ServiceModel;
import com.hmeng.calculateforme.R;
import com.hmeng.calculateforme.ui.Activities.BalconyRadius;

import java.util.List;

public class HomeFragment extends Fragment {

    HomeFragment context;
    HomeViewModel viewModel;
    /*RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;*/
    LinearLayout mServiceBalconyRadius;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mServiceBalconyRadius = root.findViewById(R.id.ll_root_balcony_radius);
        context = this;
        mServiceBalconyRadius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toBalconyRadius = new Intent(getActivity(), BalconyRadius.class);
                startActivity(toBalconyRadius);
            }
        });
        /*recyclerView = root.findViewById(R.id.recycler_home);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.recycler_view_divider));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));*/
        viewModel.getSericeLiveData().observe(getActivity(), userListUpdateObserver);

        ///final TextView textView = root.findViewById(R.id.text_home);
        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
    Observer<List<ServiceModel>> userListUpdateObserver = new Observer<List<ServiceModel>>() {
        @Override
        public void onChanged(List<ServiceModel> userArrayList) {
            /*recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),userArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(recyclerViewAdapter);*/
        }
    };
}