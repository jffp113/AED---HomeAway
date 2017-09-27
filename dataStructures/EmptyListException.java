package dataStructures;

public class EmptyListException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public EmptyListException( )
    {
        super();
    }

    public EmptyListException( String message )
    {
        super(message);
    }

}

