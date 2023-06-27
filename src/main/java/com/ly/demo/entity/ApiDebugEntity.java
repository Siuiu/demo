package com.ly.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName t_api_debug
 */
@TableName(value = "t_api_debug")
@Data
@Accessors(chain = true)
public class ApiDebugEntity implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "interface_path")
    private String interfacePath;

    /**
     *
     */
    @TableField(value = "interface_name")
    private String interfaceName;

    /**
     *
     */
    @TableField(value = "request_param")
    private String requestParam;

    /**
     *
     */
    @TableField(value = "request_type")
    private String requestType;

    /**
     *
     */
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    /**
     *
     */
    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ApiDebugEntity other = (ApiDebugEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getInterfacePath() == null ? other.getInterfacePath() == null : this.getInterfacePath().equals(other.getInterfacePath()))
                && (this.getInterfaceName() == null ? other.getInterfaceName() == null : this.getInterfaceName().equals(other.getInterfaceName()))
                && (this.getRequestParam() == null ? other.getRequestParam() == null : this.getRequestParam().equals(other.getRequestParam()))
                && (this.getRequestType() == null ? other.getRequestType() == null : this.getRequestType().equals(other.getRequestType()))
                && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
                && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInterfacePath() == null) ? 0 : getInterfacePath().hashCode());
        result = prime * result + ((getInterfaceName() == null) ? 0 : getInterfaceName().hashCode());
        result = prime * result + ((getRequestParam() == null) ? 0 : getRequestParam().hashCode());
        result = prime * result + ((getRequestType() == null) ? 0 : getRequestType().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", interfacePath=").append(interfacePath);
        sb.append(", interfaceName=").append(interfaceName);
        sb.append(", requestParam=").append(requestParam);
        sb.append(", requestType=").append(requestType);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}