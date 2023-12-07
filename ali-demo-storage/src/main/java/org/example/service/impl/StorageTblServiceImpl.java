package org.example.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import io.seata.core.context.RootContext;
import org.example.dao.StorageTblDao;
import org.example.pojo.StorageTblPojo;

import org.example.pojo.table.StorageTblPojoTableDef;
import org.example.service.StorageTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageTblServiceImpl implements StorageTblService {

    @Autowired
    private StorageTblDao storageTblDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        System.out.println("事务id---------------------->" + RootContext.getXID());
        StorageTblPojo storageTblPojo = storageTblDao.selectOneByQuery(QueryWrapper.create().where(StorageTblPojoTableDef.STORAGE_TBL_POJO.COMMODITY_CODE.eq(commodityCode)));

        if (storageTblPojo == null){
            throw new RuntimeException("storageTbl is null");
        }

        // 这里先不考虑超卖的情况
        storageTblPojo.setCount(storageTblPojo.getCount() - count);
        storageTblDao.update(storageTblPojo);
    }

}
