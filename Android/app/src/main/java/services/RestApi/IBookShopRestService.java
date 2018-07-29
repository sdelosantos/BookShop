package services.RestApi;
import java.util.List;
import model.*;
import retrofit2.*;
import retrofit2.http.*;

public interface IBookShopRestService {
    @GET("book/list")
    Call<List<Book>> listBooks();

    @POST("/user/login")
    Call<User> login(@Body User user);
}
