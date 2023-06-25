package com.zjl.eduorder.service;

import com.zjl.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author zjl
 * @since 2023-06-24
 */
public interface PayLogService extends IService<PayLog> {
    //生成微信支付二维码接口
    //参数是订单号
    Map createNatvie(String orderNo);

    //查询订单支付状态
    //参数：订单号，根据订单号查询 支付状态
    Map<String, String> queryPayStatus(String orderNo);

    //添加记录到支付表，更新订单表订单状态
    void updateOrdersStatus(Map<String, String> map);
}
