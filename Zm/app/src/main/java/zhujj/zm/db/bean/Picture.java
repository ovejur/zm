package zhujj.zm.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：朱建晶 on 2018/3/4 23:31
 * 邮箱：344951059@qq.com
 */
@Entity
public class Picture {

    static final long serialVersionUID = 24L;

    @Id(autoincrement = true)
    private Long id;

    private Long did;

    @Generated(hash = 1054502737)
    public Picture(Long id, Long did) {
        this.id = id;
        this.did = did;
    }

    @Generated(hash = 1602548376)
    public Picture() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDid() {
        return this.did;
    }

    public void setDid(Long did) {
        this.did = did;
    }


}
