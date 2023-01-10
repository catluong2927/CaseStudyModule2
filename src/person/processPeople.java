package person;

import album.*;
import song.Song;
import song.processSong;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class processPeople {
    processAlbum listAlbum = processAlbum.getInstance();
    int count;
    private static processPeople instance;

    private List<People> listPeople;

    private processPeople() {
        listPeople = new LinkedList<>();
    }

    public List<People> getListPeople() {
        return listPeople;
    }

    public static processPeople getInstance() {
        if (instance == null) {
            instance = new processPeople();
        }
        return instance;
    }

    Scanner sc = new Scanner(System.in);

    public void addPeople() {
        int size;
        if (listPeople.size() == 0) size = 0;
        else size = listPeople.size();
        System.out.println("nhập số lượng đối tượng muốn tạo:");
        int numAlbum = sc.nextInt();
        sc.nextLine();
        for (int i = size, k = 1; (i-size) < numAlbum && k <= numAlbum; i++, k++) {
            System.out.println("nhập tên đối tượng thứ: " + k + ":");
            String name = sc.nextLine().trim();
            System.out.println("nhập tuổi:");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.println("nhập giới tính:");
            String gender = sc.nextLine().trim();
            System.out.println("nhập vai trò");
            String role = sc.nextLine().trim();
            listPeople.add(new People(name, age, gender, role));
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//
    public void fixPeople(People obj) {
        int count = countPeople(obj.getName());
        if (count == 1) {
            System.out.println("chọn thông tin bạn muốn sửa (tên - tuổi - giới tính - vai trò - album)");
            String fixInfor = sc.nextLine().toLowerCase();
            switch (fixInfor) {
                case "tên":
                    System.out.println("tên mới là gì?");
                    String name = sc.nextLine();
                    obj.setName(name);
                    break;
                case "tuổi":
                    System.out.println("bao nhiêu tuổi");
                    int age = sc.nextInt();
                    sc.nextLine();
                    obj.setAge(age);
                    break;
                case "giới tính":
                    System.out.println("giới tính là gì?");
                    String gender = sc.nextLine();
                    obj.setGender(gender);
                    break;
                case "vai trò":
                    System.out.println("vai trò là gì?");
                    String role = sc.nextLine();
                    obj.setRole(role);
                    break;
                case "album":
                    MenuAlbum.displayMenu();
                    new MenuAlbum().doMainMenuAlbum(obj);
                    break;
            }
        } else {
            if (count > 1) {
                System.out.println("đối tượng này bị trùng tên:");
                displayPeopleInfo(obj);
                System.out.println("chọn Id Album muốn cập nhât:");
                int chooseId = sc.nextInt();
                sc.nextLine();
                People choosePeople = searchPeopleById(chooseId);
                System.out.println("chọn thông tin bạn muốn sửa (tên - dòng nhạc - album)");
                String fixInfor = sc.nextLine().toLowerCase();
                switch (fixInfor) {
                    case "tên":
                        System.out.println("tên mới là gì?");
                        String name = sc.nextLine();
                        choosePeople.setName(name);
                        break;
                    case "tuổi":
                        System.out.println("tuổi đối tượng");
                        int age = sc.nextInt();
                        sc.nextLine();
                        choosePeople.setAge(age);
                        break;
                    case "giới tính":
                        System.out.println("giới tính là gì?");
                        String gender = sc.nextLine();
                        choosePeople.setGender(gender);
                        break;
                    case "vai trò":
                        System.out.println("vai trò là gì?");
                        String role = sc.nextLine();
                        choosePeople.setRole(role);
                        break;
                    case "album":
                        MenuAlbum.displayMenu();
                        new MenuAlbum().doMainMenuAlbum(obj);
                        break;
                }
            } else System.out.println("không tìm thấy đối tượng để sửa");
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//
    public boolean checkPeople(String obj) {
        boolean checksinger = false;
        if (!listPeople.isEmpty()) {
            for (int i = 0; i < listPeople.size(); i++) {
                if (listPeople.get(i).getName().equals(obj)) {
                    checksinger = true;
                }
            }
        }
        return checksinger;
    }//
    public void removePeople() {
        System.out.println("chọn đối tượng muốn xóa");
        String choosePeople = sc.nextLine();
        int count = countPeople(choosePeople);
        if (count == 1) {
            for (People element : listPeople) {
                if (element.getName().equals(choosePeople)) {
                    listPeople.remove(element);
                    System.out.println("bạn đã xóa thành công");
                }
            }
        } else {
            if (count > 1) {
                System.out.println("bài hát này bị trùng tên:");
                System.out.println(displayPeopleInfo(searchPeopleByName(choosePeople)));
                System.out.println("chọn Id muốn xóa:");
                int chooseId = sc.nextInt();
                sc.nextLine();
                for (People element : listPeople) {
                    if (element.getId() == chooseId) {
                        listPeople.remove(element);
                        System.out.println("bạn đã xóa thành công");
                    }
                }
            } else System.out.println("không tìm thấy đối tượng này để xóa");
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void displayPeople() {
        if (!listPeople.isEmpty()) {
            for (People element : listPeople) {
                System.out.println(displayPeopleInfo(element));
            }
        } else System.out.println("danh sách đang trống");
    }
    public String displayPeopleInfo(People obj) {
        return "Id: " + obj.getId() + " - Tên: " + obj.getName() + " - vai trò: " + obj.getRole() + "\n"
                + "danh sách Album" + "\n" + showListAlbum(obj);

    }
    public String showListAlbum(People obj) {
        String text = null;
        Album albums;
        for (int element : obj.getListAlbum()) {
            albums = processAlbum.getInstance().searchAlbumById(element);
            text = processAlbum.getInstance().displayAlbumInfo(albums);
        }
        return text;
    }
    public void removeAlbum(People obj) {
        System.out.println(showListAlbum(obj));
        System.out.println("chọn số lượng Album muốn xóa");
        int num = sc.nextInt();
        sc.nextLine();
        if (showListAlbum(obj) != null) {
            for (int i = 0; i < num; i++) {
                System.out.println("chọn id thứ " + (i + 1) + " muốn xóa");
                int chooseId = sc.nextInt();
                sc.nextLine();
                if (obj.getListAlbum().get(i) == chooseId) obj.getListAlbum().remove(i);
            }
        } else System.out.println("đối tượng đang không có Album nào");
    }
    public boolean checkSinger(String obj) {
        boolean checksinger = false;
        if (!listPeople.isEmpty()) {
            for (int i = 0; i < listPeople.size(); i++) {
                if (listPeople.get(i).getName().equals(obj)) {
                    checksinger = true;
                }
            }
        }
        return checksinger;
    }

    public boolean checkComposer(String obj) {
        boolean checkcomposer = false;
        if (!listPeople.isEmpty()) {
            for (int i = 0; i < listPeople.size(); i++) {
                if (listPeople.get(i).getName().equals(obj)) {
                    checkcomposer = true;
                }
            }
        }
        return checkcomposer;
    }

    public int countPeople(String name) {
        int count = 0;
        for (People element : listPeople) {
            if (element.getName().equals(name)) count++;
        }
        return count;
    }

    public People searchPeopleByName(String name) {
        for (People element : listPeople) {
            if (element.getName().equals(name)) return element;
        }
        return null;
    }

    public People searchPeopleById(int id) {
        People result = null;
        for (int i = 0; i < listPeople.size(); i++) {
            if (listPeople.get(i).getId() == id) result = listPeople.get(i);
        }
        return result;
    }
    public void addAlbumToList(String name) {
        People choosePeople = searchPeopleByName(name);
        int count = countPeople(choosePeople.getName());
        if (count == 1) {
            System.out.println("chọn Album muốn thêm");
            String nameAlbum = sc.nextLine();
            if (listAlbum.checkAlbum(nameAlbum)) {
                Album chooseAlbum = listAlbum.searchAlbumByName(nameAlbum);
                choosePeople.setListAlbum(chooseAlbum.getId());
                System.out.println("bạn đã thêm thành công");
            } else System.out.println("Album không tồn tại");
        } else {
            if (count > 1) {
                System.out.println("đối tượng bị trùng tên");
                for (People element : listPeople) {
                    if (checkPeople(element.getName())) displayPeopleInfo(element);
                }
                System.out.println("nhập Id đối tượng muốn thêm");
                int chooseId = sc.nextInt();
                sc.nextLine();
                People objId = searchPeopleById(chooseId);
                System.out.println("chọn Album muốn thêm");
                String nameAlbum = sc.nextLine();
                if (listAlbum.checkAlbum(nameAlbum)) {
                    Song chooseSong = processSong.getInstance().searchSongName(nameAlbum);
                    objId.setListAlbum(chooseSong.getId());
                    System.out.println("đã thêm thành công");
                } else System.out.println("Album không tồn tại");
            } else System.out.println("không tìm thấy đối tượng để thêm");
        }
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromFile() throws IOException {
        listPeople.clear();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("file\\person.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                People person = handleLine(line.trim());
                listPeople.add(person);
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
    public boolean checkInt(String a){
        String patterm = "0123456789";
        int index = patterm.indexOf(a);
        if(index!=-1) return true;
        else return false;
    }
    public People handleLine(String line){
        List<Integer> listIdObj = new ArrayList<>();
        String[] words = line.split("/");
        for (String element: words[4].split("")){
            if(checkInt(element)) listIdObj.add(Integer.parseInt(element));
        }
        People addLine = new People(words[0],Integer.parseInt(words[1]),words[2],words[3]);
        for(int element: listIdObj){
            addLine.setListAlbum(element);
        }
        return addLine;
    }
    public void writeToFile() throws IOException {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("file\\person.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            for (People person : listPeople) {
                bufferedWriter.write(person.toFile());
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
