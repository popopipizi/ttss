package com.project.demo.controller;

import com.project.demo.entity.OrderEvaluation;
import com.project.demo.service.OrderEvaluationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 订单评价：(OrderEvaluation)表控制层
 *
 */
@RestController
@RequestMapping("/order_evaluation")
public class OrderEvaluationController extends BaseController<OrderEvaluation, OrderEvaluationService> {

    /**
     * 订单评价对象
     */
    @Autowired
    public OrderEvaluationController(OrderEvaluationService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
