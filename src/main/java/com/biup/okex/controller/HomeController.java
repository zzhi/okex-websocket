package com.biup.okex.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller()
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    @ResponseBody()
    public String Home() {

        String hello = "hello world";
        logger.info(hello);
        return hello;
    }


}
