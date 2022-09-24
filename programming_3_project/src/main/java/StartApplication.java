import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartApplication {
    public static void main(String[] args) {
        DataFactory.seed();
        List<Author> authors = DataFactory.getAuthors();
        List<Album> albums = DataFactory.getAlbums();
        List<Song> songs = DataFactory.getSongs();
        while(true) {
            switch (GUI.menu()) {
                case SHOW_SONGS -> {
                    for (Song song : songs) {
                        System.out.println(song);
                    }
                }
                case SHOW_ALBUMS -> {
                    for (Album album : albums) {
                        System.out.println(album);
                    }
                }
                case SHOW_SONGS_OF_AUTHOR -> {
                    System.out.println("Enter author name: ");
                    Scanner scanner = new Scanner(System.in);
                    String authorName = scanner.nextLine();
                    songs.stream().filter(song -> song.getAuthor().stream().anyMatch(author -> author.getName().equals(authorName))).forEach(System.out::println);
                }
                case SHOW_ALBUMS_OF_AUTHOR_FROM_YEAR -> {
                    System.out.println("Enter author name: ");
                    Scanner scanner = new Scanner(System.in);
                    String authorName = scanner.nextLine();
                    System.out.println("Enter year: ");
                    int year = scanner.nextInt();
                    albums.stream().filter(album -> album.getArtist().getName().equals(authorName) && album.getYear().getYear() == year).forEach(System.out::println);
                }
            }
        }
    }
}
