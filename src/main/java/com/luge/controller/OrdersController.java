package com.luge.controller;

import com.github.pagehelper.PageInfo;
import com.luge.domain.Orders;
import com.luge.service.OrdersService;
import com.luge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.IDN;
import java.util.List;

@Controller
@RequestMapping(path = "/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 查询所有订单信息(未分页)
     * @return
     */
    /*@RequestMapping(path = "/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mav.addObject("ordersList", ordersList);
        mav.setViewName("orders-list");
        return mav;
    }*/

    /**
     * 分页查询所有订单信息
     */
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "currentPage", required = true, defaultValue = "1") int currentPage,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "4") int pageSize) {
        ModelAndView mav = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(currentPage, pageSize);
        PageInfo pageInfo = new PageInfo(ordersList);
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("orders-page-list");
        return mav;
    }

    /**
     * 根据id删除订单
     * @param request
     * @return
     */
    @RequestMapping(path = "/delete.do")
    public String delete(HttpServletRequest request) {
        // 获取选中框的id数组
        String[] ids = request.getParameterValues("ids");
        System.out.println(ids[0]);
        ordersService.delete(ids);
        return "redirect:findAll.do";
    }

    /**
     * 根据id查询订单详细信息
     * @return
     */
    @RequestMapping(path = "/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) int id) {
        ModelAndView mav = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mav.addObject("orders", orders);
        mav.setViewName("orders-show");
        return mav;
    }
}
