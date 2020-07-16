package com.integration.web.controller.pojo;

import lombok.Data;

/**
 * 描述
 * Title: WebSocketMessage
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/16
 */
@Data
public class WebSocketMessage {
    private String unitId;
    private String userId;
    private String message;
}
