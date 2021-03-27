package Music;

import java.util.Random;

public enum InstrumentEnum {
    Hapsichord(6), TubularBells(14), Agogo(113), PanFlute(75), ChurchOrgan(19);
    private final int value;

    InstrumentEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public static String getRandomInstrument() {
        InstrumentEnum[] instruments = values();
        Random rand = new Random();
        InstrumentEnum randomInstrument = instruments[rand.nextInt(instruments.length)];

        return String.valueOf(randomInstrument.value);
    }
}