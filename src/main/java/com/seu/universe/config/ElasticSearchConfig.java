package com.seu.universe.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;

@Repository
public class ElasticSearchConfig {
    /**
     * elk集群地址
     */
    @Value("${elasticsearch.ip}")
    private String hostName;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    TransportClient transportClient = null;

    void esinit() {
        if (transportClient != null) {
            return;
        }
        try {
            Settings esSetting = Settings.builder()
                    .put("cluster.name", clusterName) //集群名字
                    .build();
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostName), Integer.valueOf(9300));
            transportClient.addTransportAddresses(transportAddress);
        } catch (Exception e) {

        }
    }

}
