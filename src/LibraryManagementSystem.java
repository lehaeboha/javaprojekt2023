import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interfejs do wyświetlania informacji o książce
interface Displayable {
    void display();
}

// Abstrakcyjna klasa reprezentująca książkę
abstract class Book implements Displayable {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

// Klasa reprezentująca książkę papierową
class PaperBook extends Book {
    private int pageCount;

    public PaperBook(String title, String author, int pageCount) {
        super(title, author);
        this.pageCount = pageCount;
    }

    @Override
    public void display() {
        System.out.println("Książka papierowa: " + getTitle() + " - " + getAuthor());
        System.out.println("Liczba stron: " + pageCount);
    }
}

// Klasa reprezentująca książkę elektroniczną
class EBook extends Book {
    private double fileSize;

    public EBook(String title, String author, double fileSize) {
        super(title, author);
        this.fileSize = fileSize;
    }

    @Override
    public void display() {
        System.out.println("Książka elektroniczna: " + getTitle() + " - " + getAuthor());
        System.out.println("Rozmiar pliku: " + fileSize + " MB");
    }
}

// Główna klasa systemu zarządzania biblioteką
public class LibraryManagementSystem {
    private List<Book> biblioteka;

    public LibraryManagementSystem() {
        biblioteka = new ArrayList<>();
    }

    public void dodajKsiazke(Book book) {
        biblioteka.add(book);
    }
    public void usunKsiazke(Book book){
      biblioteka.remove(book);
    }

    public void wyswietlKsiazki() {
        if (biblioteka.isEmpty()) {
            System.out.println("Biblioteka jest pusta.");
        } else {
            System.out.println("Lista książek w bibliotece:");
            for (Book book : biblioteka) {
                book.display();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem systemZarzadzaniaBiblioteka = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        boolean wyjscie = false;

        while (!wyjscie) {
            System.out.println("Wybierz operację:");
            System.out.println("1. Dodaj książkę");
            System.out.println("2. Usuń książkę");
            System.out.println("3. Wyświetl wszystkie książki");
            System.out.println("4. Wyjdź");

            int wybor = scanner.nextInt();
            scanner.nextLine(); // Wyczyść bufor po wprowadzeniu liczby

            switch (wybor) {
                case 1:
                    System.out.println("Wprowadź tytuł książki:");
                    String tytul = scanner.nextLine();
                    System.out.println("Wprowadź autora książki:");
                    String autor = scanner.nextLine();
                    System.out.println("Wybierz rodzaj książki (1 - papierowa, 2 - elektroniczna):");
                    int wyborRodzaju = scanner.nextInt();

                    if (wyborRodzaju == 1) {
                        System.out.println("Podaj liczbę stron:");
                        int liczbaStron = scanner.nextInt();
                        Book ksiazkaPapierowa = new PaperBook(tytul, autor, liczbaStron);
                        systemZarzadzaniaBiblioteka.dodajKsiazke(ksiazkaPapierowa);
                    } else if (wyborRodzaju == 2) {
                        System.out.println("Podaj rozmiar pliku (w MB):");
                        double rozmiarPliku = scanner.nextDouble();
                        Book ksiazkaElektroniczna = new EBook(tytul, autor, rozmiarPliku);
                        systemZarzadzaniaBiblioteka.dodajKsiazke(ksiazkaElektroniczna);
                    } else {
                        System.out.println("Nieprawidłowy wybór rodzaju książki.");
                    }

                    break;
                case 2:
                    System.out.println("Wprowadź tytuł książki do usunięcia:");
                    String tytulDoUsuniecia = scanner.nextLine();
                    System.out.println("Wprowadź autora książki do usunięcia:");
                    String autorDoUsuniecia = scanner.nextLine();

                    // Przeszukaj listę książek w poszukiwaniu pasującej książki
                    Book ksiazkaDoUsuniecia = null;
                    for (Book book : systemZarzadzaniaBiblioteka.biblioteka) {
                        if (book.getTitle().equalsIgnoreCase(tytulDoUsuniecia) && book.getAuthor().equalsIgnoreCase(autorDoUsuniecia)) {
                            ksiazkaDoUsuniecia = book;
                            break;
                        }
                    }

                    if (ksiazkaDoUsuniecia != null) {
                        systemZarzadzaniaBiblioteka.usunKsiazke(ksiazkaDoUsuniecia);
                        System.out.println("Książka została usunięta z biblioteki.");
                    } else {
                        System.out.println("Nie można znaleźć podanej książki w bibliotece.");
                    }

                    break;

                case 3:
                    systemZarzadzaniaBiblioteka.wyswietlKsiazki();
                    break;

                case 4:
                    wyjscie = true;
                    break;

                default:
                    System.out.println("Nieprawidłowy wybór operacji.");
                    break;
            }
        }

        scanner.close();
    }
}
