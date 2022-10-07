package com.musicdatabase.service.repository;


import com.musicdatabase.service.domain.Album;
import com.musicdatabase.service.domain.Author;
import com.musicdatabase.service.domain.Genre;
import com.musicdatabase.service.domain.Song;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class DataFactoryImplementation {
    public static List<Song> songs;
    public static List<Author> authors;

    public static List<Album> albums;
    public static void seed(){
        songs = new ArrayList<>();
        authors = new ArrayList<>();
        albums = new ArrayList<>();
        //create authors
        Author tacoH=new Author("Taco Hemingway",32,"male",null,null);
        Author dawidP=new Author("Dawid Podsiadło",29,"male",null,null);
        Author dariaZ=new Author("Daria Zawiałow",30,"female",null,null);
        Author quebo=new Author("Quebonafide",31,"male",null,null);
        // create albums
        Album wojnyNoce = new Album("Wojny i noce", LocalDateTime.parse("2021-01-01T00:00:00"), Genre.ALT,dariaZ,null);
        Album pocztowka = new Album("POCZTÓWKA Z WWA, LATO '19", LocalDateTime.parse("2019-01-01T00:00:00"),Genre.RAP,tacoH,null);
        Album wosk = new Album("Wosk", LocalDateTime.parse("2020-01-01T00:00:00"),Genre.RAP,tacoH,null);
        Album malomiasteczkowy = new Album("Małomiasteczkowy", LocalDateTime.parse("2018-01-01T00:00:00"),Genre.POP,dawidP,null);
        Album romanticpsycho = new Album("ROMANTICPSYCHO", LocalDateTime.parse("2020-01-01T00:00:00"),Genre.RAP,quebo,null);
        // create songs
        Song zaKrotkiSen = new Song("Za krótki sen",4,4.01,List.of(dariaZ,dawidP),wojnyNoce);
        Song flowerNight = new Song("Flower Night",12,4.27,List.of(dariaZ),wojnyNoce);
        Song serce = new Song("Serce",11,3.49,List.of(dariaZ),wojnyNoce);
        Song hollow = new Song("Hollow",10,3.54,List.of(dariaZ),wojnyNoce);

        Song wanna = new Song("W PIĄTKI LEŻĘ W WANNIE",4,3.40,List.of(tacoH,dawidP),pocztowka);
        Song future = new Song("LECI NOWY FUTURE",3,3.39,List.of(tacoH),pocztowka);
        Song sanatorium = new Song("SANATORIUM",9,4.34,List.of(tacoH,dawidP),pocztowka);
        Song wwa = new Song("WWA VHS",5,4.41,List.of(tacoH),pocztowka);

        Song woskSong = new Song("Wosk",1,3.53,List.of(tacoH),wosk);
        Song bxl = new Song("BXL",2,3.52,List.of(tacoH),wosk);
        Song szczerze = new Song("Szczerze",3,3.21,List.of(tacoH),wosk);
        Song wiatr = new Song("Wiatr",4,3.41,List.of(tacoH),wosk);

        Song malomiasteczkowySong = new Song("Małomiasteczkowy",3,3.23,List.of(dawidP),malomiasteczkowy);
        Song najnowszyKlip = new Song("Najnowszy klip",4,3.49,List.of(dawidP),malomiasteczkowy);
        Song trofea = new Song("Trofea",5,3.44,List.of(dawidP),malomiasteczkowy);
        Song nieMaFal = new Song("Nie ma fal",6,3.58,List.of(dawidP),malomiasteczkowy);

        Song romanticpsychoSong = new Song("ROMANTICPSYCHO",1,3.36,List.of(quebo,tacoH),romanticpsycho);
        Song jesien = new Song("JESIEŃ",2,3.32,List.of(quebo),romanticpsycho);
        Song tokyo2020 = new Song("TOKYO2020",12,3.32,List.of(quebo,tacoH),romanticpsycho);
        Song bubbletea = new Song("BUBBLETEA",15,4.44,List.of(quebo,dariaZ),romanticpsycho);

        // add songs to albums
        wojnyNoce.setSongs(List.of(zaKrotkiSen, flowerNight, serce, hollow));
        pocztowka.setSongs(List.of(wanna, future, sanatorium, wwa));
        wosk.setSongs(List.of(woskSong, bxl, szczerze, wiatr));
        malomiasteczkowy.setSongs(List.of(malomiasteczkowySong, najnowszyKlip, trofea, nieMaFal));
        romanticpsycho.setSongs(List.of(romanticpsychoSong, jesien, tokyo2020, bubbletea));
        // add albums to authors
        tacoH.setAlbum(List.of(pocztowka, wosk));
        dawidP.setAlbum(List.of(malomiasteczkowy));
        dariaZ.setAlbum(List.of(wojnyNoce));
        quebo.setAlbum(List.of(romanticpsycho));
        // add authors to songs
        zaKrotkiSen.setAuthor(List.of(dariaZ, dawidP));
        flowerNight.setAuthor(List.of(dariaZ));
        serce.setAuthor(List.of(dariaZ));
        hollow.setAuthor(List.of(dariaZ));
        wanna.setAuthor(List.of(tacoH, dawidP));
        future.setAuthor(List.of(tacoH));
        sanatorium.setAuthor(List.of(tacoH, dawidP));
        wwa.setAuthor(List.of(tacoH));
        woskSong.setAuthor(List.of(tacoH));
        bxl.setAuthor(List.of(tacoH));
        szczerze.setAuthor(List.of(tacoH));
        wiatr.setAuthor(List.of(tacoH));
        malomiasteczkowySong.setAuthor(List.of(dawidP));
        najnowszyKlip.setAuthor(List.of(dawidP));
        trofea.setAuthor(List.of(dawidP));
        nieMaFal.setAuthor(List.of(dawidP));
        romanticpsychoSong.setAuthor(List.of(quebo, tacoH));
        jesien.setAuthor(List.of(quebo));
        tokyo2020.setAuthor(List.of(quebo, tacoH));
        bubbletea.setAuthor(List.of(quebo, dariaZ));
        // add songs to authors
        dariaZ.setSong(List.of(zaKrotkiSen, flowerNight, serce, hollow));
        tacoH.setSong(List.of(wanna, future, sanatorium, wwa, woskSong, bxl, szczerze, wiatr));
        dawidP.setSong(List.of(malomiasteczkowySong, najnowszyKlip, trofea, nieMaFal));
        quebo.setSong(List.of(romanticpsychoSong, jesien, tokyo2020, bubbletea));



        // fill songs
        songs.addAll(List.of(zaKrotkiSen,flowerNight,serce,hollow,wanna,future,sanatorium,wwa,woskSong,bxl,szczerze,wiatr,malomiasteczkowySong,najnowszyKlip,trofea,nieMaFal,romanticpsychoSong,jesien,tokyo2020,bubbletea));
        // fill authors
        authors.addAll(List.of(tacoH,dawidP,dariaZ,quebo));
        // fill albums
        albums.addAll(List.of(wojnyNoce,pocztowka,wosk,malomiasteczkowy,romanticpsycho));
    }

    public static List<Song> getSongs() {
        return songs;
    }

    public static List<Author> getAuthors() {
        return authors;
    }
    public static List<Album> getAlbums() {
        return albums;
    }
}
