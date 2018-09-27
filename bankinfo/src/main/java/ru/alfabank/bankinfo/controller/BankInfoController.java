package ru.alfabank.bankinfo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.bankinfo.gateway.BankInfoGateway;
import ru.alfabank.bankinfo.model.BankBlzInfo;

@RestController
public class BankInfoController {

    @Autowired
    private BankInfoGateway bankInfoGateway;

    @RequestMapping("/")
    public BankBlzInfo getBankInformation(@RequestParam("bankBlz") String bankBlz) {
        return bankInfoGateway.getBankInfo(bankBlz);
    }
}
