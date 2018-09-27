package ru.alfabank.bankinfo.controller

import com.fasterxml.jackson.core.JsonProcessingException
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.github.tomakehurst.wiremock.junit.WireMockRule
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import ru.alfabank.bankinfo.Application
import ru.alfabank.bankinfo.dsl.BankInfoDslBuilder


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
 class BankInfoControllerTest {
    private val BLZ = "36580072"

    @Rule
    @JvmField
    val wireMockRule = WireMockRule(options().port(4567))

    @Autowired
    private lateinit var bankInfoController:BankInfoController

    @Test
    @Throws(JsonProcessingException::class)
    fun getBankInfo() {
        stubFor(post("/")
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "text/xml; charset=utf-8")
                                .withTransformers("xpath-response-transformer")
                                .withBody(BankInfoDslBuilder().BlzInfoResponse {
                                        bezeichnung = "Dresdner Bank"
                                        bic = "DRESDEFF365"
                                        ort = "Oberhausen, Rheinl"
                                        plz = "46003"
                                })
                ))

        val bankInfo = bankInfoController.getBankInformation(BLZ)
        assertEquals(bankInfo.bezeichnung, "Dresdner Bank")
        assertEquals(bankInfo.bic, "DRESDEFF365")
        assertEquals(bankInfo.ort, "Oberhausen, Rheinl")
        assertEquals(bankInfo.plz, "46003")
    }
}