package ru.alfabank.bankinfo.controller;

import info.developerblog.spring.thrift.annotation.ThriftClient
import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alfabank.bankinfo.Application
import ru.alfabank.thrift.OpenLibraryService

@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OpenLibraryThriftControllerTest {

    @ThriftClient(path = "/library" )
    lateinit var thriftClient: OpenLibraryService.Client


    @Test
    fun openLibThriftControllerTest(){
        val books = thriftClient.getBooks("neil")
        books?.forEach(::println)
    }

}