package zhujj.zm.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：朱建晶 on 2018/1/24 16:12
 * 邮箱：344951059@qq.com
 */
@Entity
public class Chapter {
    @Id(autoincrement = true)
    private Long id;

    private Long bid;

    @Generated(hash = 1860565603)
    public Chapter(Long id, Long bid) {
        this.id = id;
        this.bid = bid;
    }

    @Generated(hash = 393170288)
    public Chapter() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBid() {
        return this.bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }
}
