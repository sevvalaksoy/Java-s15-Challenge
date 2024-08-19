import com.aksoy.library.Book;
import com.aksoy.library.Condition;
import com.aksoy.library.Genre;
import com.aksoy.library.Library;
import com.aksoy.person.author.Author;
import com.aksoy.person.employee.Employee;
import com.aksoy.person.employee.Key;
import com.aksoy.person.employee.Manager;
import com.aksoy.person.member.Address;
import com.aksoy.person.member.Member;
import com.aksoy.person.member.UserType;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> rowlingBooks = new HashSet<>();
        rowlingBooks.add("Harry Potter and the Philosopher’s Stone");
        rowlingBooks.add("Harry Potter and the Chamber of Secrets");
        Author rowling = new Author("J.K.Rowling", rowlingBooks);
        Book n1 = new Book(1000001, rowling, "Harry Potter and the Philosopher’s Stone", 1,
                true, 1.25, new Date(), 214, 15.75, Condition.FINE, Genre.FANTASY, null);
        Book n2 = new Book(1000002, rowling, "Harry Potter and the Chamber of Secrets", 1,
                true, 1.25, new Date(), 214, 15.75, Condition.FINE, Genre.FANTASY, null);
        Library.getInstance().addBook(n1);
        Library.getInstance().addBook(n2);

        // Librarian librarian = new Librarian("Şevval", Key.LIBRARY, 8, 544-5415498, "Aj14DfH");
        Set<Employee> employees = new HashSet<>();
        employees.add(Manager.getInstance());
        System.out.println(Manager.getInstance());
        Library.getInstance().setEmployees(employees);
        Scanner input = new Scanner(System.in);
        String key;
        int count = 0;
        while (true) {
            if(count==3){
                System.out.println("Çok fazla yanlış şifre denediniz sistem kapanıyor.");
                break;
            }
            System.out.println("Lütfen şifrenizi giriniz.");
            key = input.nextLine();
            if (key.equals(Manager.getInstance().getPassword())) {
                System.out.println("Sisteme hoş geldin " + Manager.getInstance().getName() + "!");
                while (true){
                    String operation;
                    System.out.println("Lütfen yapmak istediğiniz işlemi yazınız: check budget, buy book, hire librarian, hire janitor, show employees, pay salary, switch to librarian account");
                    operation = input.nextLine().toLowerCase();
                    switch (operation) {
                        case "check budget":
                            System.out.println("Toplam bütçe ekrana yansıtılıyor...");
                            System.out.println("Bütçeniz: " + Manager.getInstance().checkBudget());
                            break;
                        case "buy book":
                            System.out.println("Almak istediğiniz kitabın adını giriniz.");
                            String name = input.nextLine().toLowerCase();
                            System.out.println("Kitabın yazarını giriniz.");
                            String author = input.nextLine().toUpperCase();
                            System.out.println("Kitabın fiyatını giriniz.");
                            int price = input.nextInt();
                            System.out.println("Kitabın baskısını giriniz.");
                            int edition = input.nextInt();
                            System.out.println("Kitabın kiralanma ücretini giriniz.");
                            double rent = input.nextDouble();
                            Date date = new Date();
                            System.out.println("Lütfen kitabın sayfa sayısını giriniz.2");
                            int pages = input.nextInt();
                            Manager.getInstance().buyBook(new Book(0,new Author(author, null), name, edition, true, rent, date, pages,
                                    price, Condition.AS_NEW, null, null));
                            break;
                        case "hire librarian":
                            System.out.println("Çalışacak kişinin ismini giriniz.");
                            String librarianName = input.nextLine();
                            System.out.println("Çalışacak kişinin telefonunu giriniz.");
                            String phone = input.nextLine();
                            System.out.println("Çalışacak kişi için bir şifre giriniz.");
                            String password = input.nextLine();
                            Manager.getInstance().hireLibrarian(librarianName,Key.LIBRARY,8,phone,password);
                            break;
                        case "hire janitor":
                            System.out.println("Çalışacak kişinin ismini giriniz.");
                            String janitorName = input.nextLine();
                            System.out.println("Çalışacak kişinin telefonunu giriniz.");
                            String janitorPhone = input.nextLine();
                            Manager.getInstance().hireJanitor(janitorName,Key.STOREROOM,8, janitorPhone);
                            break;
                        case "show employees":
                            System.out.println(Library.getInstance().getEmployees());
                            break;
                        case "pay salary":
                            if(Library.getInstance().getEmployees().size() == 1){
                                System.out.println("Çalıştırmak için önce eleman işe alınız.");
                            } else {
                                System.out.println("Saatlik ödenecek ücreti giriniz.");
                                double perHourSalary = input.nextDouble();
                                Manager.getInstance().paySalary(perHourSalary);
                            }
                            break;
                        case "switch to librarian account":
                            System.out.println("Lütfen şifrenizi giriniz.");
                            key = input.nextLine();
                            String librarianKey = Library.getInstance().getLibrarian()==null?"":Library.getInstance().getLibrarian().getPassword();
                            if(key.equals(librarianKey)) {
                                System.out.println("Sisteme hoş geldin " + Library.getInstance().getLibrarian().getName() + "!");
                                while(true){
                                    System.out.println("Lütfen yapmak istediğiniz işlemi yazınız.");
                                    operation = input.nextLine().toLowerCase();
                                    switch (operation) {
                                        case "search book":
                                            System.out.println("Kitabı nasıl aratmak istediğinizi yazınız: yazar, id veya isim ");
                                            String search = input.nextLine();
                                            switch (search){
                                                case "yazar":
                                                    System.out.println("Yazarın ismini giriniz: ");
                                                    String authorName = input.nextLine();
                                                    Author searchedAuthor = Library.getInstance().findAuthor(authorName);
                                                    Library.getInstance().getLibrarian().searchBook(searchedAuthor);
                                                    break;
                                                case "id":
                                                    System.out.println("Kitabın idsini giriniz");
                                                    Long bookID = input.nextLong();
                                                    Library.getInstance().getLibrarian().searchBook(bookID);
                                                    break;
                                                case "isim":
                                                    System.out.println("Kitabın adını giriniz.");
                                                    String bookName = input.nextLine();
                                                    Library.getInstance().getLibrarian().searchBook(bookName);
                                                    break;
                                            }
                                            break;
                                        case "show category":
                                            System.out.println("Görüntülemek istediğiniz kategoriyi giriniz: ");
                                            String genre = input.nextLine().toUpperCase();
                                            Library.getInstance().bringCategory(genre);
                                            break;
                                        case "check member":
                                            System.out.println("Üyenin şifresini giriniz.");
                                            String memberPassword = input.nextLine();
                                            Library.getInstance().getLibrarian().verifyMember(memberPassword);
                                            break;
                                        case "create member":
                                            System.out.println("Üyenin adını giriniz.");
                                            String memberName = input.nextLine();
                                            System.out.println("Üyelik için id giriniz.");
                                            long memberId = input.nextLong();
                                            input.nextLine();
                                            System.out.println("Üyenin adress bilgilerini giriniz: Şehir:");
                                            String city = input.nextLine();
                                            System.out.println("Sokak:");
                                            String street = input.nextLine();
                                            System.out.println("Apartman:");
                                            String apartman = input.nextLine();
                                            System.out.println("Daire numarası:");
                                            int flatNo = input.nextInt();
                                            System.out.println("Zip Kod:");
                                            int zipCode = input.nextInt();
                                            input.nextLine();
                                            Address memberAdress = new Address(city, street, zipCode, apartman, flatNo);
                                            System.out.println("Telefon numarası giriniz:");
                                            String phoneNumber = input.nextLine();
                                            System.out.println("Üye için şifre belirleyiniz:");
                                            String passwordMember = input.nextLine();
                                            Member member = new Member(memberName, memberId, memberAdress, 0, new Date(), phoneNumber,passwordMember, UserType.STANDARD, null
                                            );
                                            Library.getInstance().getLibrarian().createMember(member);
                                            break;
                                        case "create bill":
                                            System.out.println("Hangi kitabı kiralamak istediğinizi giriniz.");
                                            String bookName = input.nextLine();
                                            Library.getInstance().getLibrarian().searchBook(bookName);
                                            Library.getInstance().getLibrarian().createBill(Library.getInstance().bringBook(bookName));
                                            break;
                                        case "calculate fine":
                                            System.out.println("Cıkarılmasını istediğiniz elemanları giriniz.");
                                            break;
                                        case "remove book":
                                            System.out.println("Cıkarılmasını istediğiniz elemanları giriniz.");
                                            break;
                                        case "issue book":
                                            System.out.println("Cıkarılmasını istediğiniz elemanları giriniz.");
                                            break;
                                        case "take book back":
                                            System.out.println("Cıkarılmasını istediğiniz elemanları giriniz.");
                                            break;
                                        default:
                                            System.out.println("Geçersiz giriş! Verilen işlemlerden birini seçiniz: ");
                                    }
                                }
                            } else break;
                        default:
                            System.out.println("Verilen işlemlerden birini seçiniz.");
                            break;
                    }
                }
            } else {
                System.out.println("Yanlış şifre girdiniz. Lütfen tekrar deneyin.");
                count++;
            }
        }
    }
}