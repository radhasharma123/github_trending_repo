package com.in.githubapi.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.in.githubapi.App;
import com.in.githubapi.data.DataManager;
import com.in.githubapi.data.model.ItemModel;
import com.in.githubapi.ui.base.BaseFragment;
import com.in.githubapi.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.in.githubapi.R;

public class MainFragment extends BaseFragment<MainViewModel> {
    private View mView;
    private MainAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_box)
    TextView etSearch;

    public static List<ItemModel> itemModelList;
    List<ItemModel> selectedItemModelList=new ArrayList<>();


    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public MainViewModel getViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(DataManager.getInstance(App.getInstance()));
        return new ViewModelProvider(getActivity(), factory).get(MainViewModel.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, mView);
        initView();
        setupRecycler();

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.getRepos().observe(this, itemModels -> {
            itemModelList = itemModels;
            selectedItemModelList = itemModelList;
            mAdapter.addData(itemModels);
        });
        viewModel.getError().observe(this, isError -> {
            if (isError) {
                displaySnackbar(true, "Can't load more github repos");
            }
        });

        if (DataManager.getInstance(App.getInstance()).getDate() == null)
            DataManager.getInstance(App.getInstance()).setDate(Util.getDefaultDate());

        displaySnackbar(false, "Loading...");
        viewModel.loadRepos(DataManager.getInstance(App.getInstance()).getDate());

    }

    public static void hideKeyBoard(View rootView, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
    }

    private void initView() {
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                etSearch.clearFocus();
                hideKeyBoard(etSearch, App.getInstance());
                return true;
            }

            return false;
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    filter(editable.toString());
                } else {
                    filter(editable.toString());
                }
            }
        });

    }

    public void filter(String text) {
        if (TextUtils.isEmpty(text)) {
            selectedItemModelList = itemModelList;
            mAdapter.addData(itemModelList);
        } else {
            List<ItemModel> filteredNames = new ArrayList<>();
            if (itemModelList != null) {
                if (itemModelList.size() == 0) {
                    return;
                }
                for (ItemModel model : itemModelList) {
                    if (model.getName().toLowerCase().contains(text.toLowerCase())) {
                        filteredNames.add(model);
                    }
                }
            }
            
            selectedItemModelList = filteredNames;
            mAdapter.addData(filteredNames);
        }

    }

    private void setupRecycler() {
        mLayoutManager = new LinearLayoutManager(App.getInstance());
        mAdapter = new MainAdapter(pos -> {
            if (selectedItemModelList != null) {
                selectedItemModelList.get(pos).setClicked(true);
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        int scrollPosition = 0;
        mRecyclerView.scrollToPosition(scrollPosition);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(App.getInstance(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void displaySnackbar(boolean isError, String message) {
        Util.showSnack(mView, isError, message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onClear();
    }

}



