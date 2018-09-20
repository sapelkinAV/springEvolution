package ru.alfabank.bankinfo.dsl

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.post
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor

class BankInfoBuilder {
    String bezeichnung
    String bic
    String ort
    String plz

    def build(){
        stubFor(post("/")
                .willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml; charset=utf-8")
                        .withTransformers("xpath-response-transformer")
                        .withBodyFile("bankinfo-response.xml")
        ))
    }

}
