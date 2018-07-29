package com.example.dell.bookshopandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import model.Book;
import services.BookShopService;

public class BookDetailActivity extends Activity {
    TextView tvBookName;
    ImageView imgBookCover;
    TextView tvAuthorName;
    TextView tvFormat;
    TextView tvShippingDetail;
    TextView bookDescription;
    TextView tvPrice;
    Button btnBuy;
    Button btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detalle);
        //set controllers
        tvBookName = findViewById(R.id.tvBookName);
        imgBookCover = findViewById(R.id.imgBookCover);
        tvAuthorName = findViewById(R.id.tvAuthorName);
        tvFormat = findViewById(R.id.tvFormat);
        tvShippingDetail = findViewById(R.id.tvShippingDetail);
        tvPrice = findViewById(R.id.tvPrice);
        bookDescription = findViewById(R.id.bookDescription);
        btnBuy = findViewById(R.id.btnBuy);
        btnCart = findViewById(R.id.btnCart);

        String bookId = getIntent().getExtras().getString("id");
        Book bk = getBook(bookId);
        setData(bk);
    }

    private void setData(Book book){
        tvBookName.setText(book.title);
        tvFormat.setText(book.format);
        tvAuthorName.setText("by "+book.author.name);
        tvShippingDetail.setText(book.shipping.description);
        bookDescription.setText(book.description);
        tvPrice.setText("$"+String.valueOf(book.price));
        Glide.with(this).load(book.cover).into(imgBookCover);

    }

    private Book getBook(String id){
        Book bk = new Book();
        for(Book b : BookShopService.CurrentBookList){
            if(b.bookId.equals(id)){
                bk = b;
                break;
            }
        }
        return  bk;
    }
}
