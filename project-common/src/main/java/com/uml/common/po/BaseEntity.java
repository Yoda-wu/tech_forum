package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * 基类
 *
 * @author wuyuda
 * @date 2022-03-18 17:30
 */
public abstract class BaseEntity implements Serializable, Comparable<BaseEntity> {

    /**
     * 常量字符串
     */
    public static final String ID = "id";
    public static final String CREATE_TIME = "`create_time`";
    public static final String UPDATE_TIME = "`update_time`";
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String TIME_ZONE = "GMT-8";
    public static final String DELETE = "`delete`";

    /**
     * 定义抽象方法toString
     * 强迫子类重写此方法。
     *
     * @return 对象字符串
     */
    @Override
    public abstract String toString();

    /**
     * 重写比较方法
     * 允许子类重写
     *
     * @param entity 比较的对象
     * @return 比较结果
     */
    @Override
    public int compareTo(BaseEntity entity) {
        return id.compareTo(entity.id);
    }

    /**
     * 重写等于运算
     *
     * @param o 比较的对象
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return id.equals(that.id);
    }

    /**
     * 重写哈希码
     *
     * @return 哈希值
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * 主键id
     */
    @TableId(value = ID,type = IdType.AUTO)
    protected Long id;
    /**
     * 版本号
     */
    @Version
    protected int version;
    /**
     * 创建时间
     */
    @TableField(value = CREATE_TIME)
    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(pattern = DATE_PATTERN, timezone = TIME_ZONE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    protected Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = UPDATE_TIME)
    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(pattern = DATE_PATTERN, timezone = TIME_ZONE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    protected Date updateTime;
    /**
     * 逻辑删除
     */
    @TableField(value = DELETE)
    protected boolean delete;

    public BaseEntity() {
    }

    public BaseEntity(Long id, int version, Date createTime, Date updateTime, boolean delete) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delete = delete;
    }

    public final Long getId() {
        return id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

    public final int getVersion() {
        return version;
    }

    public final void setVersion(int version) {
        this.version = version;
    }

    public final Date getCreateTime() {
        return createTime;
    }

    public final void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public final Date getUpdateTime() {
        return updateTime;
    }

    public final void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public final boolean isDelete() {
        return delete;
    }

    public final void setDelete(boolean delete) {
        this.delete = delete;
    }
}
