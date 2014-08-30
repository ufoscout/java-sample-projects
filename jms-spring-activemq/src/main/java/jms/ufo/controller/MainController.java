/*
 * ---------------------------------------------------------------------------- PROJECT : EURES PACKAGE :
 * eu.europa.ec.empl.eures.cvo.batch.web.controller FILE : BatchController.java CREATED BY : ARHS Developments ON : Apr
 * 4, 2012 MODIFIED BY : ARHS Developments ON : $LastChangedDate VERSION : $LastChangedRevision
 * ---------------------------------------------------------------------------- Copyright (c) 2011 European Commission -
 * DG EMPL ----------------------------------------------------------------------------
 */
package jms.ufo.controller;

import java.util.Map;

import jms.ufo.counter.Counter;
import jms.ufo.counter.CounterQueueSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@RequestMapping("/main")
public class MainController {

	private static String REDIRECT_TO_CONSOLE = "redirect:/page/main"; //$NON-NLS-1$

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CounterQueueSender counterQueueSender;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showConsole(final Map<String, Object> model) {

		logger.debug("Method called"); //$NON-NLS-1$
		
		model.put("count", Counter.count()); //$NON-NLS-1$

		return "main"; //$NON-NLS-1$
	}
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
	public String pauseGroup() {

		counterQueueSender.startCounter();
		
		return REDIRECT_TO_CONSOLE; 
	}

}
