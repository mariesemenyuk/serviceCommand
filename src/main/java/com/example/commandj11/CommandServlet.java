package com.example.commandj11;

import com.example.commandj11.service.Command;
import com.example.commandj11.service.CommandImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.ws.WebServiceRef;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name="CommandServlet", urlPatterns={"/CommandServlet"})
public class CommandServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation =
            "http://localhost:8080/command/group?WSDL")
    private CommandImpl commandService = CommandImpl.getInstance();

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        // TODO Очень костыльный парсинг xml. Требуется протестировать с разными методами
        BufferedReader reader = request.getReader();
        List<String> collect = reader.lines().collect(Collectors.toList());
        StringBuilder methodString = new StringBuilder();
        List<String> args = new ArrayList<>();
        int counter = 0;
        for (String str:collect) {
            if(str.contains("Body") && counter == 0) {
                String s = collect.get(collect.indexOf(str) + 1);
                methodString.append(s, 11, s.length() - 1);
                counter++;
            }
            if(str.contains("arg")) {
                args.add(str.trim());
            }
        }
        String method = methodString.toString();

        // TODO дописать и добавить остальные методы
        switch (method) {
            case "createGroup":
                String name = args.get(0);
                commandService.createGroup(name.substring(6, name.length() - 7));
                break;
            case "addUserToGroup":
                String username = args.get(0);
                String groupName = args.get(1);
                commandService.addUserToGroup(username, groupName);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
