package album;

import java.util.*;

public class Album {
    static int SizeAlbum = 0;
    Scanner sc = new Scanner(System.in);
    private int id;
    private String name;
    private String typeAlbum;
    private List<Integer> songs;

    public void setName(String name) {
        this.name = name;
    }
    public void setTypeAlbum(String typeAlbum) {
        this.typeAlbum = typeAlbum;
    }

    public void setSongs(int obj) {
        this.songs.add(obj);
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getTypeAlbum() {
        return this.typeAlbum;
    }

    public List<Integer> getSongs() {
        return songs;
    }
    public Album(String name, String typeAlbum) {
        int id = SizeAlbum++;
        this.id = id;
        this.name = name;
        this.typeAlbum = typeAlbum;
        this.songs = new LinkedList<>();
    }
    public String toFile() {
        return this.name+"/"+this.typeAlbum+"/"+this.getSongs();
    }
}
