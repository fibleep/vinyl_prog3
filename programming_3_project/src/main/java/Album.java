import java.time.LocalDate;
import java.util.List;

public class Album {
    String name;
    List<Song> songs;
    LocalDate year;
    Genre genre;
    Author artist;

    public Album(String name, LocalDate year, Genre genre, Author artist, List<Song> songs) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.artist = artist;
        this.songs = songs;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getArtist() {
        return artist;
    }

    public void setArtist(Author artist) {
        this.artist = artist;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "\nALBUM" +
                "\n------------------" +
                "\nNAME : " + name +
                "\nSONGS : " + songs +
                "\nYEAR : " + year +
                "\nAUTHORS : " + artist.getName() +
                "\nGENRE : " + genre +
                "\n------------------";
    }
}
