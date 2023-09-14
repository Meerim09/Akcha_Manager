package com.meerim.akchamanager.database_expense;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.meerim.akchamanager.models.ExpenseModel;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ExpenseDAO_Impl implements ExpenseDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExpenseModel> __insertionAdapterOfExpenseModel;

  private final EntityDeletionOrUpdateAdapter<ExpenseModel> __deletionAdapterOfExpenseModel;

  public ExpenseDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExpenseModel = new EntityInsertionAdapter<ExpenseModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `expense` (`id`,`description`,`cat`,`date`,`amount`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ExpenseModel value) {
        stmt.bindLong(1, value.getId());
        if (value.getDescription() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDescription());
        }
        if (value.getCat() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCat());
        }
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate());
        }
        stmt.bindLong(5, value.getAmount());
      }
    };
    this.__deletionAdapterOfExpenseModel = new EntityDeletionOrUpdateAdapter<ExpenseModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `expense` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ExpenseModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void addExpense(final ExpenseModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfExpenseModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteExpense(final ExpenseModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfExpenseModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ExpenseModel> getAllExpense() {
    final String _sql = "select * FROM expense";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfCat = CursorUtil.getColumnIndexOrThrow(_cursor, "cat");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final List<ExpenseModel> _result = new ArrayList<ExpenseModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ExpenseModel _item;
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpCat;
        if (_cursor.isNull(_cursorIndexOfCat)) {
          _tmpCat = null;
        } else {
          _tmpCat = _cursor.getString(_cursorIndexOfCat);
        }
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final int _tmpAmount;
        _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
        _item = new ExpenseModel(_tmpDescription,_tmpDate,_tmpAmount,_tmpCat);
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
