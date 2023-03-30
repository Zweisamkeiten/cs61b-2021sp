package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHeroLite {
    public static final String keyboard = "q2wf4p5gj7l8u9y;-[=zxsctvdbkjme,.o/' ";
    public static final double CONCERT = 440.0;

    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++) {
            double concert = CONCERT * Math.pow(2, (i - 24.0) / 12);
            strings[i] = new GuitarString(concert);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[keyboard.indexOf(key)].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < keyboard.length(); i++) {
                sample += strings[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            for (int i = 0; i < keyboard.length(); i++) {
                /* advance the simulation of each guitar string by one step */
                strings[i].tic();
            }

        }
    }
}

