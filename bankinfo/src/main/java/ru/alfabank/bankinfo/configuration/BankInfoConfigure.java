package ru.alfabank.bankinfo.configuration;

import com.thomas_bayer.blz.BLZServicePortType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankInfoConfigure {

    @Value("${cxfclient.address}")
    String address;

    @Bean(name = "blzProxy")
    public BLZServicePortType blzProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean =
                new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(BLZServicePortType.class);
        jaxWsProxyFactoryBean.setAddress(address);
        return (BLZServicePortType) jaxWsProxyFactoryBean.create();
    }

    
}
