package ru.alfabank.bankinfo.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomas_bayer.blz.BLZServicePortType;
import com.thomas_bayer.blz.DetailsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alfabank.bankinfo.converters.BankInfoConverter;
import ru.alfabank.bankinfo.model.BankBlzInfo;

@Component
public class BankInfoGateway {

    @Autowired
    private BLZServicePortType blzProxy;

    @Autowired
    private BankInfoConverter bankInfoConverter;

    public BankBlzInfo getBankInfo(String bankBlz){
        return bankInfoConverter.mapToBankBlzInfo(blzProxy.getBank(bankBlz));
    }

}
