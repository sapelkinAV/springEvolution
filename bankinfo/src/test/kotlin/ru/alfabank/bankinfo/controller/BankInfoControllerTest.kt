package ru.alfabank.bankinfo.controller


import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import ru.alfabank.bankinfo.Application
import ru.alfabank.bankinfo.dsl.BankInfoDslBuilder
import ru.alfabank.bankinfo.model.BankBlzInfo

@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BankInfoControllerTest {

    @Rule
    @JvmField
    public val wireMockRule = WireMockRule(options().port(4567))

    @Autowired
    lateinit var mockMvc: MockMvc

    private val BLZ = "36580072"
    private val expectedResult = BankBlzInfo().apply {
        bezeichnung = "Dresdner Bank"
        bic = "DRESDEFF365"
        ort = "Oberhausen, Rheinl"
        plz = "46003"
    }

    @Test
    @Throws(JsonProcessingException::class)
    fun getBankInfo() {
        stubFor(post("/")
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "text/xml; charset=utf-8")
                                .withTransformers("xpath-response-transformer")
                                .withBody(BankInfoDslBuilder().blzInfoReponse {
                                    bezeichnung = expectedResult.bezeichnung
                                    bic = expectedResult.bic
                                    ort = expectedResult.ort
                                    plz = expectedResult.plz
                                })
                ))

        mockMvc.perform(MockMvcRequestBuilders.post("/?bankBlz=$BLZ")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect { mvcResult ->
            val response = ObjectMapper().readValue(mvcResult.response.contentAsString,BankBlzInfo::class.java)
            assertEquals(expectedResult,response)
        }

    }
}