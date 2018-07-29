package model;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate=true)
    public int id;
    @ColumnInfo(name = "user_id")
    public String userId;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "user_email")
    public String email;
    @ColumnInfo(name = "user_password")
    public String password;
}
