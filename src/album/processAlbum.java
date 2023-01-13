package album;

import person.*;

import person.People;
import song.MenuSong;
import song.Song;
import song.processSong;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class processAlbum {
    processSong listSong = processSong.getInstance();
    int count;
    private static processAlbum instance;

    private List<Album> listAlbum;

    private processAlbum() {
        listAlbum = new LinkedList<>();
    }

    public List<Album> getListAlbum() {
        return listAlbum;
    }

    public static processAlbum getInstance() {
        if (instance == null) {
            instance = new processAlbum();
        }
        return instance;
    }

    Scanner sc = new Scanner(System.in);

    public int countAlbum(String name) {
        int count = 0;
        for (Album element : listAlbum) {
            if (element.getName().equals(name)) count++;
        }
        return count;
    }

    public void addAlbum(People obj) {
        int size;
        if (listAlbum.size() == 0) size = 0;
        else size = listAlbum.size();
        System.out.println("nhập số lượng Album muốn tạo:");
        int numAlbum = sc.nextInt();
        sc.nextLine();
        for (int i = size, k = 1; (i - size) < numAlbum && k <= numAlbum; i++, k++) {
            System.out.println("nhập tên Album thứ: " + k + ":");
            String name = sc.nextLine();
            System.out.println("dòng nhạc cho bộ sưu tập:");
            String type = sc.nextLine();
            listAlbum.add(new Album(name, type));
            obj.setListAlbum(i);
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//

    public void fixAlbumCommon(People obj) {
        if (processPeople.getInstance().showListAlbum(obj) != null) {
            System.out.println(processPeople.getInstance().showListAlbum(obj));
            System.out.println("chọn id Album muốn sửa");
            int chooseId = sc.nextInt();
            sc.nextLine();
            for (Album element : listAlbum) {
                if (element.getId() == chooseId) {
                    System.out.println("chọn thông tin bạn muốn sửa (tên - dòng nhạc - bài hát)");
                    String fixInfor = sc.nextLine().toLowerCase();
                    switch (fixInfor) {
                        case "tên":
                            System.out.println("tên mới là gì?");
                            String name = sc.nextLine();
                            element.setName(name);
                            break;
                        case "dòng nhạc":
                            System.out.println("bộ sưu tập về chủ đề gì?");
                            String type = sc.nextLine();
                            element.setTypeAlbum(type);
                            break;
                        case "bài hát":
                            MenuSong.displayMenu();
                            new MenuSong().doMainMenuSong(element);
                            break;
                    }
                }
            }
        } else System.out.println(("đối tượng chưa có Album nào"));
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//

    public void searchAlbumOfObj(People obj) {
        System.out.println("nhập tên Album muốn tìm kiếm");
        String chooseAlbumOfObj = sc.nextLine();
        int checkId = -1;
        if (checkAlbum(chooseAlbumOfObj)) {
            checkId = searchAlbumByName(chooseAlbumOfObj).getId();
            for (int element : obj.getListAlbum()) {
                if (element == checkId) System.out.println(displayAlbumInfo(searchAlbumById(checkId)));
            }
        }
        if (checkId == -1) System.out.println("không tồn tại Album trong đối tượng này");
    }//

    public void removeAlbum(People obj) {
        if (processPeople.getInstance().showListAlbum(obj) != null) {
            System.out.println(processPeople.getInstance().showListAlbum(obj));
            System.out.println("chọn id Album muốn xóa");
            int chooseId = sc.nextInt();
            sc.nextLine();
            for (int element : obj.getListAlbum()) {
                if (element == chooseId) obj.getListAlbum().remove(element);
            }
        } else System.out.println("đối tượng đang không có Album nào");
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//

    public void displayAlbum(People obj) {
        if (!obj.getListAlbum().isEmpty()) {
            for (int element : obj.getListAlbum()) {
                System.out.println(displayAlbumInfo(searchAlbumById(element)));
            }
        } else System.out.println("danh sách đang trống");
    }//

    public boolean checkAlbum(String obj) {
        if (!listAlbum.isEmpty()) {
            for (Album element : listAlbum) {
                if (element.getName().equals(obj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addSongToAlbum(String nameAlbum) {
        Album chooseAlbum = searchAlbumByName(nameAlbum);
        int count = countAlbum(chooseAlbum.getName());
        if (count == 1) {
            System.out.println("chọn bài hát muốn thêm");
            String nameSong = sc.nextLine();
            if (listSong.checkSong(nameSong)) {
                Song chooseSong = listSong.searchSongName(nameSong);
                chooseAlbum.setSongs(chooseSong.getId());
                System.out.println("đã thêm thành công");
            } else System.out.println("thêm thất bại, vì bài hát không tồn tại");
        } else {
            if (count > 1) {
                System.out.println("Album bị trùng tên");
                for (Album element : listAlbum) {
                    if (checkAlbum(element.getName())) displayAlbumInfo(element);
                }
                System.out.println("nhập Id Album muốn thêm");
                int chooseId = sc.nextInt();
                sc.nextLine();
                Album objId = searchAlbumById(chooseId);
                System.out.println("chọn bài hát muốn thêm");
                String nameSong = sc.nextLine();
                if (listSong.checkSong(nameSong)) {
                    Song chooseSong = processSong.getInstance().searchSongName(nameSong);
                    chooseAlbum.setSongs(chooseSong.getId());
                    System.out.println("đã thêm thành công");
                } else System.out.println("thêm thất bại, vì bài hát không tồn tại");
            } else System.out.println("không tìm thấy Album này để thêm");
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String displayAlbumInfo(Album obj) {
        return "Tên Album: " +obj.getName()  + " - Id: " + obj.getId() + " - Thể loại: " + obj.getTypeAlbum() + "\n"
                + "danh sách bài hát" + "\n" + showListSong(obj);

    }

    public String showListSong(Album obj) {
        StringBuilder text = new StringBuilder();
        Song song;
        for (int element : obj.getSongs()) {
            song = processSong.getInstance().searchAlbumId(element);
            text.append(processSong.getInstance().displaySongInfo(song));
        }
        return text.toString();
    }

    public Album searchAlbumByName(String obj) {
        for (Album element : listAlbum) {
            if (element.getName().equals(obj)) return element;
        }
        return null;
    }

    public Album searchAlbumById(int id) {
        Album result = null;
        for (int i = 0; i < listAlbum.size(); i++) {
            if (listAlbum.get(i).getId() == id) result = listAlbum.get(i);
        }
        return result;
    }

    //    public void removeSong(Album obj) {
//        System.out.println(showListSong(obj));
//        System.out.println("chọn số lượng bài hát muốn xóa");
//        int num = sc.nextInt();
//        sc.nextLine();
//        if (showListSong(obj) != null) {
//            for (int i = 0; i < num; i++) {
//                System.out.println("chọn id thứ " + (i + 1) + " muốn xóa");
//                int chooseId = sc.nextInt();
//                sc.nextLine();
//                if (obj.getSongs().get(i) == chooseId) obj.getSongs().remove(i);
//            }
//        } else System.out.println("album này đang rỗng");
//    }
    public void readFromFile() throws IOException {
        listAlbum.clear();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("file\\Album.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Album album = handleLine(line.trim());
                listAlbum.add(album);
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

    public Album handleLine(String line) {
        List<Integer> listIdObj = new ArrayList<>();
        String[] words = line.split("/");
        for (String element: words[2].split("")){
            if(processPeople.getInstance().checkInt(element)) listIdObj.add(Integer.parseInt(element));
        }
        Album addAlbum = new Album(words[0], words[1]);
        for(int element: listIdObj){
            addAlbum.setSongs(element);
        }
        return addAlbum;
    }

    public void writeToFile() throws IOException {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("file\\Album.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Album album : listAlbum) {
                bufferedWriter.write(album.toFile());
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
