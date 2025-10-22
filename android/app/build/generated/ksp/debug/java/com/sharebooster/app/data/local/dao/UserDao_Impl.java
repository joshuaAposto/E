package com.sharebooster.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.sharebooster.app.data.local.entity.UserEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter<UserEntity> __deletionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter<UserEntity> __updateAdapterOfUserEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUserById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllUsers;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`userId`,`username`,`fullname`,`email`,`pfpUrl`,`isPremium`,`premiumExpiration`,`isAdmin`,`status`,`createdAt`,`lastUpdated`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindString(1, entity.getUserId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getFullname());
        statement.bindString(4, entity.getEmail());
        if (entity.getPfpUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPfpUrl());
        }
        final int _tmp = entity.isPremium() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getPremiumExpiration() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getPremiumExpiration());
        }
        final int _tmp_1 = entity.isAdmin() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        statement.bindString(9, entity.getStatus());
        if (entity.getCreatedAt() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCreatedAt());
        }
        statement.bindLong(11, entity.getLastUpdated());
      }
    };
    this.__deletionAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `users` WHERE `userId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindString(1, entity.getUserId());
      }
    };
    this.__updateAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `users` SET `userId` = ?,`username` = ?,`fullname` = ?,`email` = ?,`pfpUrl` = ?,`isPremium` = ?,`premiumExpiration` = ?,`isAdmin` = ?,`status` = ?,`createdAt` = ?,`lastUpdated` = ? WHERE `userId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindString(1, entity.getUserId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getFullname());
        statement.bindString(4, entity.getEmail());
        if (entity.getPfpUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPfpUrl());
        }
        final int _tmp = entity.isPremium() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getPremiumExpiration() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getPremiumExpiration());
        }
        final int _tmp_1 = entity.isAdmin() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        statement.bindString(9, entity.getStatus());
        if (entity.getCreatedAt() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCreatedAt());
        }
        statement.bindLong(11, entity.getLastUpdated());
        statement.bindString(12, entity.getUserId());
      }
    };
    this.__preparedStmtOfDeleteUserById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM users WHERE userId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllUsers = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM users";
        return _query;
      }
    };
  }

  @Override
  public Object insertUser(final UserEntity user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserEntity.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUser(final UserEntity user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUserEntity.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateUser(final UserEntity user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserEntity.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUserById(final String userId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUserById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, userId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteUserById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllUsers(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllUsers.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllUsers.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getUserById(final String userId,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfFullname = CursorUtil.getColumnIndexOrThrow(_cursor, "fullname");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPfpUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "pfpUrl");
          final int _cursorIndexOfIsPremium = CursorUtil.getColumnIndexOrThrow(_cursor, "isPremium");
          final int _cursorIndexOfPremiumExpiration = CursorUtil.getColumnIndexOrThrow(_cursor, "premiumExpiration");
          final int _cursorIndexOfIsAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdmin");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpFullname;
            _tmpFullname = _cursor.getString(_cursorIndexOfFullname);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPfpUrl;
            if (_cursor.isNull(_cursorIndexOfPfpUrl)) {
              _tmpPfpUrl = null;
            } else {
              _tmpPfpUrl = _cursor.getString(_cursorIndexOfPfpUrl);
            }
            final boolean _tmpIsPremium;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPremium);
            _tmpIsPremium = _tmp != 0;
            final String _tmpPremiumExpiration;
            if (_cursor.isNull(_cursorIndexOfPremiumExpiration)) {
              _tmpPremiumExpiration = null;
            } else {
              _tmpPremiumExpiration = _cursor.getString(_cursorIndexOfPremiumExpiration);
            }
            final boolean _tmpIsAdmin;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsAdmin);
            _tmpIsAdmin = _tmp_1 != 0;
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _result = new UserEntity(_tmpUserId,_tmpUsername,_tmpFullname,_tmpEmail,_tmpPfpUrl,_tmpIsPremium,_tmpPremiumExpiration,_tmpIsAdmin,_tmpStatus,_tmpCreatedAt,_tmpLastUpdated);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<UserEntity> getUserByIdFlow(final String userId) {
    final String _sql = "SELECT * FROM users WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"users"}, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfFullname = CursorUtil.getColumnIndexOrThrow(_cursor, "fullname");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPfpUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "pfpUrl");
          final int _cursorIndexOfIsPremium = CursorUtil.getColumnIndexOrThrow(_cursor, "isPremium");
          final int _cursorIndexOfPremiumExpiration = CursorUtil.getColumnIndexOrThrow(_cursor, "premiumExpiration");
          final int _cursorIndexOfIsAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdmin");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpFullname;
            _tmpFullname = _cursor.getString(_cursorIndexOfFullname);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPfpUrl;
            if (_cursor.isNull(_cursorIndexOfPfpUrl)) {
              _tmpPfpUrl = null;
            } else {
              _tmpPfpUrl = _cursor.getString(_cursorIndexOfPfpUrl);
            }
            final boolean _tmpIsPremium;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPremium);
            _tmpIsPremium = _tmp != 0;
            final String _tmpPremiumExpiration;
            if (_cursor.isNull(_cursorIndexOfPremiumExpiration)) {
              _tmpPremiumExpiration = null;
            } else {
              _tmpPremiumExpiration = _cursor.getString(_cursorIndexOfPremiumExpiration);
            }
            final boolean _tmpIsAdmin;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsAdmin);
            _tmpIsAdmin = _tmp_1 != 0;
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _result = new UserEntity(_tmpUserId,_tmpUsername,_tmpFullname,_tmpEmail,_tmpPfpUrl,_tmpIsPremium,_tmpPremiumExpiration,_tmpIsAdmin,_tmpStatus,_tmpCreatedAt,_tmpLastUpdated);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getUserByEmail(final String email,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE email = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, email);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfFullname = CursorUtil.getColumnIndexOrThrow(_cursor, "fullname");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPfpUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "pfpUrl");
          final int _cursorIndexOfIsPremium = CursorUtil.getColumnIndexOrThrow(_cursor, "isPremium");
          final int _cursorIndexOfPremiumExpiration = CursorUtil.getColumnIndexOrThrow(_cursor, "premiumExpiration");
          final int _cursorIndexOfIsAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdmin");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpFullname;
            _tmpFullname = _cursor.getString(_cursorIndexOfFullname);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPfpUrl;
            if (_cursor.isNull(_cursorIndexOfPfpUrl)) {
              _tmpPfpUrl = null;
            } else {
              _tmpPfpUrl = _cursor.getString(_cursorIndexOfPfpUrl);
            }
            final boolean _tmpIsPremium;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPremium);
            _tmpIsPremium = _tmp != 0;
            final String _tmpPremiumExpiration;
            if (_cursor.isNull(_cursorIndexOfPremiumExpiration)) {
              _tmpPremiumExpiration = null;
            } else {
              _tmpPremiumExpiration = _cursor.getString(_cursorIndexOfPremiumExpiration);
            }
            final boolean _tmpIsAdmin;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsAdmin);
            _tmpIsAdmin = _tmp_1 != 0;
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _result = new UserEntity(_tmpUserId,_tmpUsername,_tmpFullname,_tmpEmail,_tmpPfpUrl,_tmpIsPremium,_tmpPremiumExpiration,_tmpIsAdmin,_tmpStatus,_tmpCreatedAt,_tmpLastUpdated);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getUserByUsername(final String username,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE username = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, username);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfFullname = CursorUtil.getColumnIndexOrThrow(_cursor, "fullname");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPfpUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "pfpUrl");
          final int _cursorIndexOfIsPremium = CursorUtil.getColumnIndexOrThrow(_cursor, "isPremium");
          final int _cursorIndexOfPremiumExpiration = CursorUtil.getColumnIndexOrThrow(_cursor, "premiumExpiration");
          final int _cursorIndexOfIsAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdmin");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpFullname;
            _tmpFullname = _cursor.getString(_cursorIndexOfFullname);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPfpUrl;
            if (_cursor.isNull(_cursorIndexOfPfpUrl)) {
              _tmpPfpUrl = null;
            } else {
              _tmpPfpUrl = _cursor.getString(_cursorIndexOfPfpUrl);
            }
            final boolean _tmpIsPremium;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPremium);
            _tmpIsPremium = _tmp != 0;
            final String _tmpPremiumExpiration;
            if (_cursor.isNull(_cursorIndexOfPremiumExpiration)) {
              _tmpPremiumExpiration = null;
            } else {
              _tmpPremiumExpiration = _cursor.getString(_cursorIndexOfPremiumExpiration);
            }
            final boolean _tmpIsAdmin;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsAdmin);
            _tmpIsAdmin = _tmp_1 != 0;
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _result = new UserEntity(_tmpUserId,_tmpUsername,_tmpFullname,_tmpEmail,_tmpPfpUrl,_tmpIsPremium,_tmpPremiumExpiration,_tmpIsAdmin,_tmpStatus,_tmpCreatedAt,_tmpLastUpdated);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllUsers(final Continuation<? super List<UserEntity>> $completion) {
    final String _sql = "SELECT * FROM users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserEntity>>() {
      @Override
      @NonNull
      public List<UserEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfFullname = CursorUtil.getColumnIndexOrThrow(_cursor, "fullname");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPfpUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "pfpUrl");
          final int _cursorIndexOfIsPremium = CursorUtil.getColumnIndexOrThrow(_cursor, "isPremium");
          final int _cursorIndexOfPremiumExpiration = CursorUtil.getColumnIndexOrThrow(_cursor, "premiumExpiration");
          final int _cursorIndexOfIsAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdmin");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserEntity _item;
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpFullname;
            _tmpFullname = _cursor.getString(_cursorIndexOfFullname);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPfpUrl;
            if (_cursor.isNull(_cursorIndexOfPfpUrl)) {
              _tmpPfpUrl = null;
            } else {
              _tmpPfpUrl = _cursor.getString(_cursorIndexOfPfpUrl);
            }
            final boolean _tmpIsPremium;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPremium);
            _tmpIsPremium = _tmp != 0;
            final String _tmpPremiumExpiration;
            if (_cursor.isNull(_cursorIndexOfPremiumExpiration)) {
              _tmpPremiumExpiration = null;
            } else {
              _tmpPremiumExpiration = _cursor.getString(_cursorIndexOfPremiumExpiration);
            }
            final boolean _tmpIsAdmin;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsAdmin);
            _tmpIsAdmin = _tmp_1 != 0;
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item = new UserEntity(_tmpUserId,_tmpUsername,_tmpFullname,_tmpEmail,_tmpPfpUrl,_tmpIsPremium,_tmpPremiumExpiration,_tmpIsAdmin,_tmpStatus,_tmpCreatedAt,_tmpLastUpdated);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<UserEntity>> getAllUsersFlow() {
    final String _sql = "SELECT * FROM users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"users"}, new Callable<List<UserEntity>>() {
      @Override
      @NonNull
      public List<UserEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfFullname = CursorUtil.getColumnIndexOrThrow(_cursor, "fullname");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPfpUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "pfpUrl");
          final int _cursorIndexOfIsPremium = CursorUtil.getColumnIndexOrThrow(_cursor, "isPremium");
          final int _cursorIndexOfPremiumExpiration = CursorUtil.getColumnIndexOrThrow(_cursor, "premiumExpiration");
          final int _cursorIndexOfIsAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "isAdmin");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserEntity _item;
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpFullname;
            _tmpFullname = _cursor.getString(_cursorIndexOfFullname);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPfpUrl;
            if (_cursor.isNull(_cursorIndexOfPfpUrl)) {
              _tmpPfpUrl = null;
            } else {
              _tmpPfpUrl = _cursor.getString(_cursorIndexOfPfpUrl);
            }
            final boolean _tmpIsPremium;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPremium);
            _tmpIsPremium = _tmp != 0;
            final String _tmpPremiumExpiration;
            if (_cursor.isNull(_cursorIndexOfPremiumExpiration)) {
              _tmpPremiumExpiration = null;
            } else {
              _tmpPremiumExpiration = _cursor.getString(_cursorIndexOfPremiumExpiration);
            }
            final boolean _tmpIsAdmin;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsAdmin);
            _tmpIsAdmin = _tmp_1 != 0;
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item = new UserEntity(_tmpUserId,_tmpUsername,_tmpFullname,_tmpEmail,_tmpPfpUrl,_tmpIsPremium,_tmpPremiumExpiration,_tmpIsAdmin,_tmpStatus,_tmpCreatedAt,_tmpLastUpdated);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
