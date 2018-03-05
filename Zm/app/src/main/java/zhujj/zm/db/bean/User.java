package zhujj.zm.db.bean;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：朱建晶 on 2018/1/24 16:01
 * 邮箱：344951059@qq.com
 */
@Entity
public class User implements Serializable {

    static final long serialVersionUID = 14L;

    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String name;

    @Property(nameInDb = "pwd")
    private String pwd;

    @Property(nameInDb = "textSize")
    private int textSize; // 字体大小

    @Property(nameInDb = "textColor")
    private int textColor; // 字体颜色

    @Property(nameInDb = "bgColor")
    private int bgColor; // 背景

    @ToMany(referencedJoinProperty = "uid")
    private List<Book> books; // 写书的书列表

    @ToMany(referencedJoinProperty = "uid")
    private List<Time> times;

    @ToMany(referencedJoinProperty = "uid")
    private List<Novel> novels; // 追书的小说列表

    @ToMany(referencedJoinProperty = "uid")
    private List<Diary> diarys; // 日记列表

    @Property(nameInDb = "updateTime")
    private String updateTime; // 字数更新时间

    @Property(nameInDb = "updateCount")
    private Long updateCount; // 更新总字数

    @Property(nameInDb = "count")
    private Long count; // 总字数

    @Property(nameInDb = "countTime")
    private Long countTime; // 总时间

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;

    @Generated(hash = 1188341098)
    public User(Long id, String name, String pwd, int textSize, int textColor, int bgColor,
            String updateTime, Long updateCount, Long count, Long countTime) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.textSize = textSize;
        this.textColor = textColor;
        this.bgColor = bgColor;
        this.updateTime = updateTime;
        this.updateCount = updateCount;
        this.count = count;
        this.countTime = countTime;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1080278060)
    public List<Book> getBooks() {
        if (books == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BookDao targetDao = daoSession.getBookDao();
            List<Book> booksNew = targetDao._queryUser_Books(id);
            synchronized (this) {
                if (books == null) {
                    books = booksNew;
                }
            }
        }
        return books;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 353255226)
    public synchronized void resetBooks() {
        books = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getUpdateCount() {
        return this.updateCount;
    }

    public void setUpdateCount(Long updateCount) {
        this.updateCount = updateCount;
    }

    public Long getCountTime() {
        return this.countTime;
    }

    public void setCountTime(Long countTime) {
        this.countTime = countTime;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1673383456)
    public List<Time> getTimes() {
        if (times == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TimeDao targetDao = daoSession.getTimeDao();
            List<Time> timesNew = targetDao._queryUser_Times(id);
            synchronized (this) {
                if (times == null) {
                    times = timesNew;
                }
            }
        }
        return times;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1093885576)
    public synchronized void resetTimes() {
        times = null;
    }

    public int getTextSize() {
        return this.textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getBgColor() {
        return this.bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 944430418)
    public List<Novel> getNovels() {
        if (novels == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NovelDao targetDao = daoSession.getNovelDao();
            List<Novel> novelsNew = targetDao._queryUser_Novels(id);
            synchronized (this) {
                if (novels == null) {
                    novels = novelsNew;
                }
            }
        }
        return novels;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 743336670)
    public synchronized void resetNovels() {
        novels = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1016883554)
    public List<Diary> getDiarys() {
        if (diarys == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DiaryDao targetDao = daoSession.getDiaryDao();
            List<Diary> diarysNew = targetDao._queryUser_Diarys(id);
            synchronized (this) {
                if (diarys == null) {
                    diarys = diarysNew;
                }
            }
        }
        return diarys;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1143677858)
    public synchronized void resetDiarys() {
        diarys = null;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}
