package bussiness;

import Model.Book;
import config.MySqlConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookBussiness {
    public static List<Book> getAllBook(){
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Book> list_book = null;
        try {
            //1. Tạo đối tượng Connection
            connection = MySqlConfig.openConnection();
            //2. Tạo đối tượng CallableStatement
            callableStatement = connection.prepareCall("{call ouput_Book()}");
            ResultSet resultSet = callableStatement.executeQuery();
            list_book = new ArrayList<>();
            while (resultSet.next()){
                Book book = new Book();
                book.setBookId(resultSet.getString("bookId"));
                book.setBookName(resultSet.getString("bookName"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getFloat("price"));
                book.setStatus_book(resultSet.getBoolean("status"));
                list_book.add(book);
            }
            //3. Gọi procedure

            //4. Xử lý dữ liệu và trả về listProduct
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection,callableStatement);
        }
        return  list_book;
    }

    public static boolean createBook(Book book){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MySqlConfig.openConnection();
            callSt = conn.prepareCall("{call addBook(?,?,?,?,?)}");
            //set dữ liệu cho các tham số vào của procedure
            callSt.setString(1,book.getBookId());
            callSt.setString(2,book.getBookName());
            callSt.setFloat(3,book.getPrice());
            callSt.setString(4,book.getAuthor());
            callSt.setBoolean(5,book.isStatus());
            //Đăng ký kiểu dữ liệu cho tham số trả ra
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }
    // cập nhật
    public static boolean updateBook(Book book){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = MySqlConfig.openConnection();
            callSt = conn.prepareCall("{call update_book(?,?,?,?,?)}");
            //set dữ liệu cho các tham số vào của procedure
            callSt.setString(1,book.getBookId());
            callSt.setString(2,book.getBookName());
            callSt.setFloat(3,book.getPrice());
            callSt.setString(4,book.getAuthor());
            callSt.setBoolean(5,book.isStatus());
            //Đăng ký kiểu dữ liệu cho tham số trả ra
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(conn,callSt);
        }
        return result;
    }

    public static boolean delete_Book (String bookId){
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean result = false;
        try {
            connection = MySqlConfig.openConnection();
            callableStatement = connection.prepareCall("{call delete_Book(?)}");
            callableStatement.setString(1,bookId);
            callableStatement.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection,callableStatement);
        }
        return result;
    }
    public static List<Book> seach_Book (String book_Name){
     Connection connection = null;
     CallableStatement callableStatement = null;
     List<Book> list_Book = null;
     try {
         connection = MySqlConfig.openConnection();
         callableStatement = connection.prepareCall("{call seach_NameBook(?)}");
         callableStatement.setString(1,book_Name);
         ResultSet resultSet = callableStatement.executeQuery();
         list_Book = new ArrayList<>();
         while (resultSet.next()){
             Book book = new Book();
             book.setBookId(resultSet.getString("bookId"));
             book.setBookName(resultSet.getString("bookName"));
             book.setAuthor(resultSet.getString("author"));
             book.setPrice(resultSet.getFloat("price"));
             book.setStatus_book(resultSet.getBoolean("status"));
             list_Book.add(book);
         }

     }catch (Exception ex){
         ex.printStackTrace();
     }
     return  list_Book;
    }

    public static Book getBook(String bookId){
        Connection connection = null;
        CallableStatement callableStatement = null;
        Book book = null;
        try {
            connection = MySqlConfig.openConnection();
            callableStatement = connection.prepareCall("{call get_BookId(?)}");
            callableStatement.setString(1,bookId);
            ResultSet resultSet = callableStatement.executeQuery();
            book = new Book();
            while (resultSet.next()){
                book.setBookId(resultSet.getString("bookId"));
                book.setBookName(resultSet.getString("bookName"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getFloat("price"));
                book.setStatus_book(resultSet.getBoolean("status"));
            }
        }catch (Exception ex ){
            ex.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection,callableStatement);
        }
        return book;
    }
    public static List<Book> sapXep(){
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Book>bookList = null;
        try {
            connection = MySqlConfig.openConnection();
            callableStatement = connection.prepareCall("{call sapXep_GiaTang()}");
            ResultSet resultSet = callableStatement.executeQuery();
            bookList = new ArrayList<>();
            while (resultSet.next()){
                Book book = new Book();
                book.setBookId(resultSet.getString("bookId"));
                book.setBookName(resultSet.getString("bookName"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getFloat("price"));
                book.setStatus_book(resultSet.getBoolean("status"));
                bookList.add(book);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            MySqlConfig.closeConnection(connection,callableStatement);
        }
        return bookList;
    }



}
