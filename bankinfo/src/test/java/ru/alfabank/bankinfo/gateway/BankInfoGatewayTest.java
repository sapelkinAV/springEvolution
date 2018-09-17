package ru.alfabank.bankinfo.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.thomas_bayer.blz.DetailsType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alfabank.bankinfo.configuration.BankInfoConfigure;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BankInfoGatewayTest {
    private final String BLZ = "36580072";

    @Autowired
    private BankInfoGateway bankInfoGateway;

    @Autowired
    private BankInfoConfigure bankInfoConfigure;

    private WireMockServer wireMockServer;
    private ResponseTemplateTransformer transformer;


    @Before
    public void initWireMock(){
        wireMockServer = new WireMockServer();
        transformer = new ResponseTemplateTransformer(false);
        wireMockServer.start();
        wireMockServer.stubFor(
                get(bankInfoConfigure.getAddress())
                        .withHeader(HttpHeaders.ACCEPT, equalTo(MediaType.APPLICATION_XML_VALUE))
                        .willReturn(
                                aResponse()
                                        .withBodyFile("successful-response.xml")
                                        .withTransformers(transformer.getName())
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)

                        )
        );
    }

    @After
    public void  tearDown(){
        wireMockServer.stop();
    }

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