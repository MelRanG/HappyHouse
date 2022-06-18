package com.ssafy.happyhouse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DynamicUpdate
@Table(
        name="user",
        uniqueConstraints = {
                @UniqueConstraint(name="UK_USER_ID", columnNames="USER_ID")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="USER_SEQ")
    private int userSeq;
    @Column(name="USER_ID")
    private String userId;
    @Column(name="USER_PASSWORD")
    private String userPassword;
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="USER_EMAIL")
    private String userEmail;
    @Column(name="USER_PHONE")
    private String userPhone;
    @Column(name="USER_ROLE_ID")
    private int userRoleId; //default 2(normal)

}

