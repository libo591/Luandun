package com.boboeye.luandun.model.impl;

import java.util.Date;

/**
 * Created by libo_591 on 15/7/26.
 */
public class SocialMessage {
    public static final int FROM_QQ        =0;
    public static final int FROM_WEIBO     =1;
    public static final int FROM_WEICHAT   =2;
    public static final int FROM_ZHIHU     =3;

    /** 社交平台来源 */
    private int from;
    /** 发送时间 */
    private Date sendTime;
    /** 名称 */
    private String name;
    /** 标志，用于获取用户有哪些小旗子 */
    private int flags;
    /** 消息正文 */
    private String message;
    /** 图片 */
    private String[] images;
    /** 评论数 */
    private int commentCount;
    /** 转发数 */
    private int repostCount;
    /** 赞成数 */
    private int likeCount;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getRepostCount() {
        return repostCount;
    }

    public void setRepostCount(int repostCount) {
        this.repostCount = repostCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
