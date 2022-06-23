package com.lyx.autoperm.utils;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyx.autoperm.constant.PageConstant;
import com.lyx.autoperm.entity.User;


import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年06月21日 18:25
 */
public class PageUtils {

    public static Page buildPage(Map<String,Object> params){
        return new Page<User>(Integer.parseInt((String) params.get(PageConstant.PAGENUM)),(Integer.parseInt((String) params.get(PageConstant.PAGESIZE))));
    }
}
