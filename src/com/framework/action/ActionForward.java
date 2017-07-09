package com.framework.action;

public class ActionForward {
	
	private boolean redirct=true;
	private String path;
	
	
	public boolean isRedirct() {
		return redirct;
	}
	public void setRedirct(boolean redirct) {
		this.redirct = redirct;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ActionForward(){
	}
	public ActionForward(String path){

		this.path = path;
	}
	public ActionForward(boolean redirect,String path){
		this.redirct = redirect;
		this.path = path;
	}
}
