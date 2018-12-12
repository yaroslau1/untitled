package com.work.listeners;

import com.work.DAO.CityConnectDAO;
import com.work.exeption.DAOException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        System.out.println("Session Created:: ID="+sessionEvent.getSession().getId());
        sessionEvent.getSession().setAttribute("name", "Session");
        try {
            CityConnectDAO cityConnectDAO = new CityConnectDAO();
            sessionEvent.getSession().setAttribute("cityConnectDB", cityConnectDAO);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        System.out.println("Session Destroyed:: ID="+sessionEvent.getSession().getId());
        try {
            CityConnectDAO cityConnectDAO = (CityConnectDAO) sessionEvent.getSession().getAttribute("cityConnectDAO");
            cityConnectDAO.close();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
