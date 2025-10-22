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
import com.sharebooster.app.data.local.entity.ShareLogEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class ShareLogDao_Impl implements ShareLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ShareLogEntity> __insertionAdapterOfShareLogEntity;

  private final EntityDeletionOrUpdateAdapter<ShareLogEntity> __deletionAdapterOfShareLogEntity;

  private final EntityDeletionOrUpdateAdapter<ShareLogEntity> __updateAdapterOfShareLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLogById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLogsBySessionId;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLogsByUserId;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllLogs;

  public ShareLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfShareLogEntity = new EntityInsertionAdapter<ShareLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `share_logs` (`logId`,`sessionId`,`userId`,`type`,`message`,`count`,`target`,`postId`,`timestamp`,`success`,`reason`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShareLogEntity entity) {
        statement.bindString(1, entity.getLogId());
        statement.bindString(2, entity.getSessionId());
        statement.bindString(3, entity.getUserId());
        statement.bindString(4, entity.getType());
        if (entity.getMessage() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMessage());
        }
        if (entity.getCount() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getCount());
        }
        if (entity.getTarget() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getTarget());
        }
        if (entity.getPostId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPostId());
        }
        statement.bindString(9, entity.getTimestamp());
        final Integer _tmp = entity.getSuccess() == null ? null : (entity.getSuccess() ? 1 : 0);
        if (_tmp == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, _tmp);
        }
        if (entity.getReason() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getReason());
        }
      }
    };
    this.__deletionAdapterOfShareLogEntity = new EntityDeletionOrUpdateAdapter<ShareLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `share_logs` WHERE `logId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShareLogEntity entity) {
        statement.bindString(1, entity.getLogId());
      }
    };
    this.__updateAdapterOfShareLogEntity = new EntityDeletionOrUpdateAdapter<ShareLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `share_logs` SET `logId` = ?,`sessionId` = ?,`userId` = ?,`type` = ?,`message` = ?,`count` = ?,`target` = ?,`postId` = ?,`timestamp` = ?,`success` = ?,`reason` = ? WHERE `logId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShareLogEntity entity) {
        statement.bindString(1, entity.getLogId());
        statement.bindString(2, entity.getSessionId());
        statement.bindString(3, entity.getUserId());
        statement.bindString(4, entity.getType());
        if (entity.getMessage() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMessage());
        }
        if (entity.getCount() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getCount());
        }
        if (entity.getTarget() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getTarget());
        }
        if (entity.getPostId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPostId());
        }
        statement.bindString(9, entity.getTimestamp());
        final Integer _tmp = entity.getSuccess() == null ? null : (entity.getSuccess() ? 1 : 0);
        if (_tmp == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, _tmp);
        }
        if (entity.getReason() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getReason());
        }
        statement.bindString(12, entity.getLogId());
      }
    };
    this.__preparedStmtOfDeleteLogById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM share_logs WHERE logId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteLogsBySessionId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM share_logs WHERE sessionId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteLogsByUserId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM share_logs WHERE userId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllLogs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM share_logs";
        return _query;
      }
    };
  }

  @Override
  public Object insertLog(final ShareLogEntity log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfShareLogEntity.insert(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertLogs(final List<ShareLogEntity> logs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfShareLogEntity.insert(logs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLog(final ShareLogEntity log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfShareLogEntity.handle(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLog(final ShareLogEntity log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfShareLogEntity.handle(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLogById(final String logId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLogById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, logId);
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
          __preparedStmtOfDeleteLogById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLogsBySessionId(final String sessionId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLogsBySessionId.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, sessionId);
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
          __preparedStmtOfDeleteLogsBySessionId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLogsByUserId(final String userId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLogsByUserId.acquire();
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
          __preparedStmtOfDeleteLogsByUserId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllLogs(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllLogs.acquire();
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
          __preparedStmtOfDeleteAllLogs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getLogById(final String logId,
      final Continuation<? super ShareLogEntity> $completion) {
    final String _sql = "SELECT * FROM share_logs WHERE logId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, logId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ShareLogEntity>() {
      @Override
      @Nullable
      public ShareLogEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "logId");
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfCount = CursorUtil.getColumnIndexOrThrow(_cursor, "count");
          final int _cursorIndexOfTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "target");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfSuccess = CursorUtil.getColumnIndexOrThrow(_cursor, "success");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final ShareLogEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpLogId;
            _tmpLogId = _cursor.getString(_cursorIndexOfLogId);
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final Integer _tmpCount;
            if (_cursor.isNull(_cursorIndexOfCount)) {
              _tmpCount = null;
            } else {
              _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            }
            final Integer _tmpTarget;
            if (_cursor.isNull(_cursorIndexOfTarget)) {
              _tmpTarget = null;
            } else {
              _tmpTarget = _cursor.getInt(_cursorIndexOfTarget);
            }
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final String _tmpTimestamp;
            _tmpTimestamp = _cursor.getString(_cursorIndexOfTimestamp);
            final Boolean _tmpSuccess;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfSuccess)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfSuccess);
            }
            _tmpSuccess = _tmp == null ? null : _tmp != 0;
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            _result = new ShareLogEntity(_tmpLogId,_tmpSessionId,_tmpUserId,_tmpType,_tmpMessage,_tmpCount,_tmpTarget,_tmpPostId,_tmpTimestamp,_tmpSuccess,_tmpReason);
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
  public Object getLogsBySessionId(final String sessionId,
      final Continuation<? super List<ShareLogEntity>> $completion) {
    final String _sql = "SELECT * FROM share_logs WHERE sessionId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, sessionId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ShareLogEntity>>() {
      @Override
      @NonNull
      public List<ShareLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "logId");
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfCount = CursorUtil.getColumnIndexOrThrow(_cursor, "count");
          final int _cursorIndexOfTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "target");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfSuccess = CursorUtil.getColumnIndexOrThrow(_cursor, "success");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final List<ShareLogEntity> _result = new ArrayList<ShareLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShareLogEntity _item;
            final String _tmpLogId;
            _tmpLogId = _cursor.getString(_cursorIndexOfLogId);
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final Integer _tmpCount;
            if (_cursor.isNull(_cursorIndexOfCount)) {
              _tmpCount = null;
            } else {
              _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            }
            final Integer _tmpTarget;
            if (_cursor.isNull(_cursorIndexOfTarget)) {
              _tmpTarget = null;
            } else {
              _tmpTarget = _cursor.getInt(_cursorIndexOfTarget);
            }
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final String _tmpTimestamp;
            _tmpTimestamp = _cursor.getString(_cursorIndexOfTimestamp);
            final Boolean _tmpSuccess;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfSuccess)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfSuccess);
            }
            _tmpSuccess = _tmp == null ? null : _tmp != 0;
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            _item = new ShareLogEntity(_tmpLogId,_tmpSessionId,_tmpUserId,_tmpType,_tmpMessage,_tmpCount,_tmpTarget,_tmpPostId,_tmpTimestamp,_tmpSuccess,_tmpReason);
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
  public Flow<List<ShareLogEntity>> getLogsBySessionIdFlow(final String sessionId) {
    final String _sql = "SELECT * FROM share_logs WHERE sessionId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, sessionId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"share_logs"}, new Callable<List<ShareLogEntity>>() {
      @Override
      @NonNull
      public List<ShareLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "logId");
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfCount = CursorUtil.getColumnIndexOrThrow(_cursor, "count");
          final int _cursorIndexOfTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "target");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfSuccess = CursorUtil.getColumnIndexOrThrow(_cursor, "success");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final List<ShareLogEntity> _result = new ArrayList<ShareLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShareLogEntity _item;
            final String _tmpLogId;
            _tmpLogId = _cursor.getString(_cursorIndexOfLogId);
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final Integer _tmpCount;
            if (_cursor.isNull(_cursorIndexOfCount)) {
              _tmpCount = null;
            } else {
              _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            }
            final Integer _tmpTarget;
            if (_cursor.isNull(_cursorIndexOfTarget)) {
              _tmpTarget = null;
            } else {
              _tmpTarget = _cursor.getInt(_cursorIndexOfTarget);
            }
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final String _tmpTimestamp;
            _tmpTimestamp = _cursor.getString(_cursorIndexOfTimestamp);
            final Boolean _tmpSuccess;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfSuccess)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfSuccess);
            }
            _tmpSuccess = _tmp == null ? null : _tmp != 0;
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            _item = new ShareLogEntity(_tmpLogId,_tmpSessionId,_tmpUserId,_tmpType,_tmpMessage,_tmpCount,_tmpTarget,_tmpPostId,_tmpTimestamp,_tmpSuccess,_tmpReason);
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

  @Override
  public Object getLogsByUserId(final String userId, final int limit,
      final Continuation<? super List<ShareLogEntity>> $completion) {
    final String _sql = "SELECT * FROM share_logs WHERE userId = ? ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ShareLogEntity>>() {
      @Override
      @NonNull
      public List<ShareLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "logId");
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfCount = CursorUtil.getColumnIndexOrThrow(_cursor, "count");
          final int _cursorIndexOfTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "target");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfSuccess = CursorUtil.getColumnIndexOrThrow(_cursor, "success");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final List<ShareLogEntity> _result = new ArrayList<ShareLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShareLogEntity _item;
            final String _tmpLogId;
            _tmpLogId = _cursor.getString(_cursorIndexOfLogId);
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final Integer _tmpCount;
            if (_cursor.isNull(_cursorIndexOfCount)) {
              _tmpCount = null;
            } else {
              _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            }
            final Integer _tmpTarget;
            if (_cursor.isNull(_cursorIndexOfTarget)) {
              _tmpTarget = null;
            } else {
              _tmpTarget = _cursor.getInt(_cursorIndexOfTarget);
            }
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final String _tmpTimestamp;
            _tmpTimestamp = _cursor.getString(_cursorIndexOfTimestamp);
            final Boolean _tmpSuccess;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfSuccess)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfSuccess);
            }
            _tmpSuccess = _tmp == null ? null : _tmp != 0;
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            _item = new ShareLogEntity(_tmpLogId,_tmpSessionId,_tmpUserId,_tmpType,_tmpMessage,_tmpCount,_tmpTarget,_tmpPostId,_tmpTimestamp,_tmpSuccess,_tmpReason);
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
  public Flow<List<ShareLogEntity>> getLogsByUserIdFlow(final String userId, final int limit) {
    final String _sql = "SELECT * FROM share_logs WHERE userId = ? ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"share_logs"}, new Callable<List<ShareLogEntity>>() {
      @Override
      @NonNull
      public List<ShareLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "logId");
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfCount = CursorUtil.getColumnIndexOrThrow(_cursor, "count");
          final int _cursorIndexOfTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "target");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfSuccess = CursorUtil.getColumnIndexOrThrow(_cursor, "success");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final List<ShareLogEntity> _result = new ArrayList<ShareLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShareLogEntity _item;
            final String _tmpLogId;
            _tmpLogId = _cursor.getString(_cursorIndexOfLogId);
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final Integer _tmpCount;
            if (_cursor.isNull(_cursorIndexOfCount)) {
              _tmpCount = null;
            } else {
              _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            }
            final Integer _tmpTarget;
            if (_cursor.isNull(_cursorIndexOfTarget)) {
              _tmpTarget = null;
            } else {
              _tmpTarget = _cursor.getInt(_cursorIndexOfTarget);
            }
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final String _tmpTimestamp;
            _tmpTimestamp = _cursor.getString(_cursorIndexOfTimestamp);
            final Boolean _tmpSuccess;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfSuccess)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfSuccess);
            }
            _tmpSuccess = _tmp == null ? null : _tmp != 0;
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            _item = new ShareLogEntity(_tmpLogId,_tmpSessionId,_tmpUserId,_tmpType,_tmpMessage,_tmpCount,_tmpTarget,_tmpPostId,_tmpTimestamp,_tmpSuccess,_tmpReason);
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
