package services.SqlApi;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import model.User;
@Dao
public interface IBookShopSQL {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUser(User user);
    @Query("SELECT * FROM user WHERE user_id=:userId")
    User getUserById(String userId);
}
