package dataStructures;

public class EmptyStackException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public EmptyStackException( )
    {
        super();
    }

    public EmptyStackException( String message )
    {
        super(message);
    }

}

