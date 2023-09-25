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


}
