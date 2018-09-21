package com.pro.code.plugin.mapper;

import com.pro.code.plugin.entity.Columns;

public interface ColumnsMapper {
    int deleteByPrimaryKey(String columnsId);

    int insert(Columns record);

    int insertSelective(Columns record);

    Columns selectByPrimaryKey(String columnsId);

    int updateByPrimaryKeySelective(Columns record);

    int updateByPrimaryKey(Columns record);
}