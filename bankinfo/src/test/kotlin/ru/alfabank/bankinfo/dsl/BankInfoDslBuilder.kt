package ru.alfabank.bankinfo.dsl

import org.redundent.kotlin.xml.xml
import ru.alfabank.bankinfo.model.BankBlzInfo

class BankInfoDslBuilder{

    fun blzInfoReponse(blzInfo:BankBlzInfo) : String{
        return blzInfoReponse {
            bezeichnung = blzInfo.bezeichnung
            bic = blzInfo.bic
            plz = blzInfo.plz
            ort = blzInfo.ort
        }
    }

    fun blzInfoReponse(block: BankBlzInfo.() -> Unit): String {
        val bankInfo = BankBlzInfo().apply(block)
        return xml("soapenv:Envelope",prettyFormat = false) {
            namespace("soapenv","http://schemas.xmlsoap.org/soap/envelope/")
            "soapenv:Body" {
                "ns1:getBankResponse"{
                    namespace("ns1","http://thomas-bayer.com/blz/")
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
                        "ns1:plz"{
                            -bankInfo.plz
                        }
                    }
                }
            }
        }.toString()


    }
}
