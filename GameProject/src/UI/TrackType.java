package UI;

public enum TrackType {
    Track1("res/track3.png"),
    Track2("res/track6.png"),
    Track3("res/track4.png"),
    Track4("res/track1.png"),
    Track5("res/track5.png"),
    Track6("res/track2.png"),
    Track7("res/track7.png"),
    Track8("res/track8.png"),
    Track9("res/not.png");
    private String value;

    TrackType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return this.value;
    }
    public static TrackType fromString(String value){
        for (TrackType type :TrackType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

}
