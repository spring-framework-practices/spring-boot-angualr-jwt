package com.trl.springbootspringsecurityangularjwt.controller;

import com.trl.springbootspringsecurityangularjwt.exception.handler.ControllerExceptionHandler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class MainController extends ControllerExceptionHandler {
}
