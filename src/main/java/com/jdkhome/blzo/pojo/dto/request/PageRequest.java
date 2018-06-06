package com.jdkhome.blzo.pojo.dto.request;

/**
 * Created by zzhoo8 on 2017/9/4.
 */
public class PageRequest {

    private Integer page;
    private Integer size;

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public PageRequest() {
        // 默认获取第一页的20个数据
        this.page = 1;
        this.size = 20;
    }

    public PageRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
