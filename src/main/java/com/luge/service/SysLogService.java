package com.luge.service;

import com.luge.domain.SysLog;

import java.util.List;

public interface SysLogService {
    /**
     * 保存日志信息
     */
    public void save(SysLog sysLog);

    /**
     * 查询日志信息
     * @return
     */
    List<SysLog> findAll(Integer currentPage, Integer pageSize);
}
