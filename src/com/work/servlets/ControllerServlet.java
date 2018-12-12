package com.work.servlets;

import com.work.DAO.CityConnectDAO;
import com.work.entity.City;
import com.work.exeption.DAOException;
import com.work.validation.StringValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;


//@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(-1);

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "showAll";
        }

        switch (action) {
            case "/insert":
                insert(request, response);
                break;
            case "/delete":
                delete(request, response);
                break;
            case "/update":
                update(request, response);
                break;
            case "/validateUpdate":
                update(request, response);
                break;
            case "/validateInsert":
                insert(request, response);
                break;
            case "/validateFindByName":
                findByName(request, response);
                break;
            default:
                showAll(request, response);
                break;
        }
    }

    private void findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cityName = request.getParameter("cityName");
        CityConnectDAO cityConnectDAO = (CityConnectDAO) session.getAttribute("cityConnectDB");
        if (isCityNameValid(cityName) == null){
            try {
                request.setAttribute("city", cityConnectDAO.findByName(cityName));
                System.out.println(cityName);
                getServletContext().getRequestDispatcher("/find.jsp").forward(request, response);
            } catch (DAOException e) {
                throw new ServletException("Some error with findByName method", e);
            }
        } else {
            request.setAttribute("actionCityName", "errorInCityName");
            request.setAttribute("errorCityName", isCityNameValid(cityName));
            request.setAttribute("cityName", cityName);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private String isCityNameValid (String cityName){
        String errorInCityName = null;
        if (cityName == null || cityName.equals("")) {
            errorInCityName = "City Name is empty. Enter some value to the City Name";
        } else {
            try {
                Integer.parseInt(cityName);
                errorInCityName = "City Name value is wrong. Enter some value to the City Name";
            } catch (NumberFormatException e) {
            }
        }
        return errorInCityName;
    }

    private String isCountryCodeValid (String countryCode){
        String errorInCountryCode = null;
        if (countryCode == null || countryCode.equals("")) {
            errorInCountryCode = "Country Code is empty. Enter some value to the Country Code";
        } else {
            try {
                Integer.parseInt(countryCode);
                errorInCountryCode = "Country Code value is wrong. Enter some value to the Country Code";
            } catch (NumberFormatException e) {
            }
        }
        return errorInCountryCode;
    }

    private String isPopulationValid(String population){
        String errorInPopulation = null;
        if (population == null || population.equals("")) {
            errorInPopulation = "Population is empty. Enter some value to the Population";
        } else {
            try {
                if (Integer.parseInt(population) < 0) {
                    errorInPopulation = "Population value is wrong. Enter some positive value to the Population";
                }
            } catch (NumberFormatException e) {
                errorInPopulation = "Population value is not digital. Enter some positive digital to the Population";
            }
        }
        return errorInPopulation;
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        City city = new City();
        HttpSession session = request.getSession();
        CityConnectDAO cityConnectDAO = (CityConnectDAO) session.getAttribute("cityConnectDB");
        String cityName = (request.getParameter("cityName"));
        String population = (request.getParameter("population"));
        String countryCode = (request.getParameter("countryCode"));

        if (isPopulationValid(population) == null && isCityNameValid(cityName) == null && isCountryCodeValid(countryCode) == null) {
            try {
                city.setName(cityName);
                city.setPopulation(Integer.parseInt(population));
                city.setCountryCode(countryCode);
                cityConnectDAO.insert(city);
                request.setAttribute("cities", cityConnectDAO.getAll());
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (DAOException e) {
                throw new ServletException("Some error with insert method", e);
            }
        } else {
            if (isPopulationValid(population) != null) {
                request.setAttribute("actionPopulation", "errorInPopulation");
                request.setAttribute("errorPopulation", isPopulationValid(population));
            }
            if (isCityNameValid(cityName) != null) {
                request.setAttribute("actionCityName", "errorInCityName");
                request.setAttribute("errorCityName", isCityNameValid(cityName));
            }
            if (isCountryCodeValid(countryCode) != null) {
                request.setAttribute("actionCountryCode", "errorInCountryCode");
                request.setAttribute("errorCountryCode", isCountryCodeValid(countryCode));
            }
            request.setAttribute("cityName", cityName);
            request.setAttribute("population", population);
            request.setAttribute("countryCode", countryCode);
            getServletContext().getRequestDispatcher("/insert.jsp").forward(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CityConnectDAO cityConnectDAO = (CityConnectDAO) session.getAttribute("cityConnectDB");
        try {
            cityConnectDAO.deleteById(Integer.parseInt(request.getParameter("cityId")));
            request.setAttribute("cities", cityConnectDAO.getAll());
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException("Some error with delete method", e);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        City city = new City();
        String id = request.getParameter("cityId");
        String cityName = request.getParameter("cityName");
        String population = request.getParameter("population");
        HttpSession session = request.getSession();
        CityConnectDAO cityConnectDAO = (CityConnectDAO) session.getAttribute("cityConnectDB");

        if (isPopulationValid(population) == null && isCityNameValid(cityName) == null) {
            try {
                System.out.println(Integer.parseInt(request.getParameter("cityId")));
                city.setName(request.getParameter("cityName"));
                city.setPopulation(Integer.parseInt(request.getParameter("population")));
                city.setId(Integer.parseInt(request.getParameter("cityId")));
                cityConnectDAO.update(city);
                request.setAttribute("cities", cityConnectDAO.getAll());
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (DAOException e) {
                throw new ServletException("Some error with update method", e);
            }
        } else {
            if (isPopulationValid(population) != null) {
                request.setAttribute("actionPopulation", "errorInPopulation");
                request.setAttribute("errorPopulation", isPopulationValid(population));
            }
            if (isCityNameValid(cityName) != null) {
                request.setAttribute("actionCityName", "errorInCityName");
                request.setAttribute("errorCityName", isCityNameValid(cityName));
            }
            request.setAttribute("cityName", cityName);
            request.setAttribute("cityId", id);
            request.setAttribute("population", population);
            getServletContext().getRequestDispatcher("/update.jsp").forward(request, response);
        }
    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            CityConnectDAO cityConnectDAO = (CityConnectDAO) session.getAttribute("cityConnectDB");
            request.setAttribute("cities", cityConnectDAO.getAll());
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException("Some error with showAll method", e);
        }
    }
}
