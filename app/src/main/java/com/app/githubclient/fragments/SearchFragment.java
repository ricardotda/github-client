package com.app.githubclient.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.githubclient.R;
import com.app.githubclient.adapters.RepoAdapter;
import com.app.githubclient.adapters.UserAdapter;
import com.app.githubclient.repositories.Repositories;
import com.app.githubclient.repositories.types.RepoRepository;
import com.app.githubclient.repositories.types.UserRepository;
import com.app.githubclient.values.Repo;
import com.app.githubclient.values.User;

import java.util.List;

public class SearchFragment extends Fragment implements UserRepository.OnUsersListReceivedListener, RepoRepository.OnReposListReceivedListener {

    public static int SEARCH_USER = 1;
    public static int SEARCH_REPO = 2;

    private static String SEARCH_TYPE = "search_type";

    private OnListFragmentInteractionListener listener;
    private TextView searchInfo;
    private ProgressBar loading;
    private RecyclerView recyclerView;
    private RelativeLayout mainContainer;
    private RelativeLayout emptyView;
    private int searchType;

    /**
     * Mandatory empty constructor
     */
    public SearchFragment() {
    }

    public static SearchFragment newInstance(int searchType) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(SEARCH_TYPE, searchType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null && getArguments().containsKey(SEARCH_TYPE)) {
            searchType = getArguments().getInt(SEARCH_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchInfo = (TextView) view.findViewById(R.id.search_info);
        loading = (ProgressBar) view.findViewById(R.id.loading);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        mainContainer = (RelativeLayout) view.findViewById(R.id.main_container);
        emptyView = (RelativeLayout) view.findViewById(R.id.empty_view);

        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (searchType == SEARCH_USER) {
            searchInfo.setText(String.format(
                    getActivity().getString(R.string.search_info),
                    getActivity().getString(R.string.user)));
        } else if (searchType == SEARCH_REPO) {
            searchInfo.setText(String.format(
                    getActivity().getString(R.string.search_info),
                    getActivity().getString(R.string.repository)));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                loading.setVisibility(View.VISIBLE);
                handleSearchType(query);
                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
    }

    private void handleSearchType(String query) {
        if (searchType == SEARCH_USER) {
            Repositories.repository().forUser().searchUsers(query, this);
        } else {
            Repositories.repository().forRepo().searchRepos(query, this);
        }
    }

    @Override
    public void onUserReceived(List<User> gitHubUsers) {
        mainContainer.setVisibility(View.VISIBLE);
        hideLoadingAndInfo();
        recyclerView.setAdapter(new UserAdapter(getActivity(), gitHubUsers, listener));
    }

    @Override
    public void onUsersNotFound() {
        hideLoadingAndInfo();
        showEmptyView();
    }

    @Override
    public void onReposReceived(List<Repo> repos) {
        mainContainer.setVisibility(View.VISIBLE);
        hideLoadingAndInfo();
        recyclerView.setAdapter(new RepoAdapter(repos, listener));
    }

    @Override
    public void onReposNotFound() {
        hideLoadingAndInfo();
        showEmptyView();
    }

    @Override
    public void onRequestFail() {
        loading.setVisibility(View.INVISIBLE);
        Toast.makeText(getActivity(), R.string.request_fail, Toast.LENGTH_LONG).show();
    }

    private void hideLoadingAndInfo() {
        loading.setVisibility(View.INVISIBLE);
        searchInfo.setVisibility(View.INVISIBLE);
    }

    private void showEmptyView() {
        mainContainer.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onUserSelection(User user);
        void onRepoSelection(Repo repo);
    }
}
