package com.example.notebook.controller.flowable;

//import org.flowable.bpmn.model.BpmnModel;
//import org.flowable.engine.*;
//import org.flowable.engine.runtime.Execution;
//import org.flowable.engine.runtime.ProcessInstance;
//import org.flowable.image.ProcessDiagramGenerator;
//import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhaohongliang
 * @description
 * @date 18:37 2018/7/5
 */
@Controller
@RequestMapping(value = "expense")
public class FlowableController {

//    @Autowired
//    private RuntimeService runtimeService;
//
//    @Autowired
//    private TaskService taskService;
//
//    @Autowired
//    private RepositoryService repositoryService;
//
//    @Autowired
//    private ProcessEngine processEngine;
//
//    @RequestMapping(value = "add")
//    @ResponseBody
//    public String addExense(String userId, Integer money, String descption) {
//        // 启动流程
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("taskUser", userId);
//        map.put("money", money);
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Am", map);
//        return "提交成功.流程ID为：" + processInstance.getId();
//    }
//
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    public Object list(String userId) {
//        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
//        for (Task task : tasks) {
//            System.out.println(task.toString());
//        }
//        return tasks.toArray().toString();
//    }
//
//
//    /**
//     * 批准
//     *
//     * @param taskId 任务ID
//     */
//    @RequestMapping(value = "apply")
//    @ResponseBody
//    public String apply(String taskId) {
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        if (task == null) {
//            throw new RuntimeException("流程不存在");
//        }
//        //通过审核
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("outcome", "通过");
//        taskService.complete(taskId, map);
//        return "processed ok!";
//    }
//
//    /**
//     * 拒绝
//     */
//    @ResponseBody
//    @RequestMapping(value = "reject")
//    public String reject(String taskId) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("outcome", "驳回");
//        taskService.complete(taskId, map);
//        return "reject";
//    }
//
//    /**
//     * 生成流程图
//     *
//     * @param processId 任务ID
//     */
//    @RequestMapping(value = "processDiagram")
//    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
//        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
//
//        //流程走完的不显示图
//        if (pi == null) {
//            return;
//        }
//        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
//        String InstanceId = task.getProcessInstanceId();
//        List<Execution> executions = runtimeService
//                .createExecutionQuery()
//                .processInstanceId(InstanceId)
//                .list();
//
//        //得到正在执行的Activity的Id
//        List<String> activityIds = new ArrayList<>();
//        List<String> flows = new ArrayList<>();
//        for (Execution exe : executions) {
//            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
//            activityIds.addAll(ids);
//        }
//
//        //获取流程图
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
//        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
//        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
//        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0);
//        OutputStream out = null;
//        byte[] buf = new byte[1024];
//        int legth = 0;
//        try {
//            out = httpServletResponse.getOutputStream();
//            while ((legth = in.read(buf)) != -1) {
//                out.write(buf, 0, legth);
//            }
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//            if (out != null) {
//                out.close();
//            }
//        }
//    }

}
