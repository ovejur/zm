package zhujj.zm.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：朱建晶 on 2018/2/7 00:13
 * 邮箱：344951059@qq.com
 */
@Entity
public class Time {

    static final long serialVersionUID = 13L;

    @Id(autoincrement = true)
    private Long id;

    private Long uid;

    @Property(nameInDb = "count")
    private Long count; // 总字数

    @Property(nameInDb = "time")
    private Long time; // 码字的日期

    @Generated(hash = 81562171)
    public Time(Long id, Long uid, Long count, Long time) {
        this.id = id;
        this.uid = uid;
        this.count = count;
        this.time = time;
    }

    @Generated(hash = 37380482)
    public Time() {
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

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
