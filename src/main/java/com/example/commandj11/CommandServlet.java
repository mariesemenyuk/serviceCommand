package com.example.commandj11;

import com.example.commandj11.service.CommandImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;
import javax.xml.ws.WebServiceRef;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet(name="CommandServlet", urlPatterns={"/CommandServlet"})
public class CommandServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation =
            "http://localhost:8080/command/group?WSDL")
    private CommandImpl commandService;

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // TODO here parser is needed
        MessageFactory messageFactory = null;
        SOAPBody soapBody;
        String arg0;
        try {
            messageFactory = MessageFactory.newInstance();
            InputStream inStream = request.getInputStream();
            SOAPMessage message = MessageFactory.newInstance().createMessage(null,
                    inStream);
            soapBody = message.getSOAPBody();
            SOAPFault fault = soapBody.getFault();
            arg0 = soapBody.getAttribute("arg0");

        } catch (SOAPException e) {
            throw new RuntimeException(e);
        }

        try (PrintWriter out = response.getWriter()) {

            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<title>Servlet HelloServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelloServlet at " +
                    request.getContextPath () + "</h1>");
            out.println("<p>" + sayHello("world") + "</p>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    private String sayHello(java.lang.String arg0) {
        return commandService.createGroup(arg0);
    }

}
