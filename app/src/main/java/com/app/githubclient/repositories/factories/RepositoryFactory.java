package com.app.githubclient.repositories.factories;

import com.app.githubclient.repositories.types.RepoRepository;
import com.app.githubclient.repositories.types.UserRepository;

public interface RepositoryFactory {

    UserRepository forUser();
    RepoRepository forRepo();

}
