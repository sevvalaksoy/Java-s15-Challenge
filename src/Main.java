import com.aksoy.library.Book;
import com.aksoy.library.Condition;
import com.aksoy.library.Genre;
import com.aksoy.library.Library;
import com.aksoy.person.author.Author;
import com.aksoy.person.employee.Employee;
import com.aksoy.person.employee.Key;
import com.aksoy.person.employee.Librarian;
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
        Book n1 = new Book(11, rowling, "Harry Potter and the Philosopher’s Stone", 1,
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
                    System.out.println("Lütfen yapmak istediğiniz işlemi yazınız: check budget, buy book, hire librarian, hire janitor, show employees, pay salary, switch to librarian account, exit");
                    operation = input.nextLine().toLowerCase().trim();
                    switch (operation) {
                        case "exit":
                            System.out.println("Çıkış yapılıyor.");
                            return;
                        case "check budget":
                            System.out.println("Toplam bütçe ekrana yansıtılıyor...");
                            System.out.println("Bütçeniz: " + Manager.getInstance().checkBudget());
                            break;
                        case "buy book":
                            System.out.println("Almak istediğiniz kitabın adını giriniz.");
                            String name = input.nextLine().toLowerCase();
                            System.out.println("Kitabın yazarını giriniz.");
                            String author = input.nextLine().toUpperCase();
                            System.out.println("Kitap için id giriniz:");
                            Long id = input.nextLong();
                            input.nextLine();
                            System.out.println("Kitabın fiyatını giriniz.");
                            int price = input.nextInt();
                            System.out.println("Kitabın baskısını giriniz.");
                            int edition = input.nextInt();
                            System.out.println("Kitabın kiralanma ücretini giriniz.");
                            double rent = input.nextDouble();
                            Date date = new Date();
                            System.out.println("Lütfen kitabın sayfa sayısını giriniz.");
                            int pages = input.nextInt();
                            input.nextLine();
                            System.out.println("Kitabın türünü giriniz.");
                            String genreBook = input.nextLine().toUpperCase();
                            Manager.getInstance().buyBook(new Book(id,new Author(author, null), name, edition, true, rent, date, pages,
                                    price, Condition.AS_NEW, Library.getInstance().bringGenre(genreBook), new Member()));
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
                            if(!Library.getInstance().getEmployees().stream().anyMatch(employee -> employee instanceof Librarian)){
                                System.out.println("Sisteme kayıtlı bir kütüphaneci bulunamamıştır, işe alım yapınız.");
                                break;
                            }
                            System.out.println("Lütfen şifrenizi giriniz.");
                            key = input.nextLine();
                            String librarianKey = Library.getInstance().getLibrarian()==null?"":Library.getInstance().getLibrarian().getPassword();
                            if(key.equals(librarianKey)) {
                                System.out.println("Sisteme hoş geldin " + Library.getInstance().getLibrarian().getName() + "!");
                                while(true){
                                    System.out.println("Lütfen yapmak istediğiniz işlemi yazınız: search book, show category, verify member, show author books, update book, create member, remove book, issue book, take book back, exit");
                                    operation = input.nextLine().toLowerCase().trim();
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
                                                default:
                                                    System.out.println("Verilen seçeneklerden birini seçiniz");
                                                    break;
                                            }
                                            break;
                                        case "show category":
                                            System.out.println("Görüntülemek istediğiniz kategoriyi giriniz: ");
                                            String genree = input.nextLine().toUpperCase();
                                            Library.getInstance().bringCategory(genree);
                                            break;
                                        case "update book":
                                            System.out.println("Güncellenecek kitabın idsini giriniz");
                                            Long idBook = input.nextLong();
                                            System.out.println("Kitabın temizlik durumunu giriniz: ");
                                            input.nextLine();
                                            String clean = input.nextLine();
                                            Condition stat = Library.getInstance().bringCondition(clean);
                                            System.out.println("Kitabın yeni kiralama fiyatını giriniz:");
                                            Double priceBook = input.nextDouble();
                                            input.nextLine();
                                            Library.getInstance().getLibrarian().updateBook(idBook, stat, priceBook);
                                        case "show author books":
                                            System.out.println("Kitaplarını görüntülemek istediğiniz yazarın adını giriniz:");
                                            String authorName = input.nextLine();
                                            Library.getInstance().bringAuthorBooks(authorName);
                                            break;
                                        case "verify member":
                                            if(Library.getInstance().getMembers().isEmpty()){
                                                System.out.println("Sisteme kayıtlı hiç üye yoktur.");
                                                break;
                                            }
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
                                            System.out.println("Üye tipini belirleyiniz: VIP, Student, Standart");
                                            String usertype = input.nextLine();
                                            UserType type = Library.getInstance().getLibrarian().createUserType(usertype);
                                            System.out.println("Üye için şifre belirleyiniz:");
                                            String passwordMember = input.nextLine();
                                            Member member = new Member(memberName, memberId, memberAdress, 0, new Date(), phoneNumber,passwordMember, type, new ArrayList<>()
                                            );
                                            Library.getInstance().getLibrarian().createMember(member);
                                            break;
                                        case "remove book":
                                            System.out.println("Sistemden çıkarılmasını istediğiniz kitabın idsini giriniz:");
                                            long bookID = input.nextLong();
                                            Library.getInstance().getLibrarian().removeBook(bookID);
                                            break;
                                        case "issue book":
                                            System.out.println("İşlem yapmak istediğiniz kitabın idsini giriniz.");
                                            long idBk = input.nextLong();
                                            System.out.println("İşlemi yapacak üyenin idsini giriniz.");
                                            long membeerID = input.nextLong();
                                            Library.getInstance().getLibrarian().issueBook(idBk, membeerID);
                                            break;
                                        case "take book back":
                                            System.out.println("Üyenin idsini giriniz: ");
                                            long memberid = input.nextLong();
                                            System.out.println("Geri verilen kitabın idsini giriniz: ");
                                            long bookid = input.nextLong();
                                            System.out.println("Kitabın temizlik durumunu giriniz: ");
                                            input.nextLine();
                                            String isclean = input.nextLine();
                                            Condition status = Library.getInstance().bringCondition(isclean);
                                            Library.getInstance().getLibrarian().takeBookBack(bookid, memberid, status);
                                            break;
                                        case "exit":
                                            System.out.println("Çıkış yapılıyor.");
                                            return;
                                        default:
                                            System.out.println("Geçersiz giriş! Verilen işlemlerden birini seçiniz: ");
                                            break;
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