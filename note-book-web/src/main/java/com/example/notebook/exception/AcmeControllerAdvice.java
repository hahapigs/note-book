package com.example.notebook.exception;

import com.example.notebook.core.BaseError;
import com.example.notebook.core.CommonErrorCodeEnum;
import com.example.notebook.core.Constant;
import com.example.notebook.result.ResponseResult;
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
 * AcmeControllerAdvice
 * 在web应用中，请求处理时，出现异常是非常常见的。所以当应用出现各类异常时，进行异常的捕获或者二次处理(比如sql异常正常是不能外抛)是非常必要的，
 * 比如在开发对外api服务时，约定了响应的参数格式，如respCode、respMsg，调用方根据错误码进行自己的业务逻辑
 * 显然，默认的异常页是对用户或者调用者而言都是不友好的，所以一般上我们都会进行实现自己业务的异常提示信息
 * springboot中，默认在发送异常时，会跳转值/error请求进行错误的展现，根据不同的Content-Type展现不同的错误结果，如json请求时，直接返回json格式参数
 *
 * @ControllerAdvice：控制器增强，使@ExceptionHandler、@InitBinder、@ModelAttribute注解的方法应用到所有的 @RequestMapping注解的方法。
 *
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

    /**
     * 拦截Exception类的异常
     * @ExceptionHandler：异常处理器，此注解的作用是当出现其定义的异常时进行处理的方法
     *
     * @param request
     * @param throwable
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable throwable) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new ResponseResult(status.value(),false, throwable.getMessage(), null), status);
    }

    /**
     * 拦截Error类的异常
     * @ExceptionHandler：异常处理器，此注解的作用是当出现其定义的异常时进行处理的方法
     *
     * @param ex
     * @return 由于加入了@ResponseBody注解，所以返回json
     */
    @ExceptionHandler(Error.class)
    @ResponseBody
    Map<String, Object> exceptionHandler(Error ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("respCode", 9999);
        result.put("respMsg", ex.getMessage());
        return result;
    }

    /**
     * 拦截自定义CommonException异常
     *
     * @param ex
     * @return 由于加入了@ResponseBody注解，所以返回json
     */
//    @ExceptionHandler(CommonException.class)
//    @ResponseBody
//    Map<String, Object> exceptionHandler(CommonException ex) {
//        log.info("CommonException：{}({})", ex.getCode(), ex.getMessage());
//        Map<String, Object> result = new HashMap<>();
//        result.put("respCode", ex.getCode());
//        result.put("respMsg", ex.getMessage());
//        return result;
//    }

    // 可拦截不同的异常，进行不同的异常提示，比如NoHandlerFoundException、HttpMediaTypeNotSupportedException、AsyncRequestTimeoutException等等


    /**
     * 返回页面时，返回ModelAndView即可
     *
     * 由于工作中都是才有前后端分离开发模式，所以一般上都没有直接返回资源页的需求了，一般上都是返回固定的响应格式，如respCode、respMsg、data，前端通过判断respCode的值进行业务判断，是弹窗还是跳转页面
     *
     * @param request
     * @param ex
     * @return
     */
//    @ExceptionHandler(value = NullPointerException.class)
//    ModelAndView defaultErrorHandler(HttpServletRequest request, NullPointerException ex) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception", ex);
//        modelAndView.addObject("url", request.getRequestURL());
//        modelAndView.setViewName(Constant.DEFAULT_ERROR_VIEW);
//        return modelAndView;
//    }

    /**
     * 大家看到在校验不通过时，返回的异常信息是不友好的，此时可利用统一异常处理，对校验异常进行特殊处理，特别说明下，对于异常处理类
     * 共有以下几种情况(被@RequestBody和@RequestParam注解的请求实体，校验异常类是不同的)
     *
     * @param ex
     * @return
     */
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

    /**
     * 大家看到在校验不通过时，返回的异常信息是不友好的，此时可利用统一异常处理，对校验异常进行特殊处理，特别说明下，对于异常处理类
     * 共有以下几种情况(被@RequestBody和@RequestParam注解的请求实体，校验异常类是不同的)
     *
     * @param ex
     * @return
     */
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
