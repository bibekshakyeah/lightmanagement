package lightmanagement;

public class Light {
	private final int MAX_BRIGTHNESS = 100;
	private LightMode lightMode;
	private LightState lightState;
	private int lightBrightness;

	public Light(LightMode lightMode, LightState lightState) {
		this.lightMode = lightMode;
		this.lightState = lightState;
	}

	public void setLightBrightness(int brightness) throws InvalidBrightness {
		if (brightness < 0) {
			throw new InvalidBrightness("Brigtness cannot be less than 0");
		}
		if (brightness > MAX_BRIGTHNESS) {
			throw new InvalidBrightness("Brigtness cannot exceed Max BrightNess");
		}
		this.lightBrightness = brightness;
	}

	public LightMode getLightMode() {
		return lightMode;
	}

	public void setLightMode(LightMode lightMode) {
		this.lightMode = lightMode;
	}

	public LightState getLightState() {
		return lightState;
	}

	public void setLightState(LightState lightState) {
		this.lightState = lightState;
	}

	public int getLightBrightness() {
		return lightBrightness;
	}

	@Override
	public String toString() {
		return "Light [lightMode=" + lightMode + ", lightState=" + lightState + ", lightBrightness=" + lightBrightness
				+ "]";
	}

}