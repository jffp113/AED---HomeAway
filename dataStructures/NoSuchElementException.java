package dataStructures;

public class NoSuchElementException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public NoSuchElementException( )
    {
        super();
    }

    public NoSuchElementException( String message )
    {
        super(message);
    }

}

