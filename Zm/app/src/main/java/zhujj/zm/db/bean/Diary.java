package zhujj.zm.db.bean;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * 作者：朱建晶 on 2018/3/4 23:25
 * 邮箱：344951059@qq.com
 */
@Entity
public class Diary {

    static final long serialVersionUID = 23L;

    @Id(autoincrement = true)
    private Long id;

    private Long uid;

    @Property(nameInDb = "content")
    private String content; // 日记内容

    @Property(nameInDb = "time")
    private String time; // 日记时间

    @Property(nameInDb = "weather")
    private String weather; // 日记天气

    @Property(nameInDb = "feelings")
    private String feelings; // 日记心情

    @ToMany(referencedJoinProperty = "did")
    private List<Picture> times;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 21166549)
    private transient DiaryDao myDao;

    @Generated(hash = 333909877)
    public Diary(Long id, Long uid, String content, String time, String weather,
            String feelings) {
        this.id = id;
        this.uid = uid;
        this.content = content;
        this.time = time;
        this.weather = weather;
        this.feelings = feelings;
    }

    @Generated(hash = 112123061)
    public Diary() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getFeelings() {
        return this.feelings;
    }

    public void setFeelings(String feelings) {
        this.feelings = feelings;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2060451263)
    public List<Picture> getTimes() {
        if (times == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PictureDao targetDao = daoSession.getPictureDao();
            List<Picture> timesNew = targetDao._queryDiary_Times(id);
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 629297785)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDiaryDao() : null;
    }
}
