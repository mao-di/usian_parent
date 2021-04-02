package com.usian.listener;

import com.usian.service.SearchItemService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:SearchMQListener
 * Author:maodi
 * CreateTime:2021/03/24/18:00
 */
@Component
public class SearchMQListener {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private SearchItemService searchItemService;

    /**
     * 监听者接收消息三要素：
     *  1、queue
     *  2、exchange
     *  3、routing key
     */
    //创建队列监听
    @RabbitListener(bindings = @QueueBinding(
            //队列名称
            value = @Queue(value="search_queue",durable = "true"),
            //交换路由名称 和类型 topic
            exchange = @Exchange(value="item_exchage",type= ExchangeTypes.TOPIC),
//            rottingkey
            key= {"item.add"}
    ))
    public void listen(String msg) throws Exception {
        System.out.println("接收到消息用于添加：" + msg);
        int result = searchItemService.insertDocument(msg);
        if(result>0){
            System.out.println("同步失败");
        }else {
            System.out.println("同步成功");
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            //队列名称
            value = @Queue(value="search_queue",durable = "true"),
            //交换路由名称 和类型 topic
            exchange = @Exchange(value="item_exchage",type= ExchangeTypes.TOPIC),
//            rottingkey
            key= {"item.update"}
    ))
    public void updateLine(String msg) throws Exception {
        System.out.println("接收到消息用于修改：" + msg);
        int result = searchItemService.updateDocument(msg);
        if(result>0){
            System.out.println("同步失败");
        }else {
            System.out.println("同步成功");
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            //队列名称
            value = @Queue(value="search_queue",durable = "true"),
            //交换路由名称 和类型 topic
            exchange = @Exchange(value="item_exchage",type= ExchangeTypes.TOPIC),
//            rottingkey
            key= {"item.delete"}
    ))
    public void deleteLine(String msg) throws Exception {
        System.out.println("接收到消息用于删除：" + msg);
        int result = searchItemService.deleteDocument(msg);
        if(result>0){
            System.out.println("同步失败");
        }else {
            System.out.println("同步成功");
        }
    }


}
