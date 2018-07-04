package com.seu.universe.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


@Document(indexName = "social",type = "accountInfo", shards = 1,replicas = 0, refreshInterval = "-1")
public class AccountInfo {

    @Id
    private long id;

    @Field
    private String accountName;

    @Field
    private String nickName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
