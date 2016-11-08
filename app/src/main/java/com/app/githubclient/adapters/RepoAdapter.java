package com.app.githubclient.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.githubclient.R;
import com.app.githubclient.fragments.SearchFragment;
import com.app.githubclient.values.Repo;
import com.app.githubclient.values.User;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User} and makes a call to the
 * specified {@link SearchFragment.OnListFragmentInteractionListener}.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private final List<Repo> repos;
    private final SearchFragment.OnListFragmentInteractionListener listener;

    public RepoAdapter(List<Repo> users, SearchFragment.OnListFragmentInteractionListener listener) {
        this.repos = users;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.repo = repos.get(position);
        holder.repoName.setText(repos.get(position).getName());
        holder.ownerName.setText(repos.get(position).getUser().getUserName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onRepoSelection(holder.repo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView repoName;
        public final TextView ownerName;
        public Repo repo;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            repoName = (TextView) view.findViewById(R.id.repo_name);
            ownerName = (TextView) view.findViewById(R.id.owner_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + repoName.getText() + "'";
        }
    }
}
