package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.Vo.DepartmentVo;
import com.xxxx.server.pojo.Department;
import com.xxxx.server.pojo.RespBean;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @param department
     */
    RespBean addDepartment(Department department);

    /**
     * 根据父id添加子部门
     * @param departmentVo
     */
    RespBean addDep(DepartmentVo departmentVo);

    /**
     * 根据id删除子部门
     * @param id
     * @return
     */
    RespBean deleteDep(Integer id);

    /**
     * 根据id删除子部门
     * @param id
     * @return
     */
    RespBean deleteDepartment(Integer id);
}
