package cn.com.kaituo.smart_community.common.exception;

import cn.com.kaituo.smart_community.common.http.MessageCode;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
* Licensed to Homeii LTD. under the terms of the Homeii 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年7月17日        Congcong cc.         1.0            Initial Version

*/

@Data
public class SoftworksException extends RuntimeException {
    private String exceptionStatus;
    private String exceptionMessage;

    public static SoftworksException build(MessageCode messageCode, Throwable cause) {
        return new SoftworksException(messageCode, cause);
    }

    public static SoftworksException build(MessageCode messageCode) {
        return new SoftworksException(messageCode);
    }

    public static SoftworksException build(MessageCode messageCode, String message) {
        return new SoftworksException(messageCode,message);
    }

    public static SoftworksException build(MessageCode messageCode, String message, Throwable cause) {
        return new SoftworksException(messageCode,message,cause);
    }

    public SoftworksResponse toSoftworksResponse() {
        return SoftworksResponse.failure(this.exceptionStatus, this.exceptionMessage);
    }



    public SoftworksException(MessageCode messageCode, Throwable cause) {
        super(messageCode.getMsg(), cause);
        setSmartException(messageCode);
    }

    public SoftworksException(MessageCode messageCode) {
        super(messageCode.getMsg());
        setSmartException(messageCode);
    }

    public SoftworksException(MessageCode messageCode, String message) {
        super(messageCode.getMsg());
        setSmartException(messageCode);
        this.exceptionMessage += StringUtils.isEmpty(message) ? "" : message.trim();
    }

    public SoftworksException(MessageCode messageCode, String message, Throwable cause) {
        super(messageCode.getMsg(), cause);
        setSmartException(messageCode);
        this.exceptionMessage += StringUtils.isEmpty(message) ? "" : message.trim();
    }

    private void setSmartException(MessageCode messageCode) {
        this.exceptionMessage = messageCode.getMsg();
        this.exceptionStatus = messageCode.getCode();
    }
}