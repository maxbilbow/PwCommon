package com.maxbilbow.pwcommon.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Max on 04/06/2016.
 */
public abstract class AbstractPwController
{

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView getPage()
  {
    return getModelAndView();
  }

  public abstract String getViewUrl();

  protected ModelAndView getModelAndView()
  {
    return new ModelAndView(getViewUrl());
  }
}
