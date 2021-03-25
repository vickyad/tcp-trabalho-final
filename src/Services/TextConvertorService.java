package Services;

import Constants.JFugueMusicConstants;
import Constants.TextConstants;
import Music.InstrumentEnum;
import Music.NoteEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;
import java.util.stream.Collectors;

public class TextConvertorService {
    private int currentOctave;
    private int currentInstrument;
    private int currentVolume;
    private int currentBpm;

    public TextConvertorService(){
        currentOctave = 1;
    }

    public String convert(String raw_text, int initialVolume, int initialBpm, int initialInstrument) {
        currentVolume = initialVolume;
        currentBpm = initialBpm;
        currentInstrument = initialInstrument;

        List<String> musical_list = cleanString(raw_text);

        if(!musical_list.isEmpty()){
            musical_list = addPauses(musical_list);
            musical_list = convertNotes(musical_list);
            setBpm(musical_list);
            setInstruments(musical_list);
            musical_list = setVolume(musical_list, initialVolume);
        }

        StringBuilder musical_string = new StringBuilder();
        for(String item : musical_list) {
            musical_string.append(item).append(TextConstants.BLANK_SPACE);
        }

        return musical_string.toString();
    }

    // Passo 1: remover todas os caracteres que não importam
    private ArrayList<String> cleanString(String text) {
        String regex = "(BPM\\+|BPM\\-|T\\+|T\\-|[a-gA-G]|[iou]|[IOU]|\\+|\\-|\\.|\\?|\\s|\\\\n)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        ArrayList<String> found = new ArrayList<>();

        List<MatchResult> list = matcher.results().collect(Collectors.toList());
        for (MatchResult result : list) {
            found.add(text.substring(result.start(), result.end()));
        }

        return found;
    }

    // Passo 2: adicionar as pausas
    private List<String> addPauses(List<String> list) {
        return list.stream().map(item -> item.equals(TextConstants.BLANK_SPACE)? JFugueMusicConstants.PAUSE: item).collect(Collectors.toList());
    }

    // Passo 3: conversão das notas nas oitavas certas
    private List<String> convertNotes(List<String> list) {
        // substituir as notas com as oitavas certas
        HashMap< String, NoteEnum> notes = new HashMap<>();
        notes.put("A", NoteEnum.A);
        notes.put("B", NoteEnum.B);
        notes.put("C", NoteEnum.C);
        notes.put("D", NoteEnum.D);
        notes.put("E", NoteEnum.E);
        notes.put("F", NoteEnum.F);
        notes.put("G", NoteEnum.G);

        String lastChange = "";

        List<String> result = new ArrayList<>();
        for (String item : list) {
            NoteEnum currentNote = notes.get(item.toUpperCase());

            if(currentNote != null) {
                int note_int = currentNote.getValue() + currentOctave * 12;
                result.add(String.valueOf(note_int));
                lastChange = String.valueOf(note_int);
            } else if(item.equals(TextConstants.INCREASE_OCTAVE)) {
                if(currentOctave < 9){
                    currentOctave ++;
                }
            } else if (item.equals(TextConstants.DECREASE_OCTAVE)) {
                if(currentOctave > 0) {
                    currentOctave--;
                }
            } else if (item.equalsIgnoreCase(TextConstants.REPEAT_NOTE_OP1) || item.equalsIgnoreCase(TextConstants.REPEAT_NOTE_OP2) || item.equalsIgnoreCase(TextConstants.REPEAT_NOTE_OP3)) {
                result.add(lastChange);
            } else if (item.equals(TextConstants.RANDOM_NOTE_OP1) || item.equals(TextConstants.RANDOM_NOTE_OP2)) {
                lastChange = NoteEnum.getRandomNote(currentOctave * 12);
                result.add(lastChange);

            } else {
                if(item.equals(JFugueMusicConstants.PAUSE)) {
                    lastChange = JFugueMusicConstants.PAUSE;
                }
                result.add(item);
            }
        }

        return result;
    }

    //Passo 4: ajuste nos BPM's
    private void setBpm(List<String> list) {
        list.add(0, JFugueMusicConstants.BPM + currentBpm);

        if(list.contains(TextConstants.INCREASE_BPM)){
            if(currentBpm <= 170){
                currentBpm+= 50;
            }
            list.set(list.indexOf(TextConstants.INCREASE_BPM), JFugueMusicConstants.BPM + currentBpm);
        } else if(list.contains(TextConstants.DECREASE_BPM)) {
            if(currentBpm >= 90){
                currentBpm-= 50;
            }
            list.set(list.indexOf(TextConstants.DECREASE_BPM), JFugueMusicConstants.BPM + currentBpm);
        }
    }

    // Passo 5: ajuste dos instrumentos
    private void setInstruments(List<String> list) {
        list.add(0, JFugueMusicConstants.INSTRUMENT + currentInstrument);

        if(list.contains(TextConstants.RANDOM_INSTRUMENT)){
            list.set(list.indexOf(TextConstants.RANDOM_INSTRUMENT), JFugueMusicConstants.INSTRUMENT + InstrumentEnum.getRandomInstrument());
        }

    }

    // Passo 6: ajuste de volume
    private List<String> setVolume(List<String> list, int initialVolume) {
        List<String> result = new ArrayList<>();
        for (String item : list) {
            if(item.equals(TextConstants.INCREASE_VOLUME)) {
                if(currentVolume * 2 < 127) {
                    currentVolume *= 2;
                } else {
                    currentVolume = 127;
                }
                result.add(":CON(7," + currentVolume + ")");
            } else if (item.equals(TextConstants.RESET_VOLUME)) {
                result.add(":CON(7," + initialVolume + ")");
            } else {
                result.add(item);
            }
        }

        return result;
    }
}