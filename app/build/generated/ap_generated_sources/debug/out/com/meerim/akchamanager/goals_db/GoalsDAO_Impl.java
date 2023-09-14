package com.meerim.akchamanager.goals_db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.meerim.akchamanager.models.GoalsModel;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GoalsDAO_Impl implements GoalsDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GoalsModel> __insertionAdapterOfGoalsModel;

  private final EntityDeletionOrUpdateAdapter<GoalsModel> __deletionAdapterOfGoalsModel;

  public GoalsDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGoalsModel = new EntityInsertionAdapter<GoalsModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `goals` (`id`,`name`,`amount`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GoalsModel value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getAmount());
      }
    };
    this.__deletionAdapterOfGoalsModel = new EntityDeletionOrUpdateAdapter<GoalsModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `goals` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GoalsModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void addGoals(final GoalsModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfGoalsModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteGoals(final GoalsModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfGoalsModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<GoalsModel> getAllGoals() {
    final String _sql = "SELECT * FROM goals";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final List<GoalsModel> _result = new ArrayList<GoalsModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GoalsModel _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final int _tmpAmount;
        _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
        _item = new GoalsModel(_tmpName,_tmpAmount);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
