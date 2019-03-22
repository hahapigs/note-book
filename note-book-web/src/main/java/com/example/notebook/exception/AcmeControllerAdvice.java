package com.example.notebook.exception;

import com.example.notebook.exception.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaohongliang
 * @description
 * @date 16:40:00 2019-03-21
 */
@ControllerAdvice(basePackages = {"com.example.notebook"})
@Slf4j
public class AcmeControllerAdvice extends ResponseEntityExceptionHandler {

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new ErrorMessage(status.value(), false,  ex.getMessage(), null), status);
    }

//    /**
//     *  拦截Exception类的异常
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Map<String,Object> exceptionHandler(Exception e){
//        Map<String,Object> result = new HashMap<String,Object>();
//        result.put("respCode", "9999");
//        result.put("respMsg", e.getMessage());
//        //正常开发中，可创建一个统一响应实体，如CommonResp
//        return result;
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("/notebook");
//        return mav;
//    }
//
//    /**
//     * 拦截 CommonException 的异常
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(CommonException.class)
//    @ResponseBody
//    public Map<String,Object> exceptionHandler(CommonException ex){
//        log.info("CommonException：{}({})", ex.getMessage(), ex.getCode());
//        Map<String,Object> result = new HashMap<String,Object>();
//        result.put("respCode", ex.getCode());
//        result.put("respMsg", ex.getMessage());
//        return result;
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public Map<String,Object> handleBindException(MethodArgumentNotValidException ex) {
//        FieldError fieldError = ex.getBindingResult().getFieldError();
//        log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
//        Map<String,Object> result = new HashMap<String,Object>();
//        result.put("respCode", "01002");
//        result.put("respMsg", fieldError.getDefaultMessage());
//        return result;
//    }
//
//
//    @ExceptionHandler(BindException.class)
//    @ResponseBody
//    public Map<String,Object> handleBindException(BindException ex) {
//        //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
//        FieldError fieldError = ex.getBindingResult().getFieldError();
//        log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
//        Map<String,Object> result = new HashMap<String,Object>();
//        result.put("respCode", "01002");
//        result.put("respMsg", fieldError.getDefaultMessage());
//        return result;
//    }

}
