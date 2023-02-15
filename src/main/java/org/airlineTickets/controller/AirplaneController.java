package org.airlineTickets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AirplaneController {
    @GetMapping(value = "")
    public String airplaneForm() {
        return "";
    }
}
