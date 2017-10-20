package com.rumofuture.moon.domain;

import java.io.Serializable;

/**
 * Created by WangZhenqi on 2017/09/29.
 */

public class Notification implements Serializable {

    private Integer id;
    private User notifier;
    private User target;
    private String content;
    private Boolean isChecked;
    private Integer type;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getNotifier() {
        return notifier;
    }

    public void setNotifier(User notifier) {
        this.notifier = notifier;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notifier=" + notifier +
                ", target=" + target +
                ", content='" + content + '\'' +
                ", isChecked=" + isChecked +
                ", type=" + type +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
