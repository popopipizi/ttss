package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 菜品订单：(DishOrders)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DishOrders")
public class DishOrders implements Serializable {

    // DishOrders编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_orders_id")
    private Integer dish_orders_id;

    // 订单编号
    @Basic
    private String order_number;
    // 菜品名称
    @Basic
    private String dish_name;
    // 菜品类型
    @Basic
    private String dish_type;
    // 菜品价格
    @Basic
    private String price_of_dishes;
    // 订单用户
    @Basic
    private Integer order_user;
    // 用户姓名
    @Basic
    private String user_name;
    // 联系电话
    @Basic
    private String contact_number;
    // 订单桌号
    @Basic
    private String order_table_number;
    // 订单数量
    @Basic
    private Integer order_quantity;
    // 订单日期
    @Basic
    private Timestamp order_date;
    // 订单总价
    @Basic
    private String total_order_price;
    // 订单状态
    @Basic
    private String order_status;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
