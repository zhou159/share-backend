package com.share.exceptions;

import com.share.result.RestObject;
import com.share.result.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
//表示这个Controller不处理http请求，只处理当其他controller抛出异常时，进行处理。
public class Handler {
    @ExceptionHandler(ShareException.class)  //就是定义处理什么异常
    @ResponseBody
    @ResponseStatus(HttpStatus.OK) //服务器的异常
    public RestObject<String> handlerUserException(Exception e){

        return RestResponse.makeErrRsp(e.getMessage());
    }

    @ExceptionHandler(Exception.class)  //就是定义处理什么异常
    @ResponseBody
    @ResponseStatus(HttpStatus.OK) //服务器的异常
    public RestObject<String> ExceptionHandler(Exception e){

        return RestResponse.makeErrRsp(e.getMessage());
    }

}
