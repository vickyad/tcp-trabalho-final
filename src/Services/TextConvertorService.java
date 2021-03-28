package Services;

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
        resultString = "T" + initialBpm + " " + resultString;

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
        int currentVolume = 127;
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

        arrayList.add("I" + currentInstrument);

        for (String substring : text) {
            if (substring.contains("!")) {
                arrayList.add("I113");
                currentInstrument = 113;
            } else if (substring.contains("\n")) {
                arrayList.add("I14");
                currentInstrument = 14;
            } else if (substring.contains(";")) {
                arrayList.add("I75");
                currentInstrument = 75;
            } else if (substring.contains(",") && !substring.contains("CON")) {
                arrayList.add("I19");
                currentInstrument = 19;
            } else if ("iouU".contains(substring)) {
                arrayList.add("I6");
                currentInstrument = 6;
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
            if (substring.contains("CON") || substring.contains("I")) {
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
                    arrayList.add("R");
                }
                lastNote = null;
            }
        }

        return arrayList;
    }
}