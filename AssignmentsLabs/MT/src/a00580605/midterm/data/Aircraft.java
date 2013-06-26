package a00580605.midterm.data;

public class Aircraft {
	private String aircraftType;
	private static Aircraft air;
	
	private Aircraft() {
		
	}
	
	public static Aircraft getTheInstance() {
		if(air == null)
			air = new Aircraft();
		return air;
	}

	public void setAircraftType(String string) {
		this.aircraftType = string;
	}
	
	public String getAircraftType() {
		return aircraftType;
	}

	@Override
	public String toString() {
		return "Aircraft [aircraftType=" + aircraftType + "]";
	}
}
