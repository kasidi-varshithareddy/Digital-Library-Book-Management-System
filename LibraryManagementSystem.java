import java.util.*;
class Book {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;
    public Book(int bookId, String title, String author, String genre, String availabilityStatus) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }
    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setAvailabilityStatus(String status) { this.availabilityStatus = status; }
    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Status: " + availabilityStatus;
    }
}
class Library {
    private Map<Integer, Book> bookCatalog = new HashMap<>();
    public void addBook(Book book) {
        if (bookCatalog.containsKey(book.getBookId())) {
            System.out.println("Book ID already exists!");
        } else {
            bookCatalog.put(book.getBookId(), book);
            System.out.println("Book added successfully!");
        }
    }
    public void viewAllBooks() {
        if (bookCatalog.isEmpty()) {
            System.out.println("No books available.");
        } else {
            bookCatalog.values().forEach(System.out::println);
        }
    }
    public Book searchBookById(int bookId) {
        return bookCatalog.get(bookId);
    }
    public Book searchBookByTitle(String title) {
        return bookCatalog.values().stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }
    public void updateBook(int bookId, String title, String author, String genre, String status) {
        Book book = bookCatalog.get(bookId);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setAvailabilityStatus(status);
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }
    public void deleteBook(int bookId) {
        if (bookCatalog.remove(bookId) != null) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Book\n2. View All Books\n3. Search Book by ID\n4. Search Book by Title\n5. Update Book\n6. Delete Book\n7. Exit");
            System.out.print("Choose an option: ");
             try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the leftover newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Book ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume the leftover newline
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Enter Availability Status (Available/Checked Out): ");
                        String status = scanner.nextLine();
                        library.addBook(new Book(id, title, author, genre, status));
                        break;
                    case 2:
                        library.viewAllBooks();
                        break;
                    case 3:
                        System.out.print("Enter Book ID to search: ");
                        int searchId = scanner.nextInt();
                        scanner.nextLine(); // Consume the leftover newline
                        Book bookById = library.searchBookById(searchId);
                        System.out.println(bookById != null ? bookById : "Book not found.");
                        break;
                    case 4:
                        System.out.print("Enter Book Title to search: ");
                        String searchTitle = scanner.nextLine();
                        Book bookByTitle = library.searchBookByTitle(searchTitle);
                        System.out.println(bookByTitle != null ? bookByTitle : "Book not found.");
                        break;
                    case 5:
                        System.out.print("Enter Book ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume the leftover newline
                        System.out.print("Enter New Title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter New Author: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Enter New Genre: ");
                        String newGenre = scanner.nextLine();
                        System.out.print("Enter New Availability Status (Available/Checked Out): ");
                        String newStatus = scanner.nextLine();
                        library.updateBook(updateId, newTitle, newAuthor, newGenre, newStatus);
                        break;
                    case 6:
                        System.out.print("Enter Book ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume the leftover newline
                        library.deleteBook(deleteId);
                        break;
                    case 7:
                        System.out.println("Exiting system...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}