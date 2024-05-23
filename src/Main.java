import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Operations operations = new Operations();
        operations.readFromFile();
        boolean continueProgram = true;
        while (continueProgram) {
            System.out.println("1. Okul dizisini dosyaya yaz");
            System.out.println("2. Okul dizisini dosyadan oku");
            System.out.println("3. Yeni okul ekle");
            System.out.println("4. Okul adına göre okul bilgilerini göster");
            System.out.println("5. Tüm okulların bilgilerini göster");
            System.out.println("6. Aynı şehirdeki okulları göster");
            System.out.println("7. Okul kaydını düzenle");
            System.out.println("8. Okul adını kullanarak okul kaydını sil");
            System.out.println("9. Programdan çık");
            System.out.println("100. TÜM VERİLERİ SİL!!!");

            System.out.print("Seçiminizi girin: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Geçersiz giriş! Lütfen bir sayı girin.");
                scanner.next();
                continue;
            }
            switch (choice) {
                case 1:
                    operations.writeToFile();
                    break;
                case 2:
                    operations.readFromFile();
                    break;
                case 3:
                    operations.addNewSchool();
                    break;
                case 4:
                    operations.showSchoolInfoByName();
                    break;
                case 5:
                    operations.showAllSchoolsInfo();
                    break;
                case 6:
                    operations.showSchoolsInSameCity();
                    break;
                case 7:
                    operations.modifySchoolRecord();
                    break;
                case 8:
                    operations.deleteSchoolRecord();
                    break;
                case 9:
                    continueProgram = false;
                    break;
                case 100:
                    scanner.nextLine();
                    boolean isDeleteConfirmed = true;
                    while (isDeleteConfirmed) {
                        System.out.println("TÜM VERİLERİ SİLMEK İSTEDİĞİNİZE EMİN MİSİNİZ? (E/H)");
                        String confirmation = scanner.nextLine().toUpperCase();
                        if (confirmation.equals("E")) {
                            operations.clearFile();
                            isDeleteConfirmed = false;
                        } else if (confirmation.equals("H")) {
                            System.out.println("SİLME İŞLEMİ İPTAL EDİLDİ!");
                            isDeleteConfirmed = false;
                        } else {
                            System.out.println("Geçersiz giriş! Lütfen 'E' veya 'H' girin.");
                        }
                    }
                    break;
                default:
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                    break;
            }
        }
        scanner.close();
    }
}
