import Model.Book;
import bussiness.BookBussiness;
import config.MySqlConfig;

import java.util.List;
import java.util.Scanner;

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
            System.out.println("5. Tìm kiếm sản phẩm theo mã sản phảm");
            System.out.println("6. Tìm kiếm sản phẩm trong khoảng giá");
            System.out.println("7. Sắp xếp sách theo giá tăng dần");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn:");
            Byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListBook();
                    break;
                case 2:
                    createProduct(scanner);
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println(" Vui lòng lụa chọn đúng chức ngăn");
                    break;
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

}