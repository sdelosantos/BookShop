package services.RestApi;

import java.util.List;

import model.Book;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.ApiResponse;
import services.IBookShop;

public class RestService implements IBookShop {
    private IBookShopRestService service;
    private String url = "http://192.168.8.101:3000/";

    public RestService(){
        try {
            Retrofit retro = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            service = retro.create(IBookShopRestService.class);
        }catch (Exception ex){
            String msg = ex.getMessage();

        }
    }

    @Override
    public void listBooks(final ApiResponse<List<Book>> result) {
        try {
            Call<List<Book>> list =  service.listBooks();

            list.enqueue(new Callback<List<Book>>() {
                @Override
                public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                    final List<Book> l = response.body();
                    if (result != null)
                        result.success(l);
                }

                @Override
                public void onFailure(Call<List<Book>> call, Throwable t) {
                    if (result != null)
                        result.fail(null);
                }
            });
        }catch (Exception ex){
            String msg = ex.getMessage();

        }
    }

    @Override
    public void login(User user,ApiResponse<User> result) {
        try {
            Call<User> use =  service.login(user);
            final ApiResponse<User> r = result;
            use.enqueue(new Callback<User>() {
               @Override
               public void onResponse(Call<User> call, Response<User> response) {
                   final User us = response.body();
                   if (r != null)
                       r.success(us);
               }

               @Override
               public void onFailure(Call<User> call, Throwable t) {
                   if (r != null)
                       r.fail(null);
               }
           });
        }catch (Exception ex){
            String msg = ex.getMessage();

        }
    }

    @Override
    public void getUserById(String userId, ApiResponse<User> result) {

    }

    @Override
    public void saveUser(User user, ApiResponse<User> result) {

    }

}
