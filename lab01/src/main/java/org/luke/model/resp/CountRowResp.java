package org.luke.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.luke.web.model.BaseResponse;

@Data
@EqualsAndHashCode(callSuper = true)
public class CountRowResp extends BaseResponse {
    private int nRow;
}
