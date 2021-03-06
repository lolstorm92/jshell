package pt.tecnico.myDrive.exception;

public class ReadPermissionException extends MyDriveException {
    private static final long serialVersionUID = 1L;

    private String _name;
    private String _user;

    public ReadPermissionException(String name, String user) {
        _name = name;
        _user = user;
    }

    public String getFileName() {
        return _name;
    }

    @Override
    public String getMessage() {
        return _user+ " don't have permissions to read on " + getFileName() + " file";
    }
}
