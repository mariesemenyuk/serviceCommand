package com.example.commandj11;

import com.example.commandj11.service.CommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.ws.Endpoint;

public class CommandPublisher {
    private static final Logger logger = LoggerFactory.getLogger(CommandPublisher.class);

    public static void main(String[] args) {
        Endpoint endpoint = Endpoint.create(new CommandImpl());
        endpoint.publish("https://af95-185-119-1-92.eu.ngrok.io/groups");

        logger.info("Country web service ready to consume requests!");
    }
}
