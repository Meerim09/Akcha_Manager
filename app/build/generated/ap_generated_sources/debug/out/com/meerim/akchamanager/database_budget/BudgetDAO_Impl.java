package com.meerim.akchamanager.database_budget;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.meerim.akchamanager.models.BudgetModel;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BudgetDAO_Impl implements BudgetDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BudgetModel> __insertionAdapterOfBudgetModel;

  private final EntityDeletionOrUpdateAdapter<BudgetModel> __deletionAdapterOfBudgetModel;

  public BudgetDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBudgetModel = new EntityInsertionAdapter<BudgetModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `budget` (`id`,`name`,`amount`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BudgetModel value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getAmount());
      }
    };
    this.__deletionAdapterOfBudgetModel = new EntityDeletionOrUpdateAdapter<BudgetModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `budget` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BudgetModel value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void addBudget(final BudgetModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBudgetModel.insert(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteBudget(final BudgetModel model) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfBudgetModel.handle(model);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<BudgetModel> getAllBudgets() {
    final String _sql = "select * FROM budget";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final List<BudgetModel> _result = new ArrayList<BudgetModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BudgetModel _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final int _tmpAmount;
        _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
        _item = new BudgetModel(_tmpName,_tmpAmount);
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
