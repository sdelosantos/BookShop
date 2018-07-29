package services;
import java.util.*;
import model.*;

public interface IBookShop {
    void listBooks(ApiResponse<List<Book>> result);
    void login(User user,ApiResponse<User> result);
    void getUserById(String userId,ApiResponse<User> result);
    void saveUser(User user,ApiResponse<User> result);


}
