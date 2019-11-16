package com.luge.dao;

import com.luge.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogDao {
    /**
     * 保存日志信息
     */
    @Insert("insert into sysLog (visitTime, username, ip, url, executionTime, method) values (#{visitTime}, #{username}, #{ip}, #{url}, #{executionTime}, #{method})")
    void save(SysLog sysLog);

    /**
     * 查询日志信息
     * @return
     */
    @Select("select * from sysLog")
    List<SysLog> findAll(Integer currentPage, Integer pageSize);
}
