package com.xxxx.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date ：Created in 2021/2/4 15:07
 * @description：公共返回对象
 * @author：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    /**(第1步).
     * @author     :
     * @description:成功返回结果
     * @return     :
     */
    public static RespBean success(String message){

        return new RespBean(200,message,null);
    }

    /**(第2步).
     * @author     :
     * @description:成功返回结果
     * @return     :
     */
    public static RespBean success(String message,Object obj){

        return new RespBean(200,message,obj);
    }

    /**(第3步).
     * @author     :
     * @description:失败返回结果
     * @return     :
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /**(第4步).
     * @author     :
     * @description:失败返回结果
     * @return     :
     */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }

}
