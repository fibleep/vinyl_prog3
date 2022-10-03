import java.sql.Timestamp;
import java.util.List;

public class Song {
    String title;
    int index;
    double length;
    List<Author> author;
    Album album;

    public Song(String title, int index, double length, List<Author> author, Album album) {
        this.title = title;
        this.index = index;
        this.length = length;
        this.author = author;
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void addAuthor(Author author) {
        this.author.add(author);
    }

    public void removeAuthor(Author author) {
        this.author.remove(author);
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    @Override
    public String toString() {
        // get a list of all the author names
        StringBuilder authorNames = new StringBuilder();
        for (Author author : this.author) {
            authorNames.append(author.getName()).append(" | ");
        }
        return "\nSONG" +
                "\n------------------" +
                "\n [ TITLE : " + title +
                " | INDEX : " + index +
                " | LENGTH : " + length +
                " | AUTHOR : " + authorNames+
                "ALBUM : " + album.getName()+" ]" +
                "\n------------------";
    }
}
