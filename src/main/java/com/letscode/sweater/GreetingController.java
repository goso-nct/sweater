package com.letscode.sweater;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.letscode.sweater.domain.Message;
import com.letscode.sweater.repo.IMessageRepo;

@Controller
public class GreetingController 
{
	@Autowired
	private IMessageRepo messageRepo;
	
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Model model) {
    	Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("add")
    public String add(@RequestParam String text, @RequestParam String tag,  Model model) {
    	if (text.isEmpty() || tag.isEmpty()) {
    		model.addAttribute("pageMsgOne", "Текст и Тэг должны быть не пустыми !");
    	}
    	else {
	    	Message m = new Message(text, tag);
	    	messageRepo.save(m);
	        model.addAttribute("pageMsgOne", "добавлено");
    	}
    	Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
    	return "main";
    }
    
    @PostMapping("filter")
    public String filter(@RequestParam String filter,  Model model) {
    	Iterable<Message> messages;
    	if (filter != null && !filter.isEmpty()) {
    		messages = messageRepo.findByTag(filter);
    	}
    	else {
    		messages = messageRepo.findAll();
    	}
    	model.addAttribute("messages", messages); 
    	return "main";
    }
}

