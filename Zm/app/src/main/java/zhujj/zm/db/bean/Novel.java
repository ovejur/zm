package zhujj.zm.db.bean;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

/**
 * 作者：朱建晶 on 2018/3/4 15:49
 * 邮箱：344951059@qq.com
 */

@Entity
public class Novel {

    static final long serialVersionUID = 12L;

    @Id(autoincrement = true)
    private Long id;

    private Long uid;

    @Unique
    private String name;

    @Property(nameInDb = "author")
    private String author; // 作者

    @Property(nameInDb = "url")
    private String url; // 小说的网址

    @Property(nameInDb = "novelType")
    private String novelType; // 小说类别

    @Property(nameInDb = "creatTime")
    private String creatTime; // 创建时间

    @Property(nameInDb = "updateTime")
    private String updateTime; // 更新时间

    @Property(nameInDb = "introduce")
    private String introduce; // 简介

    @Property(nameInDb = "img")
    private String img; // 封面地址

    @Property(nameInDb = "count")
    private Long count; // 总字数

    @Property(nameInDb = "chapters")
    private Long chapters; // 总章节数

    @Property(nameInDb = "chapters")
    private Long chapterCache; // 缓存章节数

    @Property(nameInDb = "status")
    private int status;  // 状态  0更新中 1完本

    @Property(nameInDb = "novelNewest")
    private String novelNewest; // 小说最新章节地址

    @ToMany(referencedJoinProperty = "nid")
    private List<Chapter> chapterobjs;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 318437044)
    private transient NovelDao myDao;

    @Generated(hash = 820180915)
    public Novel(Long id, Long uid, String name, String author, String url, String novelType,
            String creatTime, String updateTime, String introduce, String img, Long count,
            Long chapters, Long chapterCache, int status, String novelNewest) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.author = author;
        this.url = url;
        this.novelType = novelType;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
        this.introduce = introduce;
        this.img = img;
        this.count = count;
        this.chapters = chapters;
        this.chapterCache = chapterCache;
        this.status = status;
        this.novelNewest = novelNewest;
    }

    @Generated(hash = 1747797347)
    public Novel() {
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNovelType() {
        return this.novelType;
    }

    public void setNovelType(String novelType) {
        this.novelType = novelType;
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

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getChapters() {
        return this.chapters;
    }

    public void setChapters(Long chapters) {
        this.chapters = chapters;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNovelNewest() {
        return this.novelNewest;
    }

    public void setNovelNewest(String novelNewest) {
        this.novelNewest = novelNewest;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1375075762)
    public List<Chapter> getChapterobjs() {
        if (chapterobjs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChapterDao targetDao = daoSession.getChapterDao();
            List<Chapter> chapterobjsNew = targetDao._queryNovel_Chapterobjs(id);
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getChapterCache() {
        return this.chapterCache;
    }

    public void setChapterCache(Long chapterCache) {
        this.chapterCache = chapterCache;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1285671335)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getNovelDao() : null;
    }



}
