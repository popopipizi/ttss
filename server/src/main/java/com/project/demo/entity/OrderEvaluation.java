package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 订单评价：(OrderEvaluation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OrderEvaluation")
public class OrderEvaluation implements Serializable {

    // OrderEvaluation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_evaluation_id")
    private Integer order_evaluation_id;

    // 订单编号
    @Basic
    private String order_number;
    // 菜品名称
    @Basic
    private String dish_name;
    // 菜品价格
    @Basic
    private String price_of_dishes;
    // 订单用户
    @Basic
    private Integer order_user;
    // 评价等级
    @Basic
    private String evaluation_level;
    // 评价内容
    @Basic
    private String evaluation_content;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
