package ru.alfabank.bankinfo.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thomas_bayer.blz.DetailsType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankInfoGatewayTest {
    private final String BLZ = "36580072";

    @Autowired
    BankInfoGateway bankInfoGateway;

    @Test
    public void getBankInfoString() throws JsonProcessingException {
        String bankInfoString = bankInfoGateway.getBankInfoString(BLZ);
        assertEquals(bankInfoString, "{\"bezeichnung\":\"Dresdner Bank\",\"bic\":\"DRESDEFF365\",\"ort\":\"Oberhausen, Rheinl\",\"plz\":\"46003\"}");
    }

    @Test
    public void getBankInfo() {
        DetailsType bankInfo = bankInfoGateway.getBankInfo(BLZ);
        assertEquals(bankInfo.getBezeichnung(), "Dresdner Bank");
        assertEquals(bankInfo.getBic(), "DRESDEFF365");
        assertEquals(bankInfo.getOrt(), "Oberhausen, Rheinl");
        assertEquals(bankInfo.getPlz(),"46003");

    }
}