package com.xxxx.server.controller;


import com.xxxx.server.mapper.JoblevelMapper;
import com.xxxx.server.pojo.Joblevel;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IJoblevelService;
import com.xxxx.server.service.impl.JoblevelServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation("获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getJobLevels() {
        return joblevelService.list();
    }

    @ApiOperation("添加职称")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel) {
        List<Joblevel> list = joblevelService.list();
        for (Joblevel joblevel1 : list) {
            if (joblevel.getName().equals(joblevel1.getName()) && (joblevel.getTitleLevel()).equals(joblevel1.getTitleLevel())) {
                return RespBean.error("职位已有, 添加失败!");
            }
        }
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation("修改职称")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel joblevel) {
        List<Joblevel> list = joblevelService.list();
        for (Joblevel joblevel1 : list) {
            if (joblevel.getName().equals(joblevel1.getName()) && (joblevel.getTitleLevel()).equals(joblevel1.getTitleLevel())) {
                return RespBean.error("职位已有, 修改失败!");
            }
        }
        // 根据id修改职称
       if (joblevelService.updateById(joblevel)) {
           return RespBean.success("修改成功!");
       }
        return RespBean.error("修改失败!");
    }

    @ApiOperation("删除职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/")
    public RespBean deleteJobLevels(Integer [] ids) {

        // 将数组转换
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
