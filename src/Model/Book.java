package Model;

import java.util.Scanner;

public class Book {
    private String bookId; //mã sách gồm 4 ký tự bắt đầu là B
    private  String bookName; //tên sách có từ 10-50 ký tự
    private  float price; //giá sách có giá trị lớn hơn 0
    private  String  author;// tác giả
    private  boolean status_book; //trạng thái sách (true|false)

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status_book;
    }

    public void setStatus_book(boolean status) {
        this.status_book = status;
    }

    public Book() {
    }

    public Book(String bookId, String bookName, float price, String author, boolean status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.author = author;
        this.status_book = status;
    }

    public void inputData(Scanner scanner){
        System.out.println("Nhap Ma Sach");
        this.bookId = scanner.nextLine();
        System.out.println("Nhap Ten Sach");
        this.bookName = scanner.nextLine();
        System.out.println("Nhap Tac Gia");
        this.author = scanner.nextLine();
        System.out.println("Nhap Gia Sach");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Trang thai sach");
        this.status_book = validateCategoryStatus(scanner);
    }
    public void outputData (){
        String status_book_2 = this.status_book ? "Hoạt động" : "Không hoạt động";
        System.out.printf("Ma Sach: %s Ten Sach: %s Tac Gia: %s  Gia: %.2f Trạng thái: %s",
                this.bookId, this.bookName, this.author, this.price, status_book_2);
    }

    public boolean validateCategoryStatus(Scanner scanner) {
        System.out.println("Nhập trạng thái thể loại sách (true, false)");
        do {
            String str = scanner.nextLine();
            if (!isStrNull(str)) {
                if (str.trim().equalsIgnoreCase("true") || str.trim().equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(str);
                } else {
                    System.out.println("Vui lòng nhập kí tự: true hoặc false");
                }
            } else {
                System.out.println("Vui Lòng Không Để Trống");
            }
        } while (true);
    };

    public boolean isStrNull(String str) {
        return str==null || str.isEmpty();
    };

}
