package teddy.wagproject.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import teddy.wagproject.R;
import teddy.wagproject.adapter.WagRecyclerViewAdapter;
import teddy.wagproject.model.StackExchangeItem;
import teddy.wagproject.utils.Utils;

/**
 * Created by teddy on 4/8/18.
 * Fragment for the main list
 */

public class WagListFragment extends Fragment {

    private static final String TAG = "WagListFragment";
    private static final String STACK_ENDPOINT = "https://api.stackexchange" +
            ".com/2.2/users?site=stackoverflow";

    private FrameLayout mLayout;
    private WagRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<StackExchangeItem> mItemsList;
    private ImageView mRefreshButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mLayout = (FrameLayout) inflater.inflate(R.layout
                .fragment_waglist, container, false);

        mRecyclerView = mLayout.findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRefreshButton = mLayout.findViewById(R.id.refresh);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemsList.clear();
                fetchForList();
            }
        });

        return mLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchForList();
    }

    private void fetchForList() {
        RequestQueue queue = Volley.newRequestQueue(this.getActivity());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, STACK_ENDPOINT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mItemsList = Utils.parseResponseIntoItems(response);
                        if (mItemsList.size() != 0) {
                            loadListIntoView();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "volley error : " + error.getLocalizedMessage());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void loadListIntoView() {
        mAdapter = new WagRecyclerViewAdapter(this.getActivity(), mItemsList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
