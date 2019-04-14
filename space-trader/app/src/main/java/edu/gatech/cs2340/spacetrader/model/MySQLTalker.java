package edu.gatech.cs2340.spacetrader.model;


import com.BoardiesITSolutions.AndroidMySQLConnector.Connection;
import com.BoardiesITSolutions.AndroidMySQLConnector.IResultInterface;
import com.BoardiesITSolutions.AndroidMySQLConnector.ResultSet;
import com.BoardiesITSolutions.AndroidMySQLConnector.Statement;
import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.InvalidSQLPacketException;
import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.MySQLConnException;
import com.BoardiesITSolutions.AndroidMySQLConnector.Exceptions.MySQLException;
import com.BoardiesITSolutions.AndroidMySQLConnector.IConnectionInterface;

import java.io.IOException;


public class  MySQLTalker {

    private static Connection conn;
    private static boolean connected = false;
    private static boolean queryInProgress = false;
    private static ResultSet resultSet = null;

    public static boolean isConnected() {
        return connected;
    }

    private static void setConnected(boolean connected) {
        System.out.println("CONNECTED TO SERVER! Server on MySQL Version "
                + conn.getServerVersion());
        MySQLTalker.connected = connected;
    }

    public static boolean isQueryInProgress() {
        return queryInProgress;
    }

    private static void setQueryInProgress(boolean queryInProgress) {
        if (!queryInProgress) {
            System.out.println("QUERY COMPLETED!");
        }
        MySQLTalker.queryInProgress = queryInProgress;
    }

    /**
     * Gets the ResultSet object after query executed.
     * Sets result set to null
     *
     * @return See BoardiesITSolutions for documentation on this object
     * @throws IllegalStateException If query in progress or result set not obtained.
     */
    public static ResultSet getResultSet() throws IllegalStateException {
        if (queryInProgress) {
            throw new IllegalStateException("Query in progress. Please wait.");
        }
        if (resultSet == null) {
            throw new IllegalStateException("Result set not obtained.");
        }
        ResultSet temp =  resultSet;
        resultSet = null;
        return temp;
    }

    private static void setResultSet(ResultSet resultSet) {
        MySQLTalker.resultSet = resultSet;
    }

