package Services;

import Music.NoteEnum;
import java.util.ArrayList;

public class TextConvertorService implements ITextConvertorService{
    private int currentOctave;

    public TextConvertorService(){
        currentOctave = 1;
    }

    public String convert(String raw_text, int initialVolume, int initialBpm, int initialInstrument) {
        int currentVolume = initialVolume;
        int currentInstrument = initialInstrument;

        ArrayList<String> resultArray = new ArrayList<>();
        NoteEnum lastNote = null;

        resultArray.add("I" + initialInstrument);
        resultArray.add("T" + initialBpm);

        for(int character = 0; character < raw_text.length(); character++){
            switch (raw_text.charAt(character)) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                    lastNote = NoteEnum.valueOf(String.valueOf(raw_text.charAt(character)));
                    resultArray.add(String.valueOf(lastNote.getValue() + currentOctave * 12));
                    break;
                case '.':
                case '?':
                    if(currentOctave < 9) {
                        currentOctave ++;
                    } else {
                        currentOctave = 0;
                    }
                    break;
                case ' ':
                    if(currentVolume < 64) {
                        currentVolume *= 2;
                    } else {
                        currentVolume = initialVolume;
                    }
                    resultArray.add(":CON(7," + currentVolume + ")");
                    break;
                case '!':
                    resultArray.add("I113");
                    currentInstrument = 113;
                    break;
                case '\n':
                    resultArray.add("I14");
                    currentInstrument = 14;
                    break;
                case ';':
                    resultArray.add("I75");
                    currentInstrument = 75;
                    break;
                case ',':
                    resultArray.add("I19");
                    currentInstrument = 19;
                    break;
                case 'i':
                case 'I':
                case 'o':
                case 'O':
                case 'u':
                case 'U':
                    resultArray.add("I6");
                    currentInstrument = 6;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    resultArray.add("I" + (currentInstrument + Character.digit(raw_text.charAt(character), 10)));
                    break;
                default:
                    assert lastNote != null;
                    resultArray.add(String.valueOf(lastNote.getValue() + currentOctave * 12));
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : resultArray) {
            sb.append(s).append(" ");
        }
        String str = sb.toString();
        System.out.println(str);

        return str;
    }
}