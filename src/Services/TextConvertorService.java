package Services;

import Constants.ConstraintsConstants;
import Constants.JFugueMusicConstants;
import Constants.TextConstants;
import Music.NoteEnum;
import java.util.ArrayList;

public class TextConvertorService implements ITextConvertorService{
    private int currentOctave;

    public TextConvertorService(){
        currentOctave = 1;
    }

    public String convert(String raw_text, int initialBpm, int initialInstrument) {
        String resultString = cleanString(raw_text);
        ArrayList<String> manipulationArray;

        manipulationArray = setVolume(resultString.toCharArray());
        manipulationArray = setInstruments(manipulationArray, initialInstrument);
        manipulationArray = setNotesOnOctaves(manipulationArray);

        resultString = convertArrayToString(manipulationArray);
        resultString = JFugueMusicConstants.BPM + initialBpm + TextConstants.BLANK_SPACE + resultString;

        System.out.println(resultString);

        return resultString;
    }

    private String convertArrayToString(ArrayList<String> manipulationArray) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Object substring : manipulationArray) {
            stringBuilder.append(substring).append(TextConstants.BLANK_SPACE);
        }
        return stringBuilder.toString();
    }

    private String cleanString(String text){
        text = text.replaceAll("[RT]", "r");
        text = text.replaceAll("[IO]", "i");
        return text;
    }

    private ArrayList<String> setVolume(char[] text) {
        int currentVolume = ConstraintsConstants.MAX_VOLUME;
        ArrayList<String> arrayList = new ArrayList<>();

        for (char c : text) {
            if (c == ' ') {
                if (currentVolume < 64) {
                    currentVolume *= 2;
                } else {
                    currentVolume = 40;
                }
                arrayList.add(":CON(7," + currentVolume + ")");
            } else {
                arrayList.add(String.valueOf(c));
            }
        }

        return arrayList;
    }

    private ArrayList<String> setInstruments(ArrayList<String> text, int initialInstrument) {
        int currentInstrument = initialInstrument;
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);

        for (String substring : text) {
            if (substring.contains("!")) {
                currentInstrument = 113;
                arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);
            } else if (substring.contains("\n")) {
                currentInstrument = 14;
                arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);
            } else if (substring.contains(";")) {
                currentInstrument = 75;
                arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);
            } else if (substring.contains(",") && !substring.contains("CON")) {
                currentInstrument = 19;
                arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);
            } else if ("iouU".contains(substring)) {
                currentInstrument = 6;
                arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);
            } else if ("0123456789".contains(substring)) {
                arrayList.add("I" + (currentInstrument + Character.digit(Integer.parseInt(substring), 10)));
            } else {
                arrayList.add(substring);
            }
        }

        return arrayList;
    }

    private ArrayList<String> setNotesOnOctaves(ArrayList<String> text) {
        ArrayList<String> arrayList = new ArrayList<>();
        NoteEnum lastNote = null;

        for (String substring : text) {
            if (substring.contains("CON") || substring.contains(JFugueMusicConstants.INSTRUMENT)) {
                arrayList.add(substring);
            } else if ("ABCDEFG".contains(substring)) {
                lastNote = NoteEnum.valueOf(substring);
                arrayList.add(String.valueOf(lastNote.getValue() + currentOctave * 12));
            } else {
                if ("abcdefg".contains(substring) && lastNote != null) {
                    arrayList.add(String.valueOf(lastNote.getValue() + currentOctave * 12));
                } else if (".?".contains(substring)) {
                    if (currentOctave < 9) {
                        currentOctave++;
                    } else {
                        currentOctave = 0;
                    }
                } else {
                    arrayList.add(JFugueMusicConstants.PAUSE);
                }
                lastNote = null;
            }
        }

        return arrayList;
    }
}