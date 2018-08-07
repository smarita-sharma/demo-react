package org.openmrs.tag.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.tag.api.TagService;

import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1)
public class TagController extends BaseRestController {
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/tag/uniquetags")
	@ResponseBody
	public List<String> allTags() {
		return tagService.getAllTags();
	}
}
