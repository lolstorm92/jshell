package pt.tecnico.myDrive.service;


import pt.tecnico.myDrive.domain.Manager;
import pt.tecnico.myDrive.domain.Session;
import pt.tecnico.myDrive.domain.SuperUser;
import pt.tecnico.myDrive.domain.User;
import pt.tecnico.myDrive.domain.File;

import pt.tecnico.myDrive.exception.InvalidUserCredentialsException;
import pt.tecnico.myDrive.exception.MyDriveException;

public class ReadPlainFile extends MyDriveService {
	
	private String _plainFileToken;
    private String _plainFileName;
    private String _content;
    
    public ReadPlainFile(String token, String plainFileName) {
    	_plainFileToken = token;
    	_plainFileName = plainFileName;
    }

    @Override
    protected void dispatch() throws MyDriveException {
    	File pf = Manager.getInstance().getSessionByToken(_plainFileToken).getCurrentDirectory().getFileByPath(_plainFileName);
    	_content = pf.getContent();
    }
    
    public String result() {
        return _content;
    }
    
}