package ru.alfabank.bankinfo.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomas_bayer.blz.BLZServicePortType;
import com.thomas_bayer.blz.DetailsType;
import com.thomas_bayer.blz.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class DocListGateWay  {

    @Autowired
    BLZServicePortType blzProxy;

    @RequestMapping("/")
    public @ResponseBody
    String getBank(@RequestParam("bankBlz") String bankBlz) throws JsonProcessingException {
        ObjectFactory blzInputFactory = new ObjectFactory();
        DetailsType bankDetails = blzProxy.getBank(bankBlz);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(bankDetails);
    }


}
