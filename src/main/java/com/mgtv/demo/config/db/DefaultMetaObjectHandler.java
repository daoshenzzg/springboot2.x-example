package com.mgtv.demo.config.db;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author daoshenzzg@163.com
 * @date 2019-09-04 17:08
 */
@Component
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", (int) System.currentTimeMillis() / 1000, metaObject);
        this.setFieldValByName("updateTime", (int) System.currentTimeMillis() / 1000, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", (int) System.currentTimeMillis() / 1000, metaObject);
    }
}
