/*
 * ---------------------------------------------------------------------------- PROJECT : EURES PACKAGE :
 * eu.europa.ec.empl.eures.cvo.batch.web.controller FILE : BatchController.java CREATED BY : ARHS Developments ON : Apr
 * 4, 2012 MODIFIED BY : ARHS Developments ON : $LastChangedDate VERSION : $LastChangedRevision
 * ---------------------------------------------------------------------------- Copyright (c) 2011 European Commission -
 * DG EMPL ----------------------------------------------------------------------------
 */
package ufo.hazelcast.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ufo.hazelcast.service.CacheService;

/**
 * <class_description>
 * <p>
 * <b>notes</b>:
 * <p>
 * ON : Apr 4, 2012
 * 
 * @author ARHS Developments - Francesco Cina
 * @version $Revision
 */
@Controller
@RequestMapping("/console")
public class ConsoleController {

	private static String CONSOLE = "console";
	private static String REDIRECT_TO_CONSOLE = "redirect:/page/" + CONSOLE; //$NON-NLS-1$

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private CacheService cacheService;

	@RequestMapping(method = RequestMethod.GET)
	public String showConsole(final Map<String, Object> model, FormBean formBean) {

		logger.info("Show console"); //$NON-NLS-1$

		model.put("sharedList", cacheService.getSharedList());
		model.put("formBean", formBean);

		return CONSOLE; //$NON-NLS-1$
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(FormBean formBean) {

		logger.info("Adding value [{}] to cache" , formBean.getValue()); //$NON-NLS-1$
		if (!formBean.getValue().isEmpty()) {
			cacheService.addToSharedList(formBean.getValue());
		}

		return REDIRECT_TO_CONSOLE;
	}

	@RequestMapping(value = "/evictAll", method = RequestMethod.GET)
	public String evictAll() {

		logger.info("Evict all entries"); //$NON-NLS-1$
		cacheService.evictAll();

		return REDIRECT_TO_CONSOLE;
	}

}
