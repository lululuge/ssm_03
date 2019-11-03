package com.luge.dao;

import com.luge.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
    @Select("select * from member where id = #{id}")
    public Member findById2(int id);
}
