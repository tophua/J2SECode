package BeListen;

/**
* @ClassName: Event(�¼�����)
* @Description:����¼��࣬������װ�¼�Դ
* @date: 2014-9-9 ����9:37:56
*
*/ 

public class Event {
	 /**
	    * @Field: source
	    *          �¼�Դ(Person�����¼�Դ)
	    */ 
	    private Person source;

	    public Event() {
	        
	    }

	    public Event(Person source) {
	        this.source = source;
	    }

	    public Person getSource() {
	        return source;
	    }

	    public void setSource(Person source) {
	        this.source = source;
	    }
}
