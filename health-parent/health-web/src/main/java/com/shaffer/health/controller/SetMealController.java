package com.shaffer.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shaffer.health.constant.MessageConstant;
import com.shaffer.health.entity.PageResult;
import com.shaffer.health.entity.QueryPageBean;
import com.shaffer.health.entity.Result;
import com.shaffer.health.pojo.SetMeal;
import com.shaffer.health.service.SetMealService;
import com.shaffer.health.utils.QiNiuUtil;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: health
 * @Date: 2021/4/15 18:59
 * @Author: Shaffer
 * @Description:
 */
@RestController
@RequestMapping("/set-meal")
public class SetMealController {

    @Reference
    private SetMealService setMealService;

    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        //- 获取原有图片名称，截取到后缀名
        String originalFilename = imgFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //- 生成唯一文件名，拼接后缀名
        String filename = UUID.randomUUID() + extension;
        //- 调用七牛上传文件
        try {
            QiNiuUtil.uploadViaByte(imgFile.getBytes(), filename);

            Map<String, String> map = new HashMap<>(2);
            map.put("imgName", filename);
            map.put("domain", QiNiuUtil.DOMAIN);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
    }

    @PostMapping("/add")
    public Result add(@RequestParam Integer[] checkgroupIds, @RequestBody SetMeal setMeal) {
        setMealService.add(setMeal, checkgroupIds);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<SetMeal> pageResult = setMealService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, pageResult);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam("id") Integer id) {
        // 根据id查询套餐数据
        SetMeal setMeal = setMealService.findById(id);

        // 获取图片存储域名
        String domain = QiNiuUtil.DOMAIN;

        // 封装返回数据
        Map<String, Object> map = new HashMap<>(2);
        map.put("setmeal", setMeal);
        map.put("domain", domain);

        // 返回数据
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, map);
    }

    @GetMapping("/findCheckgroupIdsBySetmealId")
    public Result findCheckgroupIdsBySetmealId(@RequestParam("id") Integer id) {
        List<Integer> list = setMealService.findCheckgroupIdsBySetmealId(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, list);
    }

    @PostMapping("/update")
    public Result update(@RequestParam Integer[] checkgroupIds, @RequestBody SetMeal setMeal) {
        setMealService.update(setMeal, checkgroupIds);
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    @PostMapping("/deleteById")
    public Result deleteById(Integer id) {
        setMealService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }
}
