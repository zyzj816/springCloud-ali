package org.example.controller;

import org.example.service.StorageTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storageTbl")
public class StorageTblController {
    @Autowired
    private StorageTblService storageTblService;

    @GetMapping("deduct")
    public String deduct(String commodityCode, int count){
        System.out.println("进入库存扣减服务");
        storageTblService.deduct(commodityCode, count);
        return "库存扣减成功";
    }

    @GetMapping("cs")
    public String cs(){
        return "库存扣减成功";
    }
}
