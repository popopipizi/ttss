package com.project.demo.controller;

import com.project.demo.entity.OpeningTableInformation;
import com.project.demo.service.OpeningTableInformationService;
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
 * 开桌信息：(OpeningTableInformation)表控制层
 *
 */
@RestController
@RequestMapping("/opening_table_information")
public class OpeningTableInformationController extends BaseController<OpeningTableInformation, OpeningTableInformationService> {

    /**
     * 开桌信息对象
     */
    @Autowired
    public OpeningTableInformationController(OpeningTableInformationService service) {
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
