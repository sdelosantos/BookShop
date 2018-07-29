package com.example.dell.bookshopandroid;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BookListContent extends Activity {
    public ImageView imgBookCover;
    public TextView tvBookName;
    public TextView tvAuthor;
    public TextView price;
    public TextView tvShipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_shop);

        imgBookCover = findViewById(R.id.imgBookCover);
        tvBookName = findViewById(R.id.tvBookName);
        tvAuthor = findViewById(R.id.tvAuthor);
        price = findViewById(R.id.tvprice);
        tvShipping = findViewById(R.id.tvShipping);
    }
}
