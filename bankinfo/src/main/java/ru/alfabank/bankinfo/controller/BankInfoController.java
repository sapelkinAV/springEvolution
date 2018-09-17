package ru.alfabank.bankinfo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.bankinfo.gateway.BankInfoGateway;

@RestController
public class BankInfoController {

    @Autowired
    private BankInfoGateway bankInfoGateway;

    @RequestMapping("/")
    public String getBankInformation(@RequestParam("bankBlz") String bankBlz) throws JsonProcessingException {
        return bankInfoGateway.getBankInfoString(bankBlz);
    }
}
