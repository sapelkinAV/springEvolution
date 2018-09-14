package ru.alfabank.settlementlist.configuration;

import com.thomas_bayer.blz.BLZServicePortType;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.xml.ws.Endpoint;

@Configuration
public class DocListConfigure {

    @Value("${cxfclient.address}")
    String address;

    @Bean(name = "blzProxy")
    public BLZServicePortType helloWorldProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean =
                new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(BLZServicePortType.class);
        jaxWsProxyFactoryBean.setAddress(address);
        return (BLZServicePortType) jaxWsProxyFactoryBean.create();
    }


}
