package Music;

public enum NoteEnum {
    C(0), D(2), E(4), F(5), G(7), A(9), B(11);
    private final int value;

    NoteEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
