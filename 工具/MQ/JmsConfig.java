package com.ftvalue.customer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    @Value("${spring.activemq.maxConnections:20}")
    private int maxConnections;
    @Value("${spring.activemq.timeOut:5000}")
    private int timeOut;
    @Value("${spring.activemq.maximumRedeliveries:6}")
    private int maximumRedeliveries;
    @Value("${spring.activemq.redeliveryDelay:10000}")
    private int redeliveryDelay;
    @Value("${spring.activemq.useExponentialBackOff:true}")
    private boolean useExponentialBackOff;
    @Value("${spring.activemq.backOffMultiplier:2}")
    private int backOffMultiplier;

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(maximumRedeliveries);
        redeliveryPolicy.setMaximumRedeliveryDelay(redeliveryDelay);
        redeliveryPolicy.setInitialRedeliveryDelay(redeliveryDelay);
        redeliveryPolicy.setUseExponentialBackOff(useExponentialBackOff);
        redeliveryPolicy.setBackOffMultiplier(backOffMultiplier);
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setMaxThreadPoolSize(maxConnections);
        connectionFactory.setSendTimeout(timeOut);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }

}
