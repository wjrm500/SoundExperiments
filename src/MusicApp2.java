import javax.sound.midi.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MusicApp2 {
    public static void main(String[] args) {
        MusicApp2 ma2 = new MusicApp2();
        ma2.play();
    }

    public void play() {
        try {
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();
            ShortMessage a = new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 10, 0);
            MidiEvent changeInstrument = new MidiEvent(a, 1);
            ShortMessage b = new ShortMessage(ShortMessage.NOTE_ON, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(b, 1);
            track.add(changeInstrument);
            track.add(noteOn);
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            player.setSequence(seq);
            player.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
