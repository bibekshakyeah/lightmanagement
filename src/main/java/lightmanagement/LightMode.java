package lightmanagement;

public enum LightMode{
	 MORNING("MORNING"), EVENING("EVENING") , AFTERNOON("AFTERNOON");

	 LightMode(String mode){
	    this.mode = mode;
	  }

	  private final String mode;


	  public String toString(){
	    return this.mode;
	  }
	}