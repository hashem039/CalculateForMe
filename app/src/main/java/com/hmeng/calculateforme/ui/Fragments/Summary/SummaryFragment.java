package com.hmeng.calculateforme.ui.Fragments.Summary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hmeng.calculateforme.Adapters.RecyclerViewAdapter;
import com.hmeng.calculateforme.Data.BalconyRadiusOperationTask;
import com.hmeng.calculateforme.R;
import com.hmeng.calculateforme.ui.Fragments.Home.HomeFragment;

import java.util.List;

public class SummaryFragment extends Fragment {

    private SummaryViewModel summaryViewModel;
    private HomeFragment context;
    private RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    private TextView mTvEmptyRV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        summaryViewModel =
                ViewModelProviders.of(this).get(SummaryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_summary, container, false);
        recyclerView = root.findViewById(R.id.recycler_home);
        mTvEmptyRV = root.findViewById(R.id.empty_view);
        summaryViewModel.init();
        //summaryViewModel.mContext = getActivity();
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.recycler_view_divider));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        summaryViewModel.getPrefBalconyRadiusTasksLiveData().observe(getActivity(), userListUpdateObserver);

        return root;
    }
    void checkEmpty() {
        mTvEmptyRV.setVisibility(recyclerViewAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
    Observer<List<BalconyRadiusOperationTask>> userListUpdateObserver = new Observer<List<BalconyRadiusOperationTask>>() {
        @Override
        public void onChanged(List<BalconyRadiusOperationTask> userArrayList) {
            recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),userArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            checkEmpty();
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    };
}