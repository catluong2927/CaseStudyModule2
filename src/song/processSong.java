package song;

import album.*;
import person.People;

import java.io.*;
import java.util.*;

public class processSong {
    int count;
    private static processSong instance;

    private List<Song> listSong;

    private processSong() {
        listSong = new LinkedList<>();

    }

    public List<Song> getListSong() {
        return listSong;
    }

    public static processSong getInstance() {
        if (instance == null) {
            instance = new processSong();
        }
        return instance;
    }

    Scanner sc = new Scanner(System.in);

    public void addSong(Album obj) {
        int size;
        if (listSong.size() == 0) size = 0;
        else size = listSong.size();
        System.out.println("nhập số lượng bài hát muốn thêm:");
        int numSong = sc.nextInt();
        sc.nextLine();
        for (int i = size, k = 1; (i - size) < numSong && k <= numSong; i++, k++) {
            System.out.println("nhập tên bài hát thứ: " + k + ":");
            String name = sc.nextLine();
            System.out.println("nhập dòng nhạc:");
            String type = sc.nextLine();
            System.out.println("nhập thời lượng");
            String time = sc.nextLine();
            System.out.println("nhập tên ca sĩ thể hiện: ");
            String singer = sc.nextLine();
            System.out.println("nhập tên nhạc sĩ sáng tác: ");
            String composer = sc.nextLine();
            listSong.add(new Song(name, type, time, singer, composer));
            obj.setSongs(i);
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//

    public void fixSong(Album obj) {
        processAlbum.getInstance().showListSong(obj);
        System.out.println("chọn id bài hát bạn muốn sửa:");
        int chooseSong = sc.nextInt();
        sc.nextLine();
        for (Song element : listSong) {
            if (element.getId() == chooseSong) {
                System.out.println("chọn thông tin bạn muốn sửa (tên - dòng nhạc - thời lượng - ca sĩ  - nhạc sĩ)");
                String fixInfor = sc.nextLine().toLowerCase();
                switch (fixInfor) {
                    case "tên":
                        System.out.println("tên mới là gì?");
                        String name = sc.nextLine();
                        obj.setName(name);
                        break;
                    case "dòng nhạc":
                        System.out.println("dòng nhạc là gì?");
                        String type = sc.nextLine();
                        element.setTypeMusic(type);
                        break;
                    case "thời lượng":
                        System.out.println("thời lượng bao lâu?");
                        String time = sc.nextLine();
                        element.setTime(time);
                        break;
                    case "ca sĩ":
                        System.out.println("ca sĩ thể hiện:");
                        String singer = sc.nextLine();
                        element.setSinger(singer);
                        break;
                    case "nhạc sĩ":
                        System.out.println("nhạc sĩ sáng tác:");
                        String composer = sc.nextLine();
                        element.setComposer(composer);
                        break;
                }
            }
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//

    public void searchSongOfObj(Album obj) {
        System.out.println("chọn tên bài hát muốn tìm kiếm: ");
        String name = sc.nextLine();
        Song songs;
        int checkId = -1;
        while (name.equals("")) {
            System.out.println("tên bài hát không được để trống, vui lòng nhập lại");
            name = sc.nextLine();
        }
        checkId = searchSongName(name).getId();
        for (int element : obj.getSongs()) {
            if (element == checkId) displaySongInfo(searchSongName(name));
        }
        if (checkId == -1) System.out.println("không tồn tại bài hát này");
    }//

    public void displayListSong(Album obj) {
        if (!obj.getSongs().isEmpty()) {
            for (int element : obj.getSongs()) {
                System.out.println(displaySongInfo(searchSongById(element)));
            }
        } else System.out.println("danh sách đang trống");
    }//

    public void removeSong(Album obj) {
        processAlbum.getInstance().showListSong(obj);
        System.out.println("chọn id bài hát bạn muốn xóa:");
        int chooseSong = sc.nextInt();
        sc.nextLine();
        for (int element : obj.getSongs()) {
            if (element == chooseSong) obj.getSongs().remove(element);
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Song searchSongById(int id) {
        for (Song element : listSong) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    public boolean searchSinger(String singer) {
        for (Song element : listSong) {
            if (element.getSinger().equals(singer)) return true;
        }
        return false;
    }

    public boolean searchComposer(String composer) {
        for (Song element : listSong) {
            if (element.getComposer().equals(composer)) return true;
        }
        return false;
    }
    public String searchSongSinger() {
        System.out.println("nhập tên ca sĩ muốn tìm:");
        String singer = sc.nextLine();
        String text ="";
        for (Song element : listSong) {
            if (element.getSinger().equals(singer)) text += displaySongInfo(element);
        }
        if(text.equals("")) text = "không có nhạc sĩ này";
        return text;
    }

    public String searchSongComposer() {
        System.out.println("nhập tên nhạc sĩ muốn tìm:");
        String composer = sc.nextLine();
        String text ="";
        for (Song element : listSong) {
            if (element.getComposer().equals(composer)) text += displaySongInfo(element);
        }
        if(text.equals("")) text = "không có nhạc sĩ này";
        return text;
    }

    public Song searchAlbumId(int id) {
        Song song = null;
        for (Song element : listSong) {
            if (element.getId() == id) song = element;
        }
        return song;
    }

    public String displaySong(Song obj) {
        return "Id: " + obj.getId() + " - Tên bài hát: " + obj.getName() +
                " - ca sĩ: " + obj.getSinger() + " - nhạc sĩ: " + obj.getComposer() + "\n";
    }
    public Song searchSongName(String name) {
        Song result = null;
        for (int i = 0; i < listSong.size(); i++) {
            if (listSong.get(i).getName().equals(name)) result = listSong.get(i);
        }
        return result;
    }
    public String displaySongInfo(Song obj) {
        return "Tên bài hát " + obj.getName() + " - Id: " + obj.getId() + " - Thể loại nhạc: " + obj.getTypeMusic() + " - Thời lượng: " + obj.getTime() +
                " - ca sĩ: " + obj.getSinger() + " - nhạc sĩ: " + obj.getComposer() + "\n";

    }
    public boolean checkSong(String word) {
        for (int i = 0; i < listSong.size(); i++) {
            if (listSong.get(i).getName().equals(word)) {
                return true;
            }
        }
        return false;
    }
    public void readFromFile() throws IOException {
        listSong.clear();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("file\\Song.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Song songs = handleLine(line.trim());
                listSong.add(songs);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null)
                bufferedReader.close();
            if (fileReader != null)
                fileReader.close();
        }
    }
    public Song handleLine(String line){
        String[] words = line.split("/");
        Song addSong = new Song(words[0],words[1],words[2],words[3],words[4]);
        return addSong;
    }
    public void writeToFile() throws IOException {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("file\\Song.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Song songs : listSong) {
                bufferedWriter.write(songs.toFile());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (fileWriter != null)
                fileWriter.close();
        }
    }
}


