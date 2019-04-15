package edu.gatech.cs2340.spacetrader.model;

/**
 *
 */
public abstract class Interactor {

    private final Repository myRepository;

    /**
     *
     * @param repo repository to set to
     */
    protected Interactor(Repository repo) {
        myRepository = repo;
    }

    /**
     * returns repository
     * @return repository
     */
    public Repository getRepository() {
        return myRepository;
    }

}
