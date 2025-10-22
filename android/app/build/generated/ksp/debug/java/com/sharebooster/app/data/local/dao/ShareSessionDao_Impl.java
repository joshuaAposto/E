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
import com.sharebooster.app.data.local.entity.ShareSessionEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
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
public final class ShareSessionDao_Impl implements ShareSessionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ShareSessionEntity> __insertionAdapterOfShareSessionEntity;

  private final EntityDeletionOrUpdateAdapter<ShareSessionEntity> __deletionAdapterOfShareSessionEntity;

  private final EntityDeletionOrUpdateAdapter<ShareSessionEntity> __updateAdapterOfShareSessionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSessionById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSessionsByUserId;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllSessions;

  public ShareSessionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfShareSessionEntity = new EntityInsertionAdapter<ShareSessionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `share_sessions` (`sessionId`,`userId`,`url`,`postId`,`amount`,`interval`,`currentCount`,`targetCount`,`status`,`startTime`,`endTime`,`lastUpdated`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShareSessionEntity entity) {
        statement.bindString(1, entity.getSessionId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getUrl());
        if (entity.getPostId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPostId());
        }
        statement.bindLong(5, entity.getAmount());
        statement.bindLong(6, entity.getInterval());
        statement.bindLong(7, entity.getCurrentCount());
        statement.bindLong(8, entity.getTargetCount());
        statement.bindString(9, entity.getStatus());
        statement.bindLong(10, entity.getStartTime());
        if (entity.getEndTime() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getEndTime());
        }
        statement.bindLong(12, entity.getLastUpdated());
      }
    };
    this.__deletionAdapterOfShareSessionEntity = new EntityDeletionOrUpdateAdapter<ShareSessionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `share_sessions` WHERE `sessionId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShareSessionEntity entity) {
        statement.bindString(1, entity.getSessionId());
      }
    };
    this.__updateAdapterOfShareSessionEntity = new EntityDeletionOrUpdateAdapter<ShareSessionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `share_sessions` SET `sessionId` = ?,`userId` = ?,`url` = ?,`postId` = ?,`amount` = ?,`interval` = ?,`currentCount` = ?,`targetCount` = ?,`status` = ?,`startTime` = ?,`endTime` = ?,`lastUpdated` = ? WHERE `sessionId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShareSessionEntity entity) {
        statement.bindString(1, entity.getSessionId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getUrl());
        if (entity.getPostId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPostId());
        }
        statement.bindLong(5, entity.getAmount());
        statement.bindLong(6, entity.getInterval());
        statement.bindLong(7, entity.getCurrentCount());
        statement.bindLong(8, entity.getTargetCount());
        statement.bindString(9, entity.getStatus());
        statement.bindLong(10, entity.getStartTime());
        if (entity.getEndTime() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getEndTime());
        }
        statement.bindLong(12, entity.getLastUpdated());
        statement.bindString(13, entity.getSessionId());
      }
    };
    this.__preparedStmtOfDeleteSessionById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM share_sessions WHERE sessionId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteSessionsByUserId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM share_sessions WHERE userId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllSessions = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM share_sessions";
        return _query;
      }
    };
  }

  @Override
  public Object insertSession(final ShareSessionEntity session,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfShareSessionEntity.insert(session);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSession(final ShareSessionEntity session,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfShareSessionEntity.handle(session);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSession(final ShareSessionEntity session,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfShareSessionEntity.handle(session);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSessionById(final String sessionId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSessionById.acquire();
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
          __preparedStmtOfDeleteSessionById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSessionsByUserId(final String userId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSessionsByUserId.acquire();
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
          __preparedStmtOfDeleteSessionsByUserId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllSessions(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllSessions.acquire();
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
          __preparedStmtOfDeleteAllSessions.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getSessionById(final String sessionId,
      final Continuation<? super ShareSessionEntity> $completion) {
    final String _sql = "SELECT * FROM share_sessions WHERE sessionId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, sessionId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ShareSessionEntity>() {
      @Override
      @Nullable
      public ShareSessionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfCurrentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentCount");
          final int _cursorIndexOfTargetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetCount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final ShareSessionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpCurrentCount;
            _tmpCurrentCount = _cursor.getInt(_cursorIndexOfCurrentCount);
            final int _tmpTargetCount;
            _tmpTargetCount = _cursor.getInt(_cursorIndexOfTargetCount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final long _tmpStartTime;
            _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
            final Long _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _result = new ShareSessionEntity(_tmpSessionId,_tmpUserId,_tmpUrl,_tmpPostId,_tmpAmount,_tmpInterval,_tmpCurrentCount,_tmpTargetCount,_tmpStatus,_tmpStartTime,_tmpEndTime,_tmpLastUpdated);
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
  public Object getSessionsByUserId(final String userId,
      final Continuation<? super List<ShareSessionEntity>> $completion) {
    final String _sql = "SELECT * FROM share_sessions WHERE userId = ? ORDER BY startTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ShareSessionEntity>>() {
      @Override
      @NonNull
      public List<ShareSessionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfCurrentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentCount");
          final int _cursorIndexOfTargetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetCount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final List<ShareSessionEntity> _result = new ArrayList<ShareSessionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShareSessionEntity _item;
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpCurrentCount;
            _tmpCurrentCount = _cursor.getInt(_cursorIndexOfCurrentCount);
            final int _tmpTargetCount;
            _tmpTargetCount = _cursor.getInt(_cursorIndexOfTargetCount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final long _tmpStartTime;
            _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
            final Long _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item = new ShareSessionEntity(_tmpSessionId,_tmpUserId,_tmpUrl,_tmpPostId,_tmpAmount,_tmpInterval,_tmpCurrentCount,_tmpTargetCount,_tmpStatus,_tmpStartTime,_tmpEndTime,_tmpLastUpdated);
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
  public Flow<List<ShareSessionEntity>> getSessionsByUserIdFlow(final String userId) {
    final String _sql = "SELECT * FROM share_sessions WHERE userId = ? ORDER BY startTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"share_sessions"}, new Callable<List<ShareSessionEntity>>() {
      @Override
      @NonNull
      public List<ShareSessionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfCurrentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentCount");
          final int _cursorIndexOfTargetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetCount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final List<ShareSessionEntity> _result = new ArrayList<ShareSessionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShareSessionEntity _item;
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpCurrentCount;
            _tmpCurrentCount = _cursor.getInt(_cursorIndexOfCurrentCount);
            final int _tmpTargetCount;
            _tmpTargetCount = _cursor.getInt(_cursorIndexOfTargetCount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final long _tmpStartTime;
            _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
            final Long _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item = new ShareSessionEntity(_tmpSessionId,_tmpUserId,_tmpUrl,_tmpPostId,_tmpAmount,_tmpInterval,_tmpCurrentCount,_tmpTargetCount,_tmpStatus,_tmpStartTime,_tmpEndTime,_tmpLastUpdated);
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
  public Object getActiveSession(final String userId,
      final Continuation<? super ShareSessionEntity> $completion) {
    final String _sql = "SELECT * FROM share_sessions WHERE userId = ? AND status = 'running'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ShareSessionEntity>() {
      @Override
      @Nullable
      public ShareSessionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfCurrentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentCount");
          final int _cursorIndexOfTargetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetCount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final ShareSessionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpCurrentCount;
            _tmpCurrentCount = _cursor.getInt(_cursorIndexOfCurrentCount);
            final int _tmpTargetCount;
            _tmpTargetCount = _cursor.getInt(_cursorIndexOfTargetCount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final long _tmpStartTime;
            _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
            final Long _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _result = new ShareSessionEntity(_tmpSessionId,_tmpUserId,_tmpUrl,_tmpPostId,_tmpAmount,_tmpInterval,_tmpCurrentCount,_tmpTargetCount,_tmpStatus,_tmpStartTime,_tmpEndTime,_tmpLastUpdated);
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
  public Flow<ShareSessionEntity> getActiveSessionFlow(final String userId) {
    final String _sql = "SELECT * FROM share_sessions WHERE userId = ? AND status = 'running'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"share_sessions"}, new Callable<ShareSessionEntity>() {
      @Override
      @Nullable
      public ShareSessionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfPostId = CursorUtil.getColumnIndexOrThrow(_cursor, "postId");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfCurrentCount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentCount");
          final int _cursorIndexOfTargetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetCount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final ShareSessionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpSessionId;
            _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpPostId;
            if (_cursor.isNull(_cursorIndexOfPostId)) {
              _tmpPostId = null;
            } else {
              _tmpPostId = _cursor.getString(_cursorIndexOfPostId);
            }
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpCurrentCount;
            _tmpCurrentCount = _cursor.getInt(_cursorIndexOfCurrentCount);
            final int _tmpTargetCount;
            _tmpTargetCount = _cursor.getInt(_cursorIndexOfTargetCount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final long _tmpStartTime;
            _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
            final Long _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
            }
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _result = new ShareSessionEntity(_tmpSessionId,_tmpUserId,_tmpUrl,_tmpPostId,_tmpAmount,_tmpInterval,_tmpCurrentCount,_tmpTargetCount,_tmpStatus,_tmpStartTime,_tmpEndTime,_tmpLastUpdated);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
