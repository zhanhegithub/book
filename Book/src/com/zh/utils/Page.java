package com.zh.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 @param <T> 是具体的模块的javaBean 类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Page<T> {

    public static final Integer PAGE_SIZE = 4;
    // 当前页码
    private Integer pageNo;
    // 总页码
    private Integer pageTotal;
    // 当前页显示数量
    private Integer pageSize = PAGE_SIZE;
    // 总记录数
    private Integer pageTotalCount;
    // 当前页数据
    private List<T> items;
    // 分页条的请求地址
    private String url;

    public void setPageNo(Integer pageNo) {
        /* 数据边界的有效检查 */
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

}
