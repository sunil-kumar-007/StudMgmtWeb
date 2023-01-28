package com.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sm.command.StudentCommand;
import com.sm.validator.StudentValidator;

@Controller
@RequestMapping("/insert.htm")
public class InsertStudentController {
	@Autowired
	private StudentValidator validator;

	/**
	 * formBackingObject(req)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		StudentCommand command = null;

		command = new StudentCommand();
		model.addAttribute("studCmd", command);
		return "insert";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processForm(Model model,
			@ModelAttribute("studCmd") StudentCommand command,
			BindingResult errors) {
		
		if(validator.supports(command.getClass())) {
			validator.validate(command, errors);
			if(errors.hasErrors()) {
				return "insert";
			}
		}
		
		model.addAttribute("id", command.getId());
		return "success";
	}
}
