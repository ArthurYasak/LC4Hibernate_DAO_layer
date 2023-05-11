package application.functionalInterfaces;

import application.exceptions.DAOException;

@FunctionalInterface
public interface MyRunnable {
    public abstract void run() throws DAOException;
}
