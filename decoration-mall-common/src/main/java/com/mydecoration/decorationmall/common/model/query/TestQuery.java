package com.mydecoration.decorationmall.common.model.query;

import com.mydecoration.decorationmall.common.model.PageQuery;
import com.mydecoration.decorationmall.common.model.ReadableQueryEntity;
import lombok.*;

/**
 * @author chaibing
 * @Description
 * @create 2022/1/27 13:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestQuery extends PageQuery implements ReadableQueryEntity {
    //审批id
    private Long id;
    private Long groupConfigId;
    private String instanceCode;

}
