package com.signs123.baiyunbbs.Util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {


    private List<String> list=new ArrayList<>();  //git test

    /**
     * 开始分页
     * @param list
     * @param pageNum 页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static List startPage(List list, Integer pageNum,Integer pageSize,Integer pageCount) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            System.out.println(11111);
            return null;

        }

        Integer count = list.size(); // 记录总数


        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引
        if (pageNum>pageCount){
            pageNum=pageCount;
        }

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }
}
