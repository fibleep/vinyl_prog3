import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataFactory {
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
        Album wojnyNoce = new Album("Wojny i noce", LocalDate.parse("2021-01-01"),Genre.ALT,dariaZ,null);
        Album pocztowka = new Album("POCZTÓWKA Z WWA, LATO '19", LocalDate.parse("2019-01-01"),Genre.RAP,tacoH,null);
        Album wosk = new Album("Wosk", LocalDate.parse("2020-01-01"),Genre.RAP,tacoH,null);
        Album malomiasteczkowy = new Album("Małomiasteczkowy", LocalDate.parse("2018-01-01"),Genre.POP,dawidP,null);
        Album romanticpsycho = new Album("ROMANTICPSYCHO", LocalDate.parse("2020-01-01"),Genre.RAP,quebo,null);
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
        wojnyNoce.songs=List.of(zaKrotkiSen,flowerNight,serce,hollow);
        pocztowka.songs=List.of(wanna,future,sanatorium,wwa);
        wosk.songs=List.of(woskSong,bxl,szczerze,wiatr);
        malomiasteczkowy.songs=List.of(malomiasteczkowySong,najnowszyKlip,trofea,nieMaFal);
        romanticpsycho.songs=List.of(romanticpsychoSong,jesien,tokyo2020,bubbletea);
        // add albums to authors
        tacoH.album=List.of(pocztowka,wosk);
        dawidP.album=List.of(malomiasteczkowy);
        dariaZ.album=List.of(wojnyNoce);
        quebo.album=List.of(romanticpsycho);
        // add authors to songs
        zaKrotkiSen.author=List.of(dariaZ,dawidP);
        flowerNight.author=List.of(dariaZ);
        serce.author=List.of(dariaZ);
        hollow.author=List.of(dariaZ);
        wanna.author=List.of(tacoH,dawidP);
        future.author=List.of(tacoH);
        sanatorium.author=List.of(tacoH,dawidP);
        wwa.author=List.of(tacoH);
        woskSong.author=List.of(tacoH);
        bxl.author=List.of(tacoH);
        szczerze.author=List.of(tacoH);
        wiatr.author=List.of(tacoH);
        malomiasteczkowySong.author=List.of(dawidP);
        najnowszyKlip.author=List.of(dawidP);
        trofea.author=List.of(dawidP);
        nieMaFal.author=List.of(dawidP);
        romanticpsychoSong.author=List.of(quebo,tacoH);
        jesien.author=List.of(quebo);
        tokyo2020.author=List.of(quebo,tacoH);
        bubbletea.author=List.of(quebo,dariaZ);
        // add songs to authors
        dariaZ.song=List.of(zaKrotkiSen,flowerNight,serce,hollow);
        tacoH.song=List.of(wanna,future,sanatorium,wwa,woskSong,bxl,szczerze,wiatr);
        dawidP.song=List.of(malomiasteczkowySong,najnowszyKlip,trofea,nieMaFal);
        quebo.song=List.of(romanticpsychoSong,jesien,tokyo2020,bubbletea);



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
