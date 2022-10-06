import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Album;
import domain.Author;
import domain.Song;
import presentation.GUI;
import presentation.View;
import repository.DataFactory;

import java.util.List;
import java.util.Scanner;

public class StartApplication {
    public static void main(String[] args) {
        View view = new View();
        view.initialize();
    }
}
