package ru.alfabank.bankinfo.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import kotlin.Unit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.alfabank.bankinfo.Application;
import ru.alfabank.bankinfo.dsl.BankInfoDslBuilder;
import ru.alfabank.bankinfo.model.BankBlzInfo;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
public class BankInfoGatewayTest {
    private final String BLZ = "36580072";

    @Autowired
    private BankInfoGateway bankInfoGateway;


    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(4567));

    @Test
    public void getBankInfo() throws JsonProcessingException {
        stubFor(post("/")
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "text/xml; charset=utf-8")
                                .withTransformers("xpath-response-transformer")
                                .withBody(new BankInfoDslBuilder().blzInfoReponse(bankBlzInfo -> {
                                    bankBlzInfo.setBezeichnung("Dresdner Bank");
                                    bankBlzInfo.setBic("DRESDEFF365");
                                    bankBlzInfo.setOrt("Oberhausen, Rheinl");
                                    bankBlzInfo.setPlz("46003");
                                    return Unit.INSTANCE;
                                }))
                ));

        BankBlzInfo bankInfo = bankInfoGateway.getBankInfo(BLZ);
        assertEquals(bankInfo.getBezeichnung(), "Dresdner Bank");
        assertEquals(bankInfo.getBic(), "DRESDEFF365");
        assertEquals(bankInfo.getOrt(), "Oberhausen, Rheinl");
        assertEquals(bankInfo.getPlz(),"46003");
    }


}