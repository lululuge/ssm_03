package com.luge.controller;

import com.github.pagehelper.PageInfo;
import com.luge.domain.SysLog;
import com.luge.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 分页查询日志信息
     * @return
     */
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "currentPage", required = true) Integer currentPage,
                                @RequestParam(name = "pageSize", required = true) Integer pageSize) {
        List<SysLog> sysLogList = sysLogService.findAll(currentPage, pageSize);
        PageInfo pageInfo = new PageInfo(sysLogList);
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("sysLog-page-list");
        return mav;
    }
}
