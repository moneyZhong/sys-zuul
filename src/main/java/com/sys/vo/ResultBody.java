package com.sys.vo;

import com.sys.enums.BaseErrorInfoInterface;
import com.sys.enums.CommErrEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回的公共结构
 * {
 *         "code": 网关状态码,
 *         "msg": 网关的错误信息,
 *         "sub_code": 服务状态码,
 *         "sub_msg": 业务的错误信息,
 *         "data": {
 *                   }
 * }
 * @author zq
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultBody {
    private String code;
    private String msg;
    private Object data;
    /**
     * 成功
     *
     * @return
     */
    public static ResultBody success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResultBody success(Object data) {
        ResultBody rb = new ResultBody();
        rb.setCode(CommErrEnum.SUCCESS.getResultCode());
        rb.setMsg(CommErrEnum.SUCCESS.getResultMsg());
        rb.setData(data);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(BaseErrorInfoInterface errorInfo) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getResultCode());
        rb.setMsg(errorInfo.getResultMsg());
        rb.setData(null);
        return rb;
    }
    /**
     * 失败
     */
    public static ResultBody error(String code, String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMsg(message);
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error( String message) {
        ResultBody rb = new ResultBody();
        rb.setCode("-1");
        rb.setMsg(message);
        rb.setData(null);
        return rb;
    }
}
