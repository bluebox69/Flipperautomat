package flipper.factory;

public interface AsciiArtFactory {
    String renderPressStart();

    String renderGameOver();

    String renderBall(int ballNumber);

    String renderWelcome();
}