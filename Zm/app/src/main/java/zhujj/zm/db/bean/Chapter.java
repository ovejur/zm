package zhujj.zm.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * 作者：朱建晶 on 2018/1/24 16:12
 * 邮箱：344951059@qq.com
 */
@Entity
public class Chapter {
    @Id(autoincrement = true)
    private Long id;

    private Long bid; // 写书的书id

    private Long nid; // 小说id

    private Long uid; // 用户id

    @Property(nameInDb = "name")
    private String name;  // 名称

    @Property(nameInDb = "content")
    private String content; // 内容

    @Property(nameInDb = "creatTime")
    private String creatTime; // 创建时间

    @Property(nameInDb = "updateTime")
    private String updateTime; // 更新时间

    @Property(nameInDb = "count")
    private Long count; // 总字数

    @Property(nameInDb = "countTime")
    private Long countTime; // 总时间

    @Property(nameInDb = "url")
    private String url; // 章节的网络地址


    @Generated(hash = 1689884485)
    public Chapter(Long id, Long bid, Long nid, Long uid, String name,
            String content, String creatTime, String updateTime, Long count,
            Long countTime, String url) {
        this.id = id;
        this.bid = bid;
        this.nid = nid;
        this.uid = uid;
        this.name = name;
        this.content = content;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
        this.count = count;
        this.countTime = countTime;
        this.url = url;
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

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCountTime() {
        return this.countTime;
    }

    public void setCountTime(Long countTime) {
        this.countTime = countTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNid() {
        return this.nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
