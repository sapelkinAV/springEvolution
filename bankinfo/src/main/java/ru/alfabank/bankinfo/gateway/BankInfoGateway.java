package ru.alfabank.bankinfo.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomas_bayer.blz.BLZServicePortType;
import com.thomas_bayer.blz.DetailsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankInfoGateway {

    @Autowired
    private BLZServicePortType blzProxy;


    public String getBankInfoString(String bankBlz) throws JsonProcessingException {
        DetailsType bankDetails = getBankInfo(bankBlz);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(bankDetails);
    }

    public DetailsType getBankInfo(String bankBlz){
        return blzProxy.getBank(bankBlz);
    }


}
