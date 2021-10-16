import javax.sound.midi.*;

public class MusicApp1 {
    public static void main(String[] args) {
        MusicApp1 mini = new MusicApp1();
        mini.play();
    }

    public void play() {
        try {
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();
            ShortMessage a = new ShortMessage(144, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);
            ShortMessage b = new ShortMessage(128, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            player.setSequence(seq);
            player.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
