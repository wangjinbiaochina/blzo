package com.jdkhome.blzo.generator.dao;

import com.jdkhome.blzo.generator.model.GroupMenu;
import com.jdkhome.blzo.generator.model.GroupMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMenuMapper {
    long countByExample(GroupMenuExample example);

    int deleteByExample(GroupMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupMenu record);

    int insertSelective(GroupMenu record);

    List<GroupMenu> selectByExample(GroupMenuExample example);

    GroupMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupMenu record, @Param("example") GroupMenuExample example);

    int updateByExample(@Param("record") GroupMenu record, @Param("example") GroupMenuExample example);

    int updateByPrimaryKeySelective(GroupMenu record);

    int updateByPrimaryKey(GroupMenu record);
}