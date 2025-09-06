package com.chatbto.chatbot;

import com.chatbto.chatbot.service.GeminiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ActiveProfiles("test")
class ChatbotApplicationTests {

    @MockitoBean
    GeminiService geminiService;

    @Test
    void contextLoads() {
        when(geminiService.generateResponse(anyString()))
                .thenReturn(Mono.just("test-response"));
    }
}
