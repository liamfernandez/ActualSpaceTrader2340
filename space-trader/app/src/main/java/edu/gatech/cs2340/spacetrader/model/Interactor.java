package edu.gatech.cs2340.spacetrader.model;

public abstract class Interactor {

    private final Repository myRepository;

    protected Interactor(Repository repo) {
        myRepository = repo;
    }

    public Repository getRepository() {
        return myRepository;
    }

}
