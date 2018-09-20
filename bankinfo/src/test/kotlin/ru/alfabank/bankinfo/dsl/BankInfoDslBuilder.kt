package ru.alfabank.bankinfo.dsl

import org.redundent.kotlin.xml.xml
import ru.alfabank.bankinfo.model.BankBlzInfo

class BankInfoDslBuilder{
    fun BlzInfoResponse(block: BankBlzInfo.() -> Unit): String {
        val bankInfo = BankBlzInfo().apply(block)
        //В этой либе можно указывать xml неймспейсы, но я не сильно запаривался с этим
        return xml("soapenv:Envelope") {
            xmlns = "http://schemas.xmlsoap.org/soap/envelope/"
            "soapenv:Body"{
                "ns1:getBankResponse"{
                    attribute("xmlns:ns1","http://thomas-bayer.com/blz/")
                    "ns1:details"{
                        "ns1:bezeichnung"{
                            -bankInfo.bezeichnung
                        }
                        "ns1:bic"{
                            -bankInfo.bic
                        }
                        "ns1:ort"{
                            -bankInfo.ort
                        }
                        "ns1:plz"{ -bankInfo.plz }
                    }
                }

            }
        }.toString()
                .replaceFirst("xmlns","xmlns:soapenv")
                .replace("\t","")
                .replace("\n","")

    }
}
