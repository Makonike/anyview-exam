package com.zxd.www.websocket.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Makonike
 * @date 2021-10-10 13:21
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SendMessage extends SendMessageAll{
    private String userId;
}
