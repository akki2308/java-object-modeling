import java.util.ArrayList;
import java.util.List;

// Book class (Independent entity)
class Book {
    private String title;
    private String author;
    private int isbn;

    public Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getIsbn() {
        return isbn;
    }

    public void displayBook() {
        System.out.println("Book: " + title + " | Author: " + author + " | ISBN: " + isbn);
    }
}

// Library class (Aggregates books)
class Library {
    private String name;
    private List<Book> books;  // Aggregation: Library has books, but books can exist independently

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added to " + name);
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            System.out.println(book.getTitle() + " removed from " + name);
        } else {
            System.out.println("Book not found in " + name);
        }
    }

    public void displayLibraryBooks() {
        System.out.println("\nLibrary: " + name);
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                books.get(i).displayBook();
            }
        }
    }
}

// Main class to test the implementation
public class LibraryManagement {
    public static void main(String[] args) {
        // Creating books (independent of the library)
        Book book1 = new Book("Java Programming", "James Gosling", 12345);
        Book book2 = new Book("Effective Java", "Joshua Bloch", 67890);
        Book book3 = new Book("Clean Code", "Robert C. Martin", 11223);

        // Creating a library
        Library myLibrary = new Library("City Library");

        // Adding books to the library
        myLibrary.addBook(book1);
        myLibrary.addBook(book2);

        // Displaying books in the library
        myLibrary.displayLibraryBooks();

        // Removing a book
        myLibrary.removeBook(book1);

        // Displaying updated book list
        myLibrary.displayLibraryBooks();

        // The book objects still exist outside the library
        System.out.println("\nBook still exists outside the library:");
        book1.displayBook();
    }
}
