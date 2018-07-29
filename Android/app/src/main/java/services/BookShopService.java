package services;

import java.util.ArrayList;
import java.util.List;

import model.Book;
import services.RestApi.RestService;

public class BookShopService{
  public static final IBookShop REST = new RestService();
  /*public static  IBookShop SQL;
  public static final void setContext(Context context){
    SQL = new SqlService(context);
  }*/
  public static List<Book> CurrentBookList = new ArrayList<Book>();
}
