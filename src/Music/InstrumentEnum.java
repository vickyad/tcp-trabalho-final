package Music;

import java.util.Random;

public enum InstrumentEnum {
    Piano(0), Guitar(24), Violin(40), Trumpet(56), Bass(32);
    private final int value;

    InstrumentEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    static String getRandomInstrument() {
        InstrumentEnum[] instruments = values();
        Random rand = new Random();
        InstrumentEnum randomInstrument = instruments[rand.nextInt(instruments.length)];

        return String.valueOf(randomInstrument.value);
    }
}