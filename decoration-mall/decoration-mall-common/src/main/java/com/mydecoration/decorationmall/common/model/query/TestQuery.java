package com.mydecoration.decorationmall.common.model.query;

import com.mydecoration.decorationmall.common.model.PageQuery;
import com.mydecoration.decorationmall.common.model.ReadableQueryEntity;
import lombok.*;
/**
 * @author chaibing
 * @Description
 * @create 2021/12/30 19:56
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestQuery extends PageQuery implements ReadableQueryEntity {
    private Long id;
    private Long groupConfigId;
    private String instanceCode;
}
