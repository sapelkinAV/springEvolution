package ru.alfabank.bankinfo.controller;

import org.apache.thrift.protocol.TProtocolFactory
import org.apache.thrift.transport.THttpClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import ru.alfabank.bankinfo.Application
import ru.alfabank.thrift.OpenLibraryService









@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OpenLibraryThriftHandlerTest {


    lateinit var client: OpenLibraryService.Client

    @Autowired
    lateinit var protocolFactory: TProtocolFactory

    @LocalServerPort
    lateinit var port:Integer


    @Before
    fun setUp(){
        val transport = THttpClient("http://localhost:$port/library")
        val protocol = protocolFactory.getProtocol(transport)
        client = OpenLibraryService.Client(protocol)

    }

    @Test
    fun openLibThriftControllerTest(){
        val books = client.getBooks("the+lord+of+the+rings")
        assert(books.size < 100)

    }

}