package Services;

import Constants.ConstraintsConstants;
import Constants.JFugueMusicConstants;
import Constants.TextConstants;
import Music.NoteEnum;
import java.util.ArrayList;
import java.util.HashMap;

public class TextConvertorService implements ITextConvertorService{
    private int currentOctave;
    private final HashMap<String, Integer> instrumentHashMap = new HashMap<>();

    public TextConvertorService(){
        currentOctave = 1;

        instrumentHashMap.put(TextConstants.AGOGO_CHARACTER, 113);
        instrumentHashMap.put(TextConstants.TUBULAR_BELLS_CHARACTER, 14);
        instrumentHashMap.put(TextConstants.PAN_FLUTE_CHARACTER, 75);
        instrumentHashMap.put(TextConstants.CHURCH_ORGAN_CHARACTER, 19);
        instrumentHashMap.put(TextConstants.HARPSICHORD_CHARACTERS, 6);
    }

    public String convert(String rawText, int initialBpm, int initialInstrument) {
        String resultString = cleanString(rawText);

        ArrayList<String> manipulationArray = setVolume(resultString.toCharArray());
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
        text = text.replaceAll("[RT]", TextConstants.GENERIC_CONSONANT);
        text = text.replaceAll("[ouIOU]", TextConstants.GENERIC_VOCAL);
        return text;
    }

    private ArrayList<String> setVolume(char[] text) {
        int currentVolume = ConstraintsConstants.MAX_VOLUME;
        ArrayList<String> resultArray = new ArrayList<>();

        for (char character : text) {
            if (character == ' ') {
                if (currentVolume * 2 < ConstraintsConstants.MAX_VOLUME) {
                    currentVolume *= 2;
                } else {
                    currentVolume = ConstraintsConstants.DEFAULT_VOLUME;
                }
                resultArray.add(JFugueMusicConstants.VOLUME + currentVolume + JFugueMusicConstants.CLOSE_PARENTHESIS);
            } else {
                resultArray.add(String.valueOf(character));
            }
        }

        return resultArray;
    }

    private ArrayList<String> setInstruments(ArrayList<String> text, int initialInstrument) {
        int currentInstrument = initialInstrument;
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);

        for (String substring : text) {
            if(instrumentHashMap.containsKey(substring)) {
                currentInstrument = instrumentHashMap.get(substring);
                arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);
            }else if (TextConstants.DIGIT_CHARACTERS.contains(substring)) {
                currentInstrument = currentInstrument + Integer.parseInt(substring);
                if (currentInstrument > 127) {
                    currentInstrument -= 127;
                }
                arrayList.add(JFugueMusicConstants.INSTRUMENT + currentInstrument);
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
            if (substring.contains(JFugueMusicConstants.VOLUME) || substring.contains(JFugueMusicConstants.INSTRUMENT)) {
                arrayList.add(substring);
            } else if (TextConstants.NOTES_CHARACTERS.contains(substring)) {
                lastNote = NoteEnum.valueOf(substring);
                arrayList.add(String.valueOf(lastNote.getValue() + currentOctave * 12));
            } else {
                if (TextConstants.A_TO_G_LOWERCASE.contains(substring) && lastNote != null) {
                    arrayList.add(String.valueOf(lastNote.getValue() + currentOctave * 12));
                } else if (TextConstants.INCREASE_OCTAVE_CHARACTERS.contains(substring)) {
                    if (currentOctave < ConstraintsConstants.MAX_OCTAVE) {
                        currentOctave++;
                    } else {
                        currentOctave = ConstraintsConstants.MIN_OCTAVE;
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