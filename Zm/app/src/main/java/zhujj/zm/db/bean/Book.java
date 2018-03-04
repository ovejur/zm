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
 * 作者：朱建晶 on 2018/1/22 10:53
 * 邮箱：344951059@qq.com
 */

@Entity
public class Book implements Serializable {
    static final long serialVersionUID = 18L;

    @Id(autoincrement = true)
    private Long id;

    private Long uid;

    @Unique
    private String name;

    @Property(nameInDb = "creatTime")
    private String creatTime; // 创建时间

    @Property(nameInDb = "updateTime")
    private String updateTime; // 更新时间

    @Property(nameInDb = "introduce")
    private String introduce; // 简介

    @Property(nameInDb = "inputTime")
    private Long inputTime; // 码字总时间(单位秒)

    @Property(nameInDb = "img")
    private byte[] img; // 封面

    @Property(nameInDb = "count")
    private Long count; // 总字数

    @Property(nameInDb = "chapters")
    private Long chapters; // 章节数

    @Property(nameInDb = "status")
    private int status;  // 状态  0更新中 1完本

    @ToMany(referencedJoinProperty = "bid")
    private List<Chapter> chapterobjs;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1097957864)
    private transient BookDao myDao;

    @Generated(hash = 481031013)
    public Book(Long id, Long uid, String name, String creatTime, String updateTime,
            String introduce, Long inputTime, byte[] img, Long count, Long chapters,
            int status) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
        this.introduce = introduce;
        this.inputTime = inputTime;
        this.img = img;
        this.count = count;
        this.chapters = chapters;
        this.status = status;
    }

    @Generated(hash = 1839243756)
    public Book() {
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

    public String getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getChapters() {
        return this.chapters;
    }

    public void setChapters(Long chapters) {
        this.chapters = chapters;
    }

    public Long getInputTime() {
        return this.inputTime;
    }

    public void setInputTime(Long inputTime) {
        this.inputTime = inputTime;
    }

    public byte[] getImg() {
        return this.img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 85528432)
    public List<Chapter> getChapterobjs() {
        if (chapterobjs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChapterDao targetDao = daoSession.getChapterDao();
            List<Chapter> chapterobjsNew = targetDao._queryBook_Chapterobjs(id);
            synchronized (this) {
                if (chapterobjs == null) {
                    chapterobjs = chapterobjsNew;
                }
            }
        }
        return chapterobjs;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1159819867)
    public synchronized void resetChapterobjs() {
        chapterobjs = null;
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
    @Generated(hash = 1115456930)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookDao() : null;
    }


}
