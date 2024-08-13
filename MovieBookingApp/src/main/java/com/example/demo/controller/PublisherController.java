package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DataPublisherServiceImpl;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
	@Autowired
	DataPublisherServiceImpl dataPub;
	
	@PostMapping("/produce")
	public void publishingMessage() {//(@RequestParam("msg") String msg) {
		dataPub.sendMessage("**********************\n*************************");
	}
}
