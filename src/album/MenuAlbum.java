package album;

import person.*;

import java.util.Scanner;

public class MenuAlbum {
    processAlbum albums = processAlbum.getInstance();
    Scanner sc = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println("************************************");
        System.out.println("*     Please choose the feature    *");
        System.out.println("*     1. Add Album                 *");
        System.out.println("*     2. Edit Album information    *");
        System.out.println("*     3. Search Albums             *");
        System.out.println("*     4. Delete Album              *");
        System.out.println("*     5. Show Album list           *");
        System.out.println("*     6. Add songs to Album        *");
        System.out.println("*     7. Show all Album list       *");
        System.out.println("*     0. Exit                      *");
        System.out.println("************************************");
    }

    public void doMainMenuAlbum(People obj) {
        int choice = sc.nextInt();
        sc.nextLine();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    albums.addAlbum(obj);
                    MenuAlbum.displayMenu();
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                case 2:
                    albums.fixAlbumCommon(obj);
                    MenuAlbum.displayMenu();
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                case 3:
                    albums.searchAlbumOfObj(obj);
                    MenuAlbum.displayMenu();
                    choice = sc.nextInt();
                    break;
                case 4:
                    albums.removeAlbum(obj);
                    MenuAlbum.displayMenu();
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                case 5:
                    albums.displayAlbum(obj);
                    MenuAlbum.displayMenu();
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                case 6:
                    System.out.println("chọn Album muốn thêm bài hát");
                    String nameAlbum = sc.nextLine();
                    if (albums.checkAlbum(nameAlbum)) {
                        albums.addSongToAlbum(nameAlbum);
                    } else System.out.println("không tồn tại Album này");
                    MenuAlbum.displayMenu();
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                default:
                    System.out.println("vui lòng nhập đúng chức năng");
                    choice = sc.nextInt();
            }
        }
        System.out.println("cám ơn bạn đã sử dụng chương trình");
    }
}
