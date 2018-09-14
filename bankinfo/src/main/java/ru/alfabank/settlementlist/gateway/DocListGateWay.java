package ru.alfabank.settlementlist.gateway;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.thomas_bayer.blz.BLZServicePortType;
import com.thomas_bayer.blz.DetailsType;
import com.thomas_bayer.blz.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


import java.util.GregorianCalendar;

@Component
@RestController
public class DocListGateWay  {

    @Autowired
    BLZServicePortType blzProxy;

    @RequestMapping("/")
    public String getBank(){
        ObjectFactory blzInputFactory = new ObjectFactory();

        DetailsType bankDetails = blzProxy.getBank("45250035");
        return bankDetails.toString();
    }


}
