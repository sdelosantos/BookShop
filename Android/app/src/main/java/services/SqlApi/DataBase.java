package services.SqlApi;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import model.User;

@Database(entities = {User.class}, version = 1)
public abstract class DataBase extends RoomDatabase{
        public static final String NAME = "BookShopDB";
        public abstract IBookShopSQL service();
}
