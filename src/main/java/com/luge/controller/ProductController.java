package com.luge.controller;

import com.github.pagehelper.PageInfo;
import com.luge.domain.Product;
import com.luge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有产品信息(未分页)
     * @return
     * @throws Exception
     */
    /*@RequestMapping(path = "/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Product> products = productService.findAll();
        mav.addObject("productList", products);
        mav.setViewName("product-list");
        return mav;
    }*/

    /**
     * 分页查询所有产品信息
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "currentPage", required = true, defaultValue = "1") int currentPage,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "4") int pageSize) throws Exception{
        ModelAndView mav = new ModelAndView();
        List<Product> productList = productService.findAll(currentPage, pageSize);
        PageInfo pageInfo = new PageInfo(productList);
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("product-page-list");
        return mav;
    }

    /**
     * 保存新产品
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

    /**
     * 删除选中的产品
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/delete.do")
    public String delete(HttpServletRequest request) throws Exception {
        // 获取选中框的id数组
        String[] ids = request.getParameterValues("ids");

        productService.delete(ids);
        return "redirect:findAll.do";
    }
}
