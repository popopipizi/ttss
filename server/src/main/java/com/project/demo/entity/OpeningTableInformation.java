package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 开桌信息：(OpeningTableInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OpeningTableInformation")
public class OpeningTableInformation implements Serializable {

    // OpeningTableInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opening_table_information_id")
    private Integer opening_table_information_id;

    // 座位号码
    @Basic
    private String seat_number;
    // 座位人数
    @Basic
    private String number_of_seats;
    // 开桌用户
    @Basic
    private Integer open_table_users;
    // 用户姓名
    @Basic
    private String user_name;
    // 开桌时间
    @Basic
    private Timestamp table_opening_time;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
