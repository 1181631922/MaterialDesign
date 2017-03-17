package com.fanyafeng.materialdesign.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.andview.refreshview.XRefreshView;
import com.fanyafeng.materialdesign.Constant.MaterialDesignConstant;
import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.adapter.RVAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link TabLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabLayoutFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "flag";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    private RecyclerView.LayoutManager layoutManager;

    private RVAdapter rvAdapter;
    private List<String> stringList;

//    private XRefreshView xrvTabViewRefresh;
    private RecyclerView rvTabView;


    public TabLayoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabLayoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabLayoutFragment newInstance(String param1, String param2) {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TabLayout", mParam1);
        initView();
        initData();
    }

    private void initView() {
//        xrvTabViewRefresh = (XRefreshView) view.findViewById(R.id.xrvTabViewRefresh);
//        xrvTabViewRefresh.setPullRefreshEnable(false);
//        xrvTabViewRefresh.setPullLoadEnable(false);
//        xrvTabViewRefresh.setMoveForHorizontal(true);
        rvTabView = (RecyclerView) view.findViewById(R.id.rvTabView);
        rvTabView.setHasFixedSize(true);
        switch (mParam1) {
            case "0":
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                break;
            case "1":
                layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                break;
            case "2":
                layoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
                break;
            case "3":
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                break;
            case "4":
                layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                break;
        }
        rvTabView.setLayoutManager(layoutManager);
        stringList = new ArrayList<>();
        stringList = Arrays.asList(MaterialDesignConstant.imageList);
        rvAdapter = new RVAdapter(getActivity(), stringList);
        rvTabView.setAdapter(rvAdapter);
    }

    private void initData() {

    }

}
