package flipper.state;

import flipper.state.*;

public class FlipperMachine {
    private FlipperState currentState;
    private int credit = 0;
    private int ballCount = 3;

    //all states
    // States
    private final FlipperState noCreditState;
    private final FlipperState readyState;
    private final FlipperState playingState;
    private final FlipperState endState;

    public FlipperMachine() {
        noCreditState = new NoCreditState();
        readyState = new ReadyState();
        playingState = new PlayingState();
        endState = new EndState();

        currentState = noCreditState; // dies ist der Initialzustand
    }

    // setter
    public void setState(FlipperState state) {
        this.currentState = state;
    }

    // getter
    public FlipperState getNoCreditState() {
        return noCreditState;
    }

    public FlipperState getReadyState() {
        return readyState;
    }

    public FlipperState getPlayingState() {
        return playingState;
    }

    public FlipperState getEndState() {
        return endState;
    }

    public String getCurrentStateName() {
        return currentState.getClass().getSimpleName();
    }

    //

    public int getCredit() {
        return credit;
    }

    public void addCredit() {
        credit++;
    }

    public void reduceCredit() {
        if (credit > 0) {
            credit--;
        } else {
            System.out.println("Kredit kann nicht negativ sein!");
        }
    }

    public int getBallCount() {
        return ballCount;
    }

    public void reduceBall() {
        if (ballCount > 0) {
            ballCount--;
        } else {
            System.out.println("Keine Bälle mehr vorhanden!");
        }
    }

    public void resetBallCount() {
        ballCount = 3;
    }

    // Delegation der Aktionen an den aktuellen Zustand
    public void insertCoin() {
        currentState.insertCoin(this);
    }

    public void pressStart() {
        if (credit <= 0) {
            System.out.println("Nicht genug Kredit, um das Spiel zu starten!");
            return;
        }
        currentState.pressStart(this);
    }

    public void loseBall() {
        currentState.loseBall(this);
    }


}
