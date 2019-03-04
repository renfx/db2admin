package com.rfxdevelop.db2admin;

import com.rfxdevelop.db2admin.enumeration.ResultCode;
import com.rfxdevelop.db2admin.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler
{
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handler(Exception e){

        Result result = Result.exceptionResult();
        result.setException(getExceptionAllinformation_01(e));
        result.setMsg(e.getMessage()==null? ResultCode.EXCETION.getDescription():e.getMessage());
        e.printStackTrace();
        return result;
    }

    public static String getExceptionAllinformation_01(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
        }
        return ret;
    }
}
