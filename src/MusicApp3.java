import javax.sound.midi.*;

public class MusicApp3 {
    public static void main(String args[]) {
        MusicApp3 ma3 = new MusicApp3();
        if (args.length == 2) {
            ma3.play(args[0], args[1]);
        } else {
            System.out.println("Don't forget the args!");
        }
    }

    public void play(String instrumentNumStr, String noteNumStr) {
        try {
            int instrumentNum = Integer.parseInt(instrumentNumStr);
            if (instrumentNum < 0 || instrumentNum > 127) {
                System.out.println("Instrument number (1st arg) must be within the range 0-127");
                return;
            }
            int noteNum = Integer.parseInt(noteNumStr);
            if (noteNum < 0 || noteNum > 127) {
                System.out.println("Note number (2nd arg) must be within the range 0-127");
                return;
            }
            MidiEvent changeInstrument = new MidiEvent(new ShortMessage(192, 1, instrumentNum, 0), 1);
            MidiEvent noteOn = new MidiEvent(new ShortMessage(144, 1, noteNum, 100), 1);
            MidiEvent noteOff = new MidiEvent(new ShortMessage(128, 1, noteNum, 100), 16);
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();
            for (MidiEvent event : new MidiEvent[] {changeInstrument, noteOn, noteOff}) {
                track.add(event);
            }
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            player.setSequence(seq);
            player.start();
        } catch (NumberFormatException ex) {
            System.out.println("One or more of the CLI arguments could not be converted to an integer");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
