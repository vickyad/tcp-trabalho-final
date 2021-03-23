package Music;

import org.jetbrains.annotations.NotNull;
import org.jfugue.theory.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;
import java.util.stream.Collectors;

public class TextConversor {
    private static final String PAUSE = "R";
    private static final String BPM = "T";
    private static final String INSTRUMENT = "I";
    private static final String BLANK_SPACE = " ";
    private static final String INVALID_CHARACTERES = "[^a-gimoptuA-GIMOPTU\\+\\-\\s\\n]";
    private int currentOctave;      // oitava do momento
    private int currentInstrument;  // instrumento atual (primeiramente escolhido pelo tal do usuário)
    private int currentVolume;      // volume atual da música
    private int currentBpm;         // BPM atual (primeiramente escolhido pelo tal do usuário)
    private NoteEnum currentNote;   //nota atual
    private Randomizer randomizer = new Randomizer();

    public TextConversor(){
        currentOctave = 1;
    }

    public String convert(String raw_text) {
        String musical_text = raw_text;
        System.out.println(musical_text);

        List<String> musical_list = cleanString(musical_text);
        System.out.println(musical_list.toString());

        if(!musical_list.isEmpty()){
            musical_list = addPauses(musical_list);

            System.out.println(musical_list.toString());

            musical_list = convertNotes(musical_list);

            System.out.println(musical_list.toString());

            setBpm(musical_text);
            setInstruments(musical_text);
            setVolume(musical_text);
        }

        return musical_text;
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
        return list.stream().map(item -> item.equals(BLANK_SPACE)? PAUSE : item).collect(Collectors.toList());
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

        currentOctave = 1;

        List<String> result = new ArrayList<>();
        for (String item : list) {
            NoteEnum currentNote = notes.get(item.toUpperCase());

            if(currentNote != null) {
                int note_int = currentNote.getValue() + currentOctave * 12;
                result.add(String.valueOf(note_int));
                lastChange = String.valueOf(note_int);
            } else if(item.equals("T+")) {
                if(currentOctave < 9){
                    currentOctave ++;
                }
            } else if (item.equals("T-")) {
                if(currentOctave > 0) {
                    currentOctave--;
                }
            } else if (item.equalsIgnoreCase("i") || item.equalsIgnoreCase("o") || item.equalsIgnoreCase("u")) {
                result.add(lastChange);
            } else if (item.equals(".") || item.equals("?")) {
                lastChange = NoteEnum.getRandomNote(currentOctave * 12);
                result.add(lastChange);

            } else {
                if(item.equals(PAUSE)) {
                    lastChange = PAUSE;
                }
                result.add(item);
            }
        }

        return result;
    }

    //Passo 4: ajuste nos BPM's
    private void setBpm(String text) {
        text = BPM + currentBpm + BLANK_SPACE + text;
    }

    // Passo 5: ajuste dos instrumentos
    private void setInstruments(String text) {
        text = INSTRUMENT + currentInstrument + BLANK_SPACE + text;
        text = text.replace("\n", INSTRUMENT + randomizer.getRandomInstrument() + BLANK_SPACE);
    }

    // Passo 6: ajuste de volume
    private void setVolume(String text) {

    }
}