import java.io.*;
import java.util.Scanner;

public class Operations {
    static final int MAX_SCHOOL_NUMBER = 100;
    static final School[] schools = new School[MAX_SCHOOL_NUMBER];
    static int schoolCount = 0;
    static final String filePath = "src/schools.txt";

    public void clearFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath,false))) {
            System.out.println("Dosya başarıyla temizlendi!");
        } catch (Exception e) {
            System.out.println("Dosya temizlenirken bir hata oluştu: " + e.getMessage());
        }
    }
    public void writeToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath,false))) {
            for (int i = 0 ; i<schoolCount ; i++){
                School school = schools[i];
                writer.println("Okul Adı: " + school.getSchoolName() + ", Şehir: "
                        + school.getCity() + ", Ülke: " + school.getCountry() + ", Telefon Numarası: " + school.getPhone() +
                        ", Öğrenci Sayısı: " + school.getStudentCount() + ", Kuruluş Yılı: " + school.getEstablishmentYear());
            }
        } catch (IOException e) {
            System.out.println("Dosyaya yazma hatası.");
            e.printStackTrace();
        }
        System.out.println("Okul bilgileri dosyaya yazıldı.");
    }

    public void readFromFile() {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            schoolCount = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(", ");
                School school = new School();
                school.setSchoolName(parts[0].split(": ")[1]);
                school.setCity(parts[1].split(": ")[1]);
                school.setCountry(parts[2].split(": ")[1]);
                school.setPhone(parts[3].split(": ")[1]);
                school.setStudentCount(Integer.parseInt(parts[4].split(": ")[1]));
                school.setEstablishmentYear(Integer.parseInt(parts[5].split(": ")[1]));
                schools[schoolCount++] = school;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı.");
        }
    }
    public void addNewSchool() {
        if (schoolCount >= MAX_SCHOOL_NUMBER) {
            System.out.println("Okul limiti doldu. Yeni okul eklenemez.");
        } else {
            School school = new School();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Okul Adı Girin: ");
            school.setSchoolName(scanner.nextLine());
            System.out.print("Şehir Girin: ");
            school.setCity(scanner.nextLine());
            System.out.print("Ülke Girin: ");
            school.setCountry(scanner.nextLine());
            System.out.print("Telefon Numarası Girin: ");
            school.setPhone(scanner.nextLine());

            int studentCount = 0;
            boolean validStudentCount = false;
            while (!validStudentCount) {
                try {
                    System.out.print("Öğrenci Sayısı Girin: ");
                    studentCount = Integer.parseInt(scanner.nextLine());
                    validStudentCount = true;
                } catch (NumberFormatException e) {
                    System.out.println("Lütfen geçerli bir sayı giriniz.");
                }
            }
            int establishmentYear = 0;
            boolean validEstablishmentYear = false;
            while (!validEstablishmentYear) {
                try {
                    System.out.print("Kuruluş Yılı Girin: ");
                    establishmentYear = Integer.parseInt(scanner.nextLine());
                    validEstablishmentYear = true;
                } catch (NumberFormatException e) {
                    System.out.println("Lütfen geçerli bir yıl giriniz.");
                }
            }
            school.setStudentCount(studentCount);
            school.setEstablishmentYear(establishmentYear);
            schools[schoolCount++] = school;
            System.out.println("Yeni okul eklendi.");
            writeToFile();
        }
    }
    public void showSchoolInfoByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Okul adını girin: ");
        String searchedSchoolName = scanner.nextLine();
        for (int i = 0; i < schoolCount; i++) {
            if (schools[i].getSchoolName().equalsIgnoreCase(searchedSchoolName)) {
                System.out.println("Okul Adı: " + schools[i].getSchoolName() + ", Şehir: " +
                        schools[i].getCity() + ", Ülke: " + schools[i].getCountry() + ", Telefon Numarası: " +
                        schools[i].getPhone() + ", Öğrenci Sayısı: " + schools[i].getStudentCount() + ", Kuruluş Yılı: " +
                        schools[i].getEstablishmentYear());
                return;
            }
        }
        System.out.println("Okul bulunamadı!");
    }
    public void showAllSchoolsInfo() {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı.");
        }
    }
    public void showSchoolsInSameCity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Şehir Girin: ");
        String searchedCity = scanner.nextLine();
        for (int i = 0; i < schoolCount; i++) {
            School school = schools[i];
            if (school.getCity().equalsIgnoreCase(searchedCity)) {
                System.out.println("Okul Adı: " + school.getSchoolName() +
                        ", Şehir: " + school.getCity() + ", Ülke: " + school.getCountry() +
                        ", Telefon Numarası: " + school.getPhone() + ", Öğrenci Sayısı: " + school.getStudentCount() +
                        ", Kuruluş Yılı: " + school.getEstablishmentYear());
            }
        }
    }
    public void modifySchoolRecord() {
        showAllSchoolsInfo();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Okul Adı Girin: ");
        String searchedSchoolName = scanner.nextLine();
        for (int i = 0; i < schoolCount; i++) {
            School school = schools[i];
            if (school.getSchoolName().equalsIgnoreCase(searchedSchoolName)) {
                System.out.println("Değiştirilecek alanı seçin:");
                System.out.println("1. Okul Adı");
                System.out.println("2. Şehir");
                System.out.println("3. Ülke");
                System.out.println("4. Telefon Numarası");
                System.out.println("5. Öğrenci Sayısı");
                System.out.println("6. Kuruluş Yılı");

                System.out.print("Seçiminizi girin: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Yeni Okul Adı Girin: ");
                        school.setSchoolName(scanner.next());
                        break;
                    case 2:
                        System.out.print("Yeni Şehir Girin: ");
                        school.setCity(scanner.next());
                        break;
                    case 3:
                        System.out.print("Yeni Ülke Girin: ");
                        school.setCountry(scanner.next());
                        break;
                    case 4:
                        System.out.print("Yeni Telefon Numarası Girin: ");
                        school.setPhone(scanner.next());
                        break;
                    case 5:
                        System.out.print("Yeni Öğrenci Sayısı Girin: ");
                        school.setStudentCount(scanner.nextInt());
                        break;
                    case 6:
                        System.out.print("Yeni Kuruluş Yılı Girin: ");
                        school.setEstablishmentYear(scanner.nextInt());
                        break;
                    default:
                        System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                        break;
                }
                System.out.println("Okul kaydı güncellendi.");
                writeToFile();
                return;
            }
        }
        System.out.println("Okul bulunamadı.");
    }
    public void deleteSchoolRecord() {
        showAllSchoolsInfo();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Silinecek Okul Adı Girin: ");
        String schoolNameToDelete = scanner.nextLine();
        for (int i = 0; i < schoolCount; i++) {
            School school = schools[i];
            if (school.getSchoolName().equalsIgnoreCase(schoolNameToDelete)) {
                for (int j = i; j < schoolCount - 1; j++) {
                    schools[j] = schools[j + 1];
                }
                schoolCount--;
                System.out.println("Okul kaydı silindi.");
                return;
            }
        }
        System.out.println("Okul bulunamadı!");
    }
}
