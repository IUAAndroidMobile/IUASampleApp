package com.nicolasfanin.IUASampleApp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.dto.GitHubRepo;
import com.nicolasfanin.IUASampleApp.utils.GitHubRepoAdapter;

import java.util.List;

import static com.nicolasfanin.IUASampleApp.utils.Constants.GIT_HUB_REPO;

public class GitHubRepoListFragment extends Fragment {

    private List<GitHubRepo> gitHubRepoList;

    private RecyclerView gitHubRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.git_hub_repo_list_fragment, container, false);
        gitHubRecyclerView = view.findViewById(R.id.git_hub_repo_recycler_view);
        gitHubRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gitHubRepoList = getArguments().getParcelableArrayList(GIT_HUB_REPO);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GitHubRepoAdapter adapter = new GitHubRepoAdapter(gitHubRepoList);
        gitHubRecyclerView.setAdapter(adapter);
    }
}
