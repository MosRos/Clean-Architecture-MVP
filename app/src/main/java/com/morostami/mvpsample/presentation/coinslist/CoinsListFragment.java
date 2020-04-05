package com.morostami.mvpsample.presentation.coinslist;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.morostami.mvpsample.R;
import com.morostami.mvpsample.databinding.FragmentCoinsListBinding;
import com.morostami.mvpsample.domain.Coin;
import com.morostami.mvpsample.presentation.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CoinsListFragment extends Fragment implements CoinsListContract.View {
    final String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private FragmentCoinsListBinding fragmentCoinsListBinding;

    @Inject
    public CoinsListPresenter presenter;

    private RecyclerView coinsRecycler;
    private CoinsAdapter coinsAdapter;
    private ArrayList<Coin> coinArrayList = new ArrayList();

    public CoinsListFragment() {
        //empty constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity) getActivity()).coinsComponent.injectCoinsListFragment(this);
        presenter.attacheView(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragmentCoinsListBinding = FragmentCoinsListBinding.inflate(inflater, container , false);
        View root = fragmentCoinsListBinding.getRoot();
        mContext = (getContext() != null) ? getContext() : root.getContext();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        regesterWidgets();
        fragmentCoinsListBinding.progressBar.setVisibility(View.VISIBLE);
        presenter.loadCoinsList();
    }

    @Override
    public void onStart() {
        super.onStart();

    }
    private void regesterWidgets() {
        int noOfColumns = getResources().getInteger(R.integer.coins_grid_columns);
        GridLayoutManager glManager = new GridLayoutManager(mContext, noOfColumns, RecyclerView.VERTICAL, false);
        coinsRecycler = fragmentCoinsListBinding.coinsRecycler;
        coinsRecycler.setLayoutManager(glManager);

        coinsAdapter = new CoinsAdapter();
        coinsRecycler.setAdapter(coinsAdapter);
    }

    private void notifyCoinsAdapter(List<Coin> coins) {
        coinsAdapter.addCoins(coins);
        coinsAdapter.notifyDataSetChanged();
    }


    @Override
    public void displayCoinsList(List<Coin> coins) {
        if (coins != null) {
            notifyCoinsAdapter(coins);
            new Handler().postDelayed(() ->{
                fragmentCoinsListBinding.progressBar.setVisibility(View.GONE);
            }, 3000);
        }
    }

    @Override
    public void showError(String errorMessage) {

    }
}