    /**
     * Attempts to establish connection to server
     * NOTE! Use isConnected() to verify connection status
     */
    public static void initialize() {

        System.out.println("Initializing MySQL Talker");

        conn = new Connection("172.0.50.109", "root", "bobwaters",
                42424, "spacetrader", new IConnectionInterface() {

            @Override
            public void actionCompleted() {
                System.out.println("Action Completed");
                MySQLTalker.setConnected(true);
            }

            @Override
            public void handleInvalidSQLPacketException(InvalidSQLPacketException ex) {
                System.out.println("Invalid SQL Packet Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleMySQLException(MySQLException ex) {
                System.out.println("MySQL Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleIOException(IOException ex) {
                System.out.println("IO Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleMySQLConnException(MySQLConnException ex) {
                System.out.println("ERROR!!!!! MySQL Connection Exception");
                System.out.println(ex.getMessage());
            }

            @Override
            public void handleException(Exception exception) {
                System.out.println("Exception");
                //System.out.println(exception.getStackTrace());
            }
        });



        //System.out.println("HERE'S THE CONNECTION OBJECT! Memory Address: "
        // +mysqlConnection.toString());
        /*try {
            //System.out.println("Sleeping to allow connection establishment");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("SLEEP FAILED!!!!");
        }*/

        /*System.out.println("Waiting for connection to establish...");
        double time = 0;
        do  {
            try {
                System.out.printf("%.2f seconds...%n", time);
                Thread.sleep(50);
                time += 0.05;
            } catch (InterruptedException e) {
                System.out.println("Timer failed! Second count is inaccurate");
            }
            if (time > 5) {
                conn = null;
                System.out.println("Error! Connection timed out.");
                break;
            }
        } while (conn.getServerVersion() == null);*/

        if (connected) {
            System.out.println("Connection Established! Database version MySQL: "
                    + conn.getServerVersion());
        }
    }

    /**
     * Executes a query which WILL NOT obtain a result.
     *
     * @param query
     * @throws RuntimeException If connection not established, or another query still in progress
     */
    public static void executeNonReturningQuery(String query) throws RuntimeException {
        if (!connected) {
            System.out.println("Database connection not established");
            throw new RuntimeException("Database connection not established");
        }
        if (queryInProgress) {
            System.out.println("Cannot execute multiple queries simultaneously.");
            throw new RuntimeException("Cannot execute multiple queries simultaneously.");
        }
        queryInProgress = true;
        Statement statement = conn.createStatement();
        statement.execute(query, new IConnectionInterface() {
            @Override
            public void actionCompleted() {
                System.out.println("Query Executed Successfully");
                setQueryInProgress(false);
            }

            @Override
            public void handleInvalidSQLPacketException(InvalidSQLPacketException ex) {
                System.out.println("Invalid SQL Packet Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleMySQLException(MySQLException ex) {
                System.out.println("MySQL Exception");
                //System.out.println(ex);
                ////System.out.println(query);
            }

            @Override
            public void handleIOException(IOException ex) {
                System.out.println("IO Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleMySQLConnException(MySQLConnException ex) {
                System.out.println("MySQL Connection Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleException(Exception exception) {
                System.out.println("Exception");
                //System.out.println(exception.getStackTrace());
            }
        });

        /*double time = 0;
        System.out.println("Executing query...");
        do  {
            try {
                System.out.printf("%.2f seconds...%n", time);
                Thread.sleep(50);
                time += 0.05;
            } catch (InterruptedException e) {
                System.out.println("Timer failed! Second count is inaccurate");
            }
            if (time > 5) {
                conn = null;
                System.out.println("Error! Query timed out.");
                break;
            }
        } while (queryInProgress);*/

    }

    /**
     * Executes a query which will obtain a result.
     * This method DOES NOT return that result.
     * For the result, call getResult() method.
     *
     * @param query Query to be executed. Will obtain result.
     * @throws RuntimeException If connection not established, or another query still in progress
     */
    public static void executeReturningQuery(String query) throws RuntimeException {
        if (!connected) {
            System.out.println("Database connection not established");
            throw new RuntimeException("Database connection not established");
        }
        if (queryInProgress) {
            System.out.println("Cannot execute multiple queries simultaneously.");
            throw new RuntimeException("Cannot execute multiple queries simultaneously.");
        }

        queryInProgress = true;
        Statement statement = conn.createStatement();
        statement.executeQuery(query, new IResultInterface() {
            @Override
            public void executionComplete(ResultSet rSet) {
                System.out.println("Query Executed Successfully");
                setResultSet(rSet);
                setQueryInProgress(false);
            }

            @Override
            public void handleInvalidSQLPacketException(InvalidSQLPacketException ex) {
                System.out.println("Invalid SQL Packet Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleMySQLException(MySQLException ex) {
                System.out.println("MySQL Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleIOException(IOException ex) {
                System.out.println("IO Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleMySQLConnException(MySQLConnException ex) {
                System.out.println("MySQL Connection Exception");
                //System.out.println(ex.getStackTrace());
            }

            @Override
            public void handleException(Exception exception) {
                System.out.println("Exception");
                //System.out.println(exception.getStackTrace());
            }
        });

        /*double time = 0;
        System.out.println("Executing query...");
        do  {
            try {
                System.out.printf("%.2f seconds...%n", time);
                Thread.sleep(50);
                time += 0.05;
            } catch (InterruptedException e) {
                System.out.println("Timer failed! Second count is inaccurate");
            }
            if (time > 5) {
                conn = null;
                System.out.println("Error! Query timed out.");
                break;
            }
        } while (queryInProgress);*/
    }

    public static void closeConnection() {
        conn.close();
        conn = null;
    }
}
