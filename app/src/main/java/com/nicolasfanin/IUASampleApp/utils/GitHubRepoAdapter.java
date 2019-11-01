package com.nicolasfanin.IUASampleApp.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.dto.GitHubRepo;

import java.util.List;

public class GitHubRepoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GitHubRepo> gitHubRepoList;

    public GitHubRepoAdapter(List<GitHubRepo> gitHubRepoList) {
        this.gitHubRepoList = gitHubRepoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GitHubRepoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.git_hub_repo_item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GitHubRepo githubRepo = gitHubRepoList.get(i);
        ((GitHubRepoViewHolder) viewHolder).getGitHubRepoNameTextView().setText(githubRepo.getName());
    }

    @Override
    public int getItemCount() {
        return gitHubRepoList.size();
    }


    class GitHubRepoViewHolder extends RecyclerView.ViewHolder {

        private TextView gitHubRepoTextView;

        public GitHubRepoViewHolder(@NonNull View itemView) {
            super(itemView);
            gitHubRepoTextView = itemView.findViewById(R.id.git_hub_repo_text_view);
        }

        public TextView getGitHubRepoNameTextView() {
            return gitHubRepoTextView;
        }
    }
}
