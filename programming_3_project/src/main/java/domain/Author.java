package domain;

import java.util.List;

public class Author {
    String name;
    int age;
    String gender;
    List<Album> album;
    List<Song> song;

    public Author(String name, int age, String gender, List<Album> album, List<Song> song) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.album = album;
        this.song = song;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void addAlbum(Album album) {
        this.album.add(album);
    }

    public void removeAlbum(Album album) {
        this.album.remove(album);
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    public List<Song> getSong() {
        return song;
    }

    public void addSong(Song song) {
        this.song.add(song);
    }

    public void removeSong(Song song) {
        this.song.remove(song);
    }

    public void setSong(List<Song> song) {
        this.song = song;
    }

    @Override
    public String toString() {
        // get a list of all the album names
        StringBuilder albumNames = new StringBuilder();
        for (Album album : this.album) {
            albumNames.append(album.getName()).append(" | ");
        }
        // get a list of all the song names
        StringBuilder songNames = new StringBuilder();
        for (Song song : this.song) {
            songNames.append(song.getTitle()).append(" | ");
        }

        return "\nAUTHOR" +
                "\n------------------" +
                "\n [ NAME : " + name +
                " | AGE : " + age +
                " | GENDER : " + gender +
                " | ALBUMS : " + albumNames+
                "SONGS : " + songNames+"" +
                "\n------------------";
    }
}
