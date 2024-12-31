package com.sky.controller.admin;

import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags="套餐管理")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    //分页查询
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分页查询{}",setmealPageQueryDTO);
        PageResult pageResult=setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    //新增套餐
    @PostMapping
    @ApiOperation("新增套餐")
    public Result add(@RequestBody SetmealVO setmealVO) {
        log.info("新增套餐{}",setmealVO);
        setmealService.insert(setmealVO);
        return Result.success();
    }

    //启售或者停售
    @PostMapping("/status/{status}")
    @ApiOperation("启售或者停售")
    public Result status(@PathVariable Integer status,Long id) {
        log.info("启售或者停售{},{}",status,id);
        setmealService.startOrStop(status,id);
        return Result.success();
    }

    //根据ID查询套餐及关联表
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询套餐及关联表")
    public Result<SetmealVO> queryById(@PathVariable Long id) {
        log.info("根据ID查询套餐及关联表{}",id);
        SetmealVO setmealVO=setmealService.getById(id);
        return Result.success(setmealVO);
    }

    //修改套餐
    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealVO setmealVO) {
        log.info("修改套餐{}",setmealVO);
        setmealService.update(setmealVO);
        return Result.success();
    }

    //删除套餐
    @DeleteMapping
    @ApiOperation("删除套餐")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("删除套餐{}",ids);
        setmealService.deleteByIds(ids);
        return Result.success();
    }
}
