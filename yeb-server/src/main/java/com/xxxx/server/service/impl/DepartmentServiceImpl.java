package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.Vo.DepartmentVo;
import com.xxxx.server.mapper.DepartmentMapper;
import com.xxxx.server.pojo.Department;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private IDepartmentService departmentService;

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllDepartments() {

        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @Override
    public RespBean addDepartment(Department department) {
        // 查看父部门存在吗?不存提示不存在
        Department department1 = departmentMapper.selectById(department.getParentId());
        if (department1 == null) {
            return RespBean.error("父部门不存在!");
        }
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
       if(1 == department.getResult()) {
           return RespBean.success("添加成功!", department);
       }
        return RespBean.error("添加失败!");
    }

    @Override
    public RespBean addDep( DepartmentVo departmentVo) {

        Department department = new Department();
        // 查询父部门的depPath值
        Department department1 = departmentMapper.selectById(departmentVo.getParentId());
        String depPath = department1.getDepPath();
        // 输入添加的子部门名字
        department.setName(departmentVo.getName());
        department.setParentId(departmentVo.getParentId());
        department.setId(null);
        department.setEnabled(true);
        department.setResult(1);
        // 如果府部门isParent为false这将其设置为true, 如果如果府部门isParent为true则跳过
        if (!department1.getIsParent()) {
            // 更新父部门信息
            department1.setIsParent(true);
            departmentMapper.updateById(department1);
        }
        // 添加子部门记录
        boolean save = departmentService.save(department);
        department.setDepPath(depPath+"."+department.getId());
        departmentService.updateById(department);
        if(1 == department.getResult()) {
            return RespBean.success("添加成功!", department);
        }
        return RespBean.error("添加失败!");
    }

    @Override
    public RespBean deleteDep(Integer id) {
        // 查询子部门信息
        Department department1 = departmentMapper.selectById(id);
        if(department1 == null) {
                    return RespBean.error("操作失败!");
        }
        // 父信息
        Department department2 = departmentMapper.selectById(department1.getParentId());

        // 查询被删除的部门存在子部门吗?, 存在就不能删除
        List<Integer> parentId1 = departmentService.list(new QueryWrapper<Department>()
                // 所需的字段  父id为department2.getId()的部门
                .eq("parentId", department1.getId()))
                // list本质上查的是MenuRole得对象List<MenuRole> 但是最后返回一个Integer菜单id所以用stream流转了一下拿到菜单id(mid)
                .stream().map(Department::getParentId)
                // 有了菜单id在把它转成list
                .collect(Collectors.toList());
        if (parentId1.size() >0) {
            return RespBean.error("还有子部门存在, 删除失败!");
        }

        if(departmentService.removeById(id)) {
            // 根据父id(department2.getId())查询拥有的子部门
            List<Integer> parentId = departmentService.list(new QueryWrapper<Department>()
                    // 所需的字段  父id为department2.getId()的部门
                    .eq("parentId", department2.getId()))
                    // list本质上查的是MenuRole得对象List<MenuRole> 但是最后返回一个Integer菜单id所以用stream流转了一下拿到菜单id(mid)
                    .stream().map(Department::getParentId)
                    // 有了菜单id在把它转成list
                    .collect(Collectors.toList());
            // 判断父id还有没有子部门
            if (parentId.size() == 0) {
                // 如果父id下没有子部门则将其setIsParent设置为false
                department2.setIsParent(false);
                // 修改父id信息
                departmentMapper.updateById(department2);
            }
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    /**
     * 根据id删除子部门
     * @param id
     * @return
     */
    @Override
    public RespBean deleteDepartment(Integer id) {
        // 判断子部门下还有没有子部门
        List<Integer> departments = departmentMapper.selectList(new QueryWrapper<Department>()
                .eq("parentId", id))

                .stream().map(Department::getParentId)
                .collect(Collectors.toList());

        if (departments.size() > 0) {
            return RespBean.error("还有子部门存在, 删除失败!");
        }
        departmentMapper.deleteDepartment(id);
        return null;
    }
}
