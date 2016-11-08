package com.app.githubclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.githubclient.R;
import com.app.githubclient.fragments.SearchFragment;
import com.app.githubclient.values.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User} and makes a call to the
 * specified {@link SearchFragment.OnListFragmentInteractionListener}.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final Context context;
    private final List<User> users;
    private final SearchFragment.OnListFragmentInteractionListener listener;

    public UserAdapter(Context context, List<User> users, SearchFragment.OnListFragmentInteractionListener listener) {
        this.context = context;
        this.users = users;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.user = users.get(position);
        Picasso.with(context).load(users.get(position).getProfilePhotoPath()).into(holder.profilePicture);
        holder.userName.setText(users.get(position).getUserName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onUserSelection(holder.user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final ImageView profilePicture;
        public final TextView userName;
        public User user;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            profilePicture = (ImageView) view.findViewById(R.id.profile_picture);
            userName = (TextView) view.findViewById(R.id.user_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + userName.getText() + "'";
        }
    }
}
