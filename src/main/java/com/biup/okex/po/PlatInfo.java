package com.biup.okex.po;

import java.util.Date;

public class PlatInfo {
    private Integer platId;

    private String platCode;

    private String platName;

    private String platUrl;

    private String accessKey;

    private String secretKey;

    private Byte deleteFlag;

    private Date ctime;

    private Date mtime;

    public Integer getPlatId() {
        return platId;
    }

    public void setPlatId(Integer platId) {
        this.platId = platId;
    }

    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode == null ? null : platCode.trim();
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName == null ? null : platName.trim();
    }

    public String getPlatUrl() {
        return platUrl;
    }

    public void setPlatUrl(String platUrl) {
        this.platUrl = platUrl == null ? null : platUrl.trim();
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey == null ? null : accessKey.trim();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey == null ? null : secretKey.trim();
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}