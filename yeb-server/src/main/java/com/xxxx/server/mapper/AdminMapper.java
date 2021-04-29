package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取操作员
     * @param id
     * @param keywords
     * @return
     */
    // 在方法参数的前面写上@Param("参数名")，表示给参数命名，名称就是括号中的内容
    //　　如public Student select(@Param("aaaa") String name,@Param("bbbb")int class_id);
    //　　给入参 String name 命名为aaaa，然后sql语句....where  s_name= #{aaaa} 中就可以根据aaaa得到参数值了。
    // 此 id1 跟AdminMapper.xml文件的传入 id1 同
    List<Admin> getAllAdmins(@Param("id1") Integer id, @Param("keywords") String keywords);

    /**
     * 更新操作员角色
     * @return
     */



}
