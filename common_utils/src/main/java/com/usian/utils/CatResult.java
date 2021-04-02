package com.usian.utils;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName:CatResult
 * Author:maodi
 * CreateTime:2021/03/16/11:34
 */
public class CatResult implements Serializable {
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
