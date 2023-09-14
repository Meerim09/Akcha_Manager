package com.meerim.akchamanager.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.meerim.akchamanager.models.IncomeModel;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class IncomeDAO_Impl implements IncomeDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<IncomeModel> __insertionAdapterOfIncomeModel;

  private final EntityDeletionOrUpdateAdapter<IncomeModel> __deletionAdapterOfIncomeModel;

  public IncomeDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIncomeModel = new EntityInsertionAdapter<IncomeModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `notifications` (`id`,`description`,`date`,`amount`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IncomeModel value) {
        stmt.bindLong(1, value.getId());
        if (value.getDescription() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescription());
        }
        if (value.getDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDate());
        }
        stmt.bindLong(4, value.getAmount());
      }
    };
    this.__deletionAdapterOfIncomeModel = new EntityDeletionOrUpdateAdapter<IncomeModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `notifications` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IncomeModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void addIncome(final IncomeModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfIncomeModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteIncome(final IncomeModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfIncomeModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<IncomeModel> getAllIncome() {
    final String _sql = "select * FROM notifications";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final List<IncomeModel> _result = new ArrayList<IncomeModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final IncomeModel _item;
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final int _tmpAmount;
        _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
        _item = new IncomeModel(_tmpDescription,_tmpDate,_tmpAmount);
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
