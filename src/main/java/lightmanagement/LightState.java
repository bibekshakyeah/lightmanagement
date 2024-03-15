package lightmanagement;

public enum LightState {
    ON("ON"), OFF("OFF");

    LightState(String state) {
        this.state = state;
    }

    private final String state;


    public String toString() {
        return this.state;
    }
}
