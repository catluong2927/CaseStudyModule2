import person.*;
import album.*;
import song.*;
import java.io.IOException;



public class Main {
    public static void main(String[] args) {
        try {
            processSong.getInstance().readFromFile();
            processAlbum.getInstance().readFromFile();
            processPeople.getInstance().readFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MenuPeople c = new MenuPeople();
        c.displayMenuPeople();
        c.doMainMenuPeople();

    }
}
