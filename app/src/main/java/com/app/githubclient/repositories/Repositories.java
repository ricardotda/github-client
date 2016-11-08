package com.app.githubclient.repositories;

import com.app.githubclient.repositories.factories.RemoteRepositoryFactory;
import com.app.githubclient.repositories.factories.RepositoryFactory;

public class Repositories {

    /** no instances **/
    private Repositories() {}

    public static RepositoryFactory repository() {
        return new RemoteRepositoryFactory();
    }
}
