package Music;

public enum InstrumentEnum {
    Harpsichord(6), TubularBells(14), Agogo(113), PanFlute(75), ChurchOrgan(19);
    private final int value;

    InstrumentEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}