package person;
import song.*;

import java.util.Scanner;

public class MenuPeople {
    processPeople peoples = processPeople.getInstance();
    Scanner sc = new Scanner(System.in);

    public static void displayMenuPeople() {
        System.out.println("************************************************");
        System.out.println("*    Please choose the feature                 *");
        System.out.println("*    1. Add object                             *");
        System.out.println("*    2. Edit object information                *");
        System.out.println("*    3. Search object                          *");
        System.out.println("*    4. Delete object                          *");
        System.out.println("*    5. Show object list                       *");
        System.out.println("*    6. Add Album to the object's Albums list  *");
        System.out.println("*    7. Search songs by Singer                 *");
        System.out.println("*    8. Search songs by Composer               *");
        System.out.println("*    0. Exit                                   *");
        System.out.println("************************************************");
    }

    public void doMainMenuPeople() {
        int choice = sc.nextInt();
        sc.nextLine();
        while (choice != 0) {
            switch (choice) {
                case 1 -> {
                    peoples.addPeople();
                    MenuPeople.displayMenuPeople();
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                case 2 -> {
                    System.out.println("chọn đối tượng để sửa");
                    String choosePeople2 = sc.nextLine();
                    if (peoples.checkPeople(choosePeople2)) {
                        peoples.fixPeople(peoples.searchPeopleByName(choosePeople2));
                    } else System.out.println("không tồn tại đối tượng để sửa");
                    MenuPeople.displayMenuPeople();
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                case 3 -> {
                    System.out.println("chọn tên đối tượng muốn tìm kiếm: ");
                    String name = sc.nextLine();
                    while (name.equals("")) {
                        System.out.println("tên đối tượng không được để trống, vui lòng nhập lại");
                        name = sc.nextLine();
                    }
                    if (peoples.checkPeople(name)) {
                        System.out.println(peoples.displayPeopleInfo(peoples.searchPeopleByName(name)));
                    } else System.out.println("không tìm thấy đối tượng");
                    MenuPeople.displayMenuPeople();
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                case 4 -> {
                    peoples.removePeople();
                    MenuPeople.displayMenuPeople();
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                case 5 -> {
                    peoples.displayPeople();
                    MenuPeople.displayMenuPeople();
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                case 6 -> {
                    System.out.println("chọn đối tượng muốn thêm Album");
                    String namePeople = sc.nextLine();
                    if (peoples.checkPeople(namePeople)) {
                        peoples.addAlbumToList(namePeople);
                    } else System.out.println("không tồn tại đối tượng");
                    MenuPeople.displayMenuPeople();
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                case 7 -> {
                    System.out.println(processSong.getInstance().searchSongSinger());
                    MenuPeople.displayMenuPeople();
                    choice = sc.nextInt();
                    sc.nextLine();
                }
                case 8 -> {
                    System.out.println(processSong.getInstance().searchSongComposer());
                    MenuPeople.displayMenuPeople();
                    choice = sc.nextInt();
                    sc.nextLine();

                }
                default -> {
                    System.out.println("vui lòng nhập đúng chức năng");
                    choice = sc.nextInt();
                }
            }
        }
        System.out.println("cám ơn bạn đã sử dụng chương trình");
    }
}
