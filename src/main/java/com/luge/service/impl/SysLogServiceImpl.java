package com.luge.service.impl;

import com.github.pagehelper.PageHelper;
import com.luge.dao.SysLogDao;
import com.luge.domain.SysLog;
import com.luge.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysLogService")
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    /**
     * 保存日志信息
     */
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    /**
     * 查询日志信息
     * @return
     */
    @Override
    public List<SysLog> findAll(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return sysLogDao.findAll(currentPage, pageSize);
    }
}
