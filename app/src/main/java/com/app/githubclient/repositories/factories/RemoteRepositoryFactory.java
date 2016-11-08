package com.app.githubclient.repositories.factories;

import com.app.githubclient.repositories.remote.RemoteRepoRepository;
import com.app.githubclient.repositories.remote.RemoteUserRepository;
import com.app.githubclient.repositories.types.RepoRepository;
import com.app.githubclient.repositories.types.UserRepository;

public class RemoteRepositoryFactory implements RepositoryFactory {

    private static UserRepository remoteUserRepository;
    private static RepoRepository remoteRepositoryRepository;

    @Override
    public UserRepository forUser() {
        if (remoteUserRepository == null) {
            remoteUserRepository = new RemoteUserRepository();
        }
        return remoteUserRepository;

    }

    @Override
    public RepoRepository forRepo() {
        if (remoteRepositoryRepository == null) {
            remoteRepositoryRepository = new RemoteRepoRepository();
        }
        return remoteRepositoryRepository;
    }
}
