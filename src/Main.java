import Model.Book;
import bussiness.BookBussiness;
import config.MySqlConfig;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
         * 2. In menu và thực hiện các chức năng theo menu
         *   ****************MENU*************************
         *   1. Hiển thị các sách
         *   2. Thêm mới sách
         *   3. Cập nhật sách
         *   4. Xóa sách
         *   5. Tìm sách theo tên sách
         *   6. Thống kê sách theo tác giả
         *   7. Sắp xếp sách theo giá tăng dần (procedure)
         *   8. Thoát
         * */
        do {
            System.out.println("************MENU************");
            System.out.println("1. Hiển thị các sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa ");
            System.out.println("5. Tìm sách theo tên");
            System.out.println("6. Thống kê sách theo tác giả");
            System.out.println("7. Sắp xếp sách theo giá tăng dần");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn:");
            try {
                Byte choice = Byte.parseByte(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayListBook();
                        break;
                    case 2:
                        createProduct(scanner);
                        break;
                    case 3:
                        updateBook(scanner);
                        break;
                    case 4:
                        delete_Book(scanner);
                        break;
                    case 5:
                        seachBook(scanner);
                        break;
                    case 6:
                        thongKeTheoTacGia();
                        break;
                    case 7:
                        sapXep();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println(" Vui lòng lụa chọn đúng chức ngăn");
                        break;
                }
            }catch (NumberFormatException numberFormatException){
                numberFormatException.getMessage();
            } catch (Exception e){
                e.getMessage();
            }


        }while (true);
    }
    public static void displayListBook(){
        List<Book> listBook = BookBussiness.getAllBook();
        System.out.println("THÔNG TIN CÁC SẢN PHẨM:");
        listBook.stream().forEach(book-> {
            book.outputData();
            System.out.println("");
        });
    }

    public static void createProduct(Scanner scanner){
        //Nhập dữ liệu cho 1 sản phẩm để thêm mới
        Book book = new Book();
        book.inputData(scanner);
        //Gọi createProduct của ProductBussiness để thực hiện thêm dữ liệu vào db
        boolean result = BookBussiness.createBook(book);
        if (result){
            System.out.println("Thêm mới thành công");
        }else{
            System.err.println("Có lỗi xảy ra trong quá trình thực hiện, vui lòng thực hiện lai");
        }
    }
    public static void updateBook (Scanner scanner){
        System.out.println("Nhập mã sản phẩm cần cập nhật");
        String bookId = scanner.nextLine();
        Book bookUpdate = BookBussiness.getBook(bookId);
        if (bookUpdate  !=null){
            System.out.println("Nhập tên: ");
            bookUpdate.setBookName(scanner.nextLine());
            System.out.println("Tác Giả");
            bookUpdate.setAuthor(scanner.nextLine());
            System.out.println("Giá Tiền");
            bookUpdate.setPrice(Float.parseFloat(scanner.nextLine()));
            System.out.println("Trạng thái hoạt động");
            bookUpdate.setStatus_book(Boolean.parseBoolean(scanner.nextLine()));

            // thực hiện cập nhật.
            boolean result = BookBussiness.updateBook(bookUpdate);
            if (result){
                System.out.println("Cập nhật thành công");
            }else {
                System.out.println("Cập nhật thất bại");
            }
        }else {
            System.err.println("Mã sp không tồn tại");
        }
    }
    public static void delete_Book(Scanner scanner){
        System.out.println("Nhập vào mã cần xoá.");
        String delete_bookId = scanner.nextLine();
        Book book = BookBussiness.getBook(delete_bookId);
        if (book != null){
            boolean isDelete_Book = BookBussiness.delete_Book(delete_bookId);
            if (isDelete_Book){
                System.out.println("Đã xoá thành công");
            }else {
                System.out.println("Xoá thất bại");
            }
        }else {
            System.out.println("Mã sách không tồn tại");
        }
    }
    public static void seachBook (Scanner scanner){
        System.out.println("Nhập tên tìm kiếm");
        String bookName = scanner.nextLine();
        List<Book> listBook = BookBussiness.seach_Book(bookName);
        listBook.stream().forEach(book ->{
            book.outputData();
            System.out.println("");
        } );
    }
    public  static void thongKeTheoTacGia(){
        List<Book>bookList = BookBussiness.getAllBook();
        bookList.stream().sorted(Comparator.comparing(Book::getAuthor))
                .forEach(book ->{
                    book.outputData();
                    System.out.println("");
                } );
    }
    public static void sapXep(){
        List<Book> bookList = BookBussiness.sapXep();
        bookList.stream().collect(Collectors.toList()).forEach(System.out::println);
    }


}