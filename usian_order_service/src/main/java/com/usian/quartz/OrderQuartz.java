package com.usian.quartz;

import com.usian.config.RedisClient;
import com.usian.mapper.LocalMessageMapper;
import com.usian.mq.MQSender;
import com.usian.pojo.LocalMessage;
import com.usian.pojo.TbOrder;
import com.usian.service.LocalMessageService;
import com.usian.service.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

/**
 * ClassName:OrderQuartz
 * Author:maodi
 * CreateTime:2021/03/29/14:01
 */
public class OrderQuartz implements Job {

    @Autowired
    private OrderService orderService;
    @Autowired
    private LocalMessageService localMessageService;

    @Autowired
    private MQSender mQSender;

    @Autowired
    private RedisClient redisClient;


    /**
     * 关闭超时订单
     * 检查本地消息表
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //解决quartz集群任务重复执行
        if (redisClient.setnx("SETNX_LOCK_KEY:" + ip, ip, 30)) {
            //1、查询超时订单
            List<TbOrder> tbOrderList = orderService.selectOvertimeOrder();
            System.out.println("执行关闭超时订单任务...." + new Date());
            //关闭超时订单
            for (int i = 0; i < tbOrderList.size(); i++) {
                TbOrder tbOrder = tbOrderList.get(i);
                orderService.updateOverTimeTbOrder(tbOrder);

                //3、把超时订单中的商品库存数量加回去
                orderService.updateTbItemByOrderId(tbOrder.getOrderId());
            }
            System.out.println("执行扫描本地消息表的任务...." + new Date());
            List<LocalMessage> localMessageList =
                    localMessageService.selectlocalMessageByStatus(1);
            for (int i = 0; i < localMessageList.size(); i++) {
                LocalMessage localMessage = localMessageList.get(i);
                mQSender.sendMsg(localMessage);
            }
            redisClient.del("SETNX_LOCK_KEY:" + ip);
        } else {
            System.out.println("============机器：" + ip +
                    " 占用分布式锁，任务正在执行=======================");
        }
    }

}
