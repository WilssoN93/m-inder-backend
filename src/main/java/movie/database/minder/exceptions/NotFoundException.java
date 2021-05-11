package movie.database.minder.exceptions;

public class NotFoundException extends RuntimeException{

    private String message;

    public NotFoundException(final String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
