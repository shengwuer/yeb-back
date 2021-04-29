package com.xxxx.server.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.server.Vo.DepartmentVo;
import com.xxxx.server.mapper.DepartmentMapper;
import com.xxxx.server.pojo.Department;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments() {
       return departmentService.getAllDepartments();
    }

//    @ApiOperation(value = "添加部门")
//    @PostMapping("/")
//    public RespBean addDepartment(@RequestBody Department department) {
//
//       return departmentService.addDepartment(department);
//    }

    @ApiOperation(value = "添加部门1")
    @PostMapping("/")
    public RespBean addDep(@RequestBody DepartmentVo departmentVo) {

        return departmentService.addDep(departmentVo);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDep(@PathVariable Integer id) {
        return departmentService.deleteDep(id);
    }

//    @ApiOperation(value = "删除部门1")
//    @DeleteMapping("/{id}")
//    public RespBean deleteDep(@PathVariable Integer id) {
//        return departmentService.deleteDep(id);
//    }
//
}
