package com.mydecoration.decorationmall.dao.mapper;

import com.mydecoration.decorationmall.dao.common.Query;
import com.mydecoration.decorationmall.dao.entity.TestDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestDAO extends MyBatisBaseDao<TestDO, Long> {

    List<TestDO> selectByAnyKey(Query query);

    Long selectCountByAnyKey(Query query);
}
