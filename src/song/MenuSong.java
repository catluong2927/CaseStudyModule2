package song;

import album.Album;

import java.util.Scanner;

public class MenuSong {
    processSong songs = processSong.getInstance();
    Scanner sc = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println("***********************************");
        System.out.println("*     Please choose the feature   *");
        System.out.println("*     1. Add song                 *");
        System.out.println("*     2. Edit song information    *");
        System.out.println("*     3. Search songs by name     *");
        System.out.println("*     4. Delete song              *");
        System.out.println("*     5. Show playlist            *");
        System.out.println("*     0. Exit                     *");
        System.out.println("***********************************");
    }

    public void doMainMenuSong(Album obj) {
        int choice = sc.nextInt();
        while (choice != 0) {
            switch (choice) {
                case 1 -> {
                    songs.addSong(obj);
                    MenuSong.displayMenu();
                    choice = sc.nextInt();
                }
                case 2 -> {
                    songs.fixSong(obj);
                    MenuSong.displayMenu();
                    choice = sc.nextInt();
                }
                case 3 -> {
                    songs.searchSongOfObj(obj);
                    MenuSong.displayMenu();
                    choice = sc.nextInt();
                }
                case 4 -> {
                    songs.removeSong(obj);
                    MenuSong.displayMenu();
                    choice = sc.nextInt();
                }
                case 5 -> {
                    songs.displayListSong(obj);
                    MenuSong.displayMenu();
                    choice = sc.nextInt();
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
