package flipper.factory;

public class FactorySelector {
    private static AsciiArtFactory currentFactory;

    public static void setFactory(AsciiArtFactory factory) {
        currentFactory = factory;
    }

    public static AsciiArtFactory getFactory() {
        if (currentFactory == null) {
            throw new IllegalStateException("Keine Factory gesetzt.");
        }
        return currentFactory;
    }
}
