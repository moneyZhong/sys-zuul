package com.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhongqian
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_access_info")
public class AccessInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("access_key")
    private String accessKey;

    @TableField("access_secret")
    private String accessSecret;

    @TableField("if_del")
    private Boolean ifDel;

    @TableField("remark")
    private String remark;

    @TableField("version")
    private Long version;

    @TableField("rec_create_time")
    private LocalDateTime recCreateTime;

    @TableField("rec_update_time")
    private LocalDateTime recUpdateTime;


    public static final String ID = "id";

    public static final String ACCESS_KEY = "access_key";

    public static final String ACCESS_SECRET = "access_secret";

    public static final String IF_DEL = "if_del";

    public static final String REMARK = "remark";

    public static final String VERSION = "version";

    public static final String REC_CREATE_TIME = "rec_create_time";

    public static final String REC_UPDATE_TIME = "rec_update_time";

}
