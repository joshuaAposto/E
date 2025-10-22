package com.sharebooster.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.sharebooster.app.data.local.dao.ShareLogDao;
import com.sharebooster.app.data.local.dao.ShareLogDao_Impl;
import com.sharebooster.app.data.local.dao.ShareSessionDao;
import com.sharebooster.app.data.local.dao.ShareSessionDao_Impl;
import com.sharebooster.app.data.local.dao.UserDao;
import com.sharebooster.app.data.local.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ShareBoosterDatabase_Impl extends ShareBoosterDatabase {
  private volatile UserDao _userDao;

  private volatile ShareSessionDao _shareSessionDao;

  private volatile ShareLogDao _shareLogDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`userId` TEXT NOT NULL, `username` TEXT NOT NULL, `fullname` TEXT NOT NULL, `email` TEXT NOT NULL, `pfpUrl` TEXT, `isPremium` INTEGER NOT NULL, `premiumExpiration` TEXT, `isAdmin` INTEGER NOT NULL, `status` TEXT NOT NULL, `createdAt` TEXT, `lastUpdated` INTEGER NOT NULL, PRIMARY KEY(`userId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `share_sessions` (`sessionId` TEXT NOT NULL, `userId` TEXT NOT NULL, `url` TEXT NOT NULL, `postId` TEXT, `amount` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `currentCount` INTEGER NOT NULL, `targetCount` INTEGER NOT NULL, `status` TEXT NOT NULL, `startTime` INTEGER NOT NULL, `endTime` INTEGER, `lastUpdated` INTEGER NOT NULL, PRIMARY KEY(`sessionId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `share_logs` (`logId` TEXT NOT NULL, `sessionId` TEXT NOT NULL, `userId` TEXT NOT NULL, `type` TEXT NOT NULL, `message` TEXT, `count` INTEGER, `target` INTEGER, `postId` TEXT, `timestamp` TEXT NOT NULL, `success` INTEGER, `reason` TEXT, PRIMARY KEY(`logId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1a4f9646beee2623691e411ada7c42fe')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `share_sessions`");
        db.execSQL("DROP TABLE IF EXISTS `share_logs`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(11);
        _columnsUsers.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("fullname", new TableInfo.Column("fullname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("pfpUrl", new TableInfo.Column("pfpUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("isPremium", new TableInfo.Column("isPremium", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("premiumExpiration", new TableInfo.Column("premiumExpiration", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("isAdmin", new TableInfo.Column("isAdmin", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("createdAt", new TableInfo.Column("createdAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.sharebooster.app.data.local.entity.UserEntity).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsShareSessions = new HashMap<String, TableInfo.Column>(12);
        _columnsShareSessions.put("sessionId", new TableInfo.Column("sessionId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("postId", new TableInfo.Column("postId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("amount", new TableInfo.Column("amount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("currentCount", new TableInfo.Column("currentCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("targetCount", new TableInfo.Column("targetCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("endTime", new TableInfo.Column("endTime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareSessions.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShareSessions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesShareSessions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoShareSessions = new TableInfo("share_sessions", _columnsShareSessions, _foreignKeysShareSessions, _indicesShareSessions);
        final TableInfo _existingShareSessions = TableInfo.read(db, "share_sessions");
        if (!_infoShareSessions.equals(_existingShareSessions)) {
          return new RoomOpenHelper.ValidationResult(false, "share_sessions(com.sharebooster.app.data.local.entity.ShareSessionEntity).\n"
                  + " Expected:\n" + _infoShareSessions + "\n"
                  + " Found:\n" + _existingShareSessions);
        }
        final HashMap<String, TableInfo.Column> _columnsShareLogs = new HashMap<String, TableInfo.Column>(11);
        _columnsShareLogs.put("logId", new TableInfo.Column("logId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("sessionId", new TableInfo.Column("sessionId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("count", new TableInfo.Column("count", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("target", new TableInfo.Column("target", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("postId", new TableInfo.Column("postId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("timestamp", new TableInfo.Column("timestamp", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("success", new TableInfo.Column("success", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShareLogs.put("reason", new TableInfo.Column("reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShareLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesShareLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoShareLogs = new TableInfo("share_logs", _columnsShareLogs, _foreignKeysShareLogs, _indicesShareLogs);
        final TableInfo _existingShareLogs = TableInfo.read(db, "share_logs");
        if (!_infoShareLogs.equals(_existingShareLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "share_logs(com.sharebooster.app.data.local.entity.ShareLogEntity).\n"
                  + " Expected:\n" + _infoShareLogs + "\n"
                  + " Found:\n" + _existingShareLogs);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1a4f9646beee2623691e411ada7c42fe", "14b4e12620d4063f664a879829e545e5");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","share_sessions","share_logs");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `share_sessions`");
      _db.execSQL("DELETE FROM `share_logs`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ShareSessionDao.class, ShareSessionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ShareLogDao.class, ShareLogDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public ShareSessionDao shareSessionDao() {
    if (_shareSessionDao != null) {
      return _shareSessionDao;
    } else {
      synchronized(this) {
        if(_shareSessionDao == null) {
          _shareSessionDao = new ShareSessionDao_Impl(this);
        }
        return _shareSessionDao;
      }
    }
  }

  @Override
  public ShareLogDao shareLogDao() {
    if (_shareLogDao != null) {
      return _shareLogDao;
    } else {
      synchronized(this) {
        if(_shareLogDao == null) {
          _shareLogDao = new ShareLogDao_Impl(this);
        }
        return _shareLogDao;
      }
    }
  }
}
