package com.example.notebook.user.model;


import com.example.notebook.annotation.MyValid;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * user
 * @author zhaohongliang
 * @description
 * @date 17:34:00 2019-03-20
 */
@Entity
@Data
@Table(name = "t_user")
public class User implements Serializable {

    /**
     * id
     * 注意：springboot2.0依赖了Hibernate5.2版本，1.5x依赖的是Hibernate5.0.12版本，这两个版本在处理Id自增方面是不一样的
     * @GeneratedValue 默认strategy = GenerationType.AUTO
     * @GeneratedValue(strategy = GenerationType.AUTO)，id生成策略为auto,那么默认会被转出id自增。
     * 在新版本里，auto是不行的，不会自增，而且Hibernate会额外创建出来一个表来专门维护Id。可以自行尝试一下，会多出来一个表。
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     * 这里采用UUID策略，没有采用自增策略，所以不必修改@GeneratedValue的value
     */
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid", strategy="uuid")
    @Column(name = "id", length = 32)
    private String id;          // id

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, length = 20)
    @NotNull(message = "name不可以为空")
    private String name;        // 姓名

    /**
     * 年龄
     */
    @Column(name = "age", length = 3)
    private Integer age;        // 年龄

    /**
     * 家庭住址
     */
    @Length(max = 25, message = "地址长度不能超过25")
    @Column(name = "address", length = 255)
    private String address;     // 家庭住址

}
