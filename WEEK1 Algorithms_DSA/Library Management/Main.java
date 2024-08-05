import java.util.Arrays;
 class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

 class Bookshelf {
    Book[] books;

    Bookshelf(Book[] books) {
        this.books = books;
    }

    // Method to perform linear search by title
    public Book linearSearchByTitle(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Method to perform binary search by title (assuming the list is sorted)
    public Book binarySearchByTitle(String title) {
        Arrays.sort(books, (b1, b2) -> b1.title.compareToIgnoreCase(b2.title));
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (books[mid].title.equalsIgnoreCase(title)) {
                return books[mid];
            } else if (books[mid].title.compareToIgnoreCase(title) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
public class Main {
    public static void main(String[] args) {
        Book book1 = new Book(1, "Book 1", "Author 1");
        Book book2 = new Book(2, "Book 2", "Author 2");
        Book book3 = new Book(3, "Book 3", "Author 3");

        Book[] books = {book1, book2, book3};
        Bookshelf bookshelf = new Bookshelf(books);

        // Perform linear search
        Book searchedBook = bookshelf.linearSearchByTitle("Book 2");
        if (searchedBook!= null) {
            System.out.println("Linear Search: Found book - " + searchedBook);
        } else {
            System.out.println("Linear Search: Book not found");
        }

        // Perform binary search
        searchedBook = bookshelf.binarySearchByTitle("Book 2");
        if (searchedBook!= null) {
            System.out.println("Binary Search: Found book - " + searchedBook);
        } else {
            System.out.println("Binary Search: Book not found");
        }
    }
}