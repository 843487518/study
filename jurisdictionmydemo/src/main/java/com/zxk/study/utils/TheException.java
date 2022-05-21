package com.zxk.study.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Zhouxinkai
 * @Description:
 * @date 2022/4/24  9:08
 */
@Data
@ToString

public class TheException {
    @JsonProperty
    private Integer code;
    @JsonProperty
    private String message;
    @JsonProperty
    private String url;
}
