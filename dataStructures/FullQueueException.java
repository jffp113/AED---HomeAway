package dataStructures;

public class FullQueueException extends RuntimeException
{                                     

    static final long serialVersionUID = 0L;


    public FullQueueException( )   
    {
        super();
    }

    public FullQueueException( String message )
    {
        super(message);
    }

}
