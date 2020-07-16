package com.integration.websocket.entity;

import javax.websocket.Session;
import java.io.Serializable;

/**
 * 描述
 * Title: Client
 * Description: TODO
 * Copyright: Copyright (c) 2019<br>
 * Company: 齐丰科技股份有限公司<br>
 *
 * @author: xu wen kai
 * date: 2020/7/16
 */

public class Client implements Serializable {
    private String userName;

    private Session session;

    public Client(String userName, Session session) {
        this.userName = userName;
        this.session = session;
    }

    public Client() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Client{" +
                "userName='" + userName + '\'' +
                ", session=" + session +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
