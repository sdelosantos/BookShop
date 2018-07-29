package com.example.dell.bookshopandroid;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.List;

import model.Book;
import services.BookShopService;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "RecycleViewAdapter";
    List<Book> _list;
    Context mContext;

    public RecycleViewAdapter(Context context,List<Book> listBook){
        _list = listBook;
        mContext = context;
        BookShopService.CurrentBookList = _list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_book_list_content,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        try {
            Log.d(TAG, "onBindViewHolder: called");
            ViewHolder h = (ViewHolder) holder;

            Book _book = _list.get(position);
            Glide.with(mContext).load(_book.cover).into(h.imgBookCover);
            h.tvBookName.setText(_book.title);
            h.tvAuthor.setText("by " + _book.author.name);

            h.tvprice.setText("$"+String.valueOf(_book.price));
            String price = "";
            if(_book.shipping.price<1){
                price = "FREE shipping";
            }else{
                price = "$"+String.valueOf(_book.shipping.price);
            }
            h.tvShipping.setText(price);
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Book clickBook = _list.get(position);
                        Intent intent = new Intent(mContext, BookDetailActivity.class);
                        intent.putExtra("id", String.valueOf(clickBook.bookId));
                        mContext.startActivity(intent);
                    }catch (Exception ex){
                        String msg = ex.getMessage();

                    }
                }
            });
            /*h.btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //ObjectMapper
                    Anime _anime = _list.get(position);
                    Intent intent = new Intent(mContext, Anime_detail.class);
                    intent.putExtra("id", String.valueOf(_anime.id));
                    mContext.startActivity(intent);
                }
            });*/
        }
        catch (Exception  e){
            String msg = e.getMessage();
            Log.d(TAG, msg);
            System.out.print(msg);
            //Log.d("Error",e.getMessage());

        }
    }

    @Override
    public int getItemCount() {
        return _list.size();
    }

    // VIEW HOLDER
    public class ViewHolder extends RecyclerView.ViewHolder{
         ImageView imgBookCover;
         TextView tvBookName;
         TextView tvAuthor;
         TextView tvprice;
         TextView tvShipping;
         TextView tvPrice;
         LinearLayout parentLayout;

        public ViewHolder(View itemView){
            super(itemView);
            imgBookCover = (ImageView)itemView.findViewById(R.id.imgBookCover);
            tvBookName = (TextView)itemView.findViewById(R.id.tvBookName);
            tvAuthor = (TextView)itemView.findViewById(R.id.tvAuthor);
            tvprice= (TextView)itemView.findViewById(R.id.tvprice);
            tvShipping= (TextView)itemView.findViewById(R.id.tvShipping);
            parentLayout = (LinearLayout)itemView.findViewById(R.id.main_list_layout);
        }
    }
}
