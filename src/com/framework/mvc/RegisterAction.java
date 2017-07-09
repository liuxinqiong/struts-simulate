package com.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framework.action.Action;
import com.framework.action.ActionForm;
import com.framework.action.ActionForward;

public class RegisterAction extends  Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, String paramter) {
		System.out.println("ÓÃ»§×¢²á...");
		return new ActionForward("/regsucc.jsp");
	}

}
