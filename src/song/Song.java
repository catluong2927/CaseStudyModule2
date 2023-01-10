package song;

import java.util.*;

public class Song {
    static int SizeSong = 0;
    Scanner sc = new Scanner(System.in);
    private int id;
    private String name;
    private String typemusic;
    private String time;
    private String composer;
    private String singer;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeMusic(String typeMusic) {
        this.typemusic = typeMusic;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeMusic() {
        return typemusic;
    }

    public String getTime() {
        return time;
    }

    public String getSinger() {
        return singer;
    }

    public String getComposer() {
        return composer;
    }

    public Song(String name, String typeMusic, String time,String singer,String composer) {
        int id = SizeSong++;
        this.id = id;
        this.name = name;
        this.typemusic = typeMusic;
        this.time = time;
        this.singer = singer;
        this.composer = composer;
    }
    public String toFile() {
        return this.name+"/"+this.typemusic+"/"+this.time+"/"+this.singer+"/"+this.composer;
    }
}
