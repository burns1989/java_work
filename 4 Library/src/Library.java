public class Library {
	String address;
	Book[] books;
	int noBooks = 0;
	static int openingTime = 9;
	static int closingTime = 5;

	public Library(String add) {
		address = add;
		books = new Book[5];
	}

	public void addBook(Book book) {
		books[noBooks] = book;
		noBooks++;
	}

	public static void printOpeningHours() {
		System.out.println("Libraries are open daily from " + openingTime + "am to " + closingTime + "pm.");
	}

	public void printAddress() {
		System.out.println(address);
	}

	public void borrowBook(String bookTitle) {
		boolean bookFound = false;
		for (int i = 0; i < noBooks; i++) {
			if (books[i].title.equals(bookTitle)) {
				bookFound = true;
				if (!books[i].isBorrowed()) {
					books[i].borrowed();
					System.out.println("You successfully borrowed " + books[i].title);
				} else if (books[i].isBorrowed()) {
					System.out.println("Sorry, this book is already borrowed.");
				}
			}
		}
		if (!bookFound) {
			System.out.println("Sorry, this books is not in our catalog.");
		}

	}

	public void returnBook(String bookTitle) {
		boolean bookFound = false;
		for (int i = 0; i < noBooks; i++) {
			if (books[i].title.equals(bookTitle)) {
				bookFound = true;
				if (books[i].isBorrowed()) {
					System.out.println("You successfully returned " + books[i].title);
					books[i].returned();
				}
			}
		}
	}

	/**
	 * Print out all the books in the library.
	 */
	public void printAvailableBooks() {
		for (int i = 0; i < noBooks; i++) {
			if (!books[i].isBorrowed()) {
				System.out.println(books[i].title);
			}
		}
	}

	public static void main(String[] args) {
		// Create two libraries
		Library firstLibrary = new Library("10 Main St.");
		Library secondLibrary = new Library("228 Liberty St.");

		// Add four books to the first library
		firstLibrary.addBook(new Book("The Da Vinci Code"));
		firstLibrary.addBook(new Book("Le Petit Prince"));
		firstLibrary.addBook(new Book("A Tale of Two Cities"));
		firstLibrary.addBook(new Book("The Lord of the Rings"));

		// Print opening hours and the addresses
		System.out.println("Library hours:");
		printOpeningHours();
		System.out.println();
		System.out.println("Library addresses:");
		firstLibrary.printAddress();
		secondLibrary.printAddress();
		System.out.println();

		// Try to borrow The Lords of the Rings from both libraries
		System.out.println("Borrowing The Lord of the Rings:");
		firstLibrary.borrowBook("The Lord of the Rings");
		firstLibrary.borrowBook("The Lord of the Rings");
		secondLibrary.borrowBook("The Lord of the Rings");
		System.out.println();

		// Print the titles of all available books from both libraries
		System.out.println("Books available in the first library:");
		firstLibrary.printAvailableBooks();
		System.out.println();
		System.out.println("Books available in the second library:");
		secondLibrary.printAvailableBooks();
		System.out.println();

		// Return The Lords of the Rings to the first library
		System.out.println("Returning The Lord of the Rings:");
		firstLibrary.returnBook("The Lord of the Rings");
		System.out.println();

		// Print the titles of available from the first library
		System.out.println("Books available in the first library:");
		firstLibrary.printAvailableBooks();

	}

}
