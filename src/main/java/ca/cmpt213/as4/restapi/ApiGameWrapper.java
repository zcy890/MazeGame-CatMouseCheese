package ca.cmpt213.as4.restapi;


import ca.cmpt213.as4.model.GameManager;

/**
 * This wrapper class converts data about the general game information from ui to backend and vice versa
 */

public class ApiGameWrapper {
    public long gameNumber;
    public boolean isGameWon;
    public boolean isGameLost;
    public int numCheeseFound;
    public int numCheeseGoal;

    // MAY NEED TO CHANGE PARAMETERS HERE TO SUITE YOUR PROJECT
    public static ApiGameWrapper makeFromGame(GameManager manager, long id) {
        ApiGameWrapper wrapper = new ApiGameWrapper();
        wrapper.setGameNumber(id);
        wrapper.setGameWon(manager.isWon());
        wrapper.setGameLost(manager.isLost());
        wrapper.setNumCheeseGoal(manager.getNumOfCheeseToBeCollected());
        wrapper.setNumCheeseFound(manager.getNumOfCheeseCollected());
        // Populate this object!

        return wrapper;
    }

    public long getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(long gameNumber) {
        this.gameNumber = gameNumber;
    }

    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
    }

    public void setGameLost(boolean gameLost) {
        isGameLost = gameLost;
    }

    public void setNumCheeseFound(int numCheeseFound) {
        this.numCheeseFound = numCheeseFound;
    }

    public void setNumCheeseGoal(int numCheeseGoal) {
        this.numCheeseGoal = numCheeseGoal;
    }
}
