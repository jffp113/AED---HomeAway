package dataStructures;

public class InvalidPositionException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public InvalidPositionException( )
    {
        super();
    }

    public InvalidPositionException( String message )
    {
        super(message);
    }

}

