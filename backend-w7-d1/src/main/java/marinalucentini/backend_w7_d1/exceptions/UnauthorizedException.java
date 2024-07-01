package marinalucentini.backend_w7_d1.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException (String message){
        super(message);
    }
}
