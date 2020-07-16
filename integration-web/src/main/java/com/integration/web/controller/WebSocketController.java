package com.integration.web.controller;

import com.integration.web.controller.pojo.WebSocketMessage;
import com.integration.websocket.server.SocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述
 * Title: WebSocketController
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/16
 */
@RequestMapping(value = "/websocket")
@RestController
public class WebSocketController {

    @Autowired
    SocketServer socketServer;

    @PostMapping(value = "/sendMessage")
    public void sendMessage(@RequestBody WebSocketMessage webSocketMessage) {
        ExecutorService executorService = Executors.newFixedThreadPool(200);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(() -> {
                webSocketMessage.setUnitId(String.valueOf(finalI));
                SocketServer.sendMessage(webSocketMessage.toString(), webSocketMessage.getUserId());
            });
        }
    }
}
