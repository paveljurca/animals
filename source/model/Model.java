package model;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.ImageIcon;

/**
 *
 * @author xjurp20
 */
public class Model implements IModel {

    private String wantedAnimal;
    private Set<ImageIcon> animalsImg;
    private int correct;
    private int wrong;
    private Random rand;

    private enum Animal {
        MONKEY,
        GIRAFFE,
        LION,
        GAZELLA,
        HIPOPOTAMUS,
        RHINOCERUS,
        HYENA,
        OSTRICH;
    }

    public Model() {
        init();
    }

    private void init() {
        correct = 0;
        wrong = 0;
        rand = new Random();
        wantedAnimal = getRandAnimal();

        animalsImg = new HashSet<>();
        for (Animal a : Animal.values()) {
            URL imageURL = Model.class.getResource("/images/"
                    + a.toString().toLowerCase() + ".jpg");
            if (imageURL != null) {
                animalsImg.add(new ImageIcon(imageURL, a.toString()));
            }
        }

        if (animalsImg.isEmpty()) {
            System.err.println("ERR: no images here!");
            System.exit(1);
        }
    }

    private String getRandAnimal() {
        return Animal.values()[rand.nextInt(Animal.values().length)].toString();
    }

    @Override
    public String getWantedAnimal() {
        return wantedAnimal;
    }

    @Override
    public boolean guessAnimal(String animal) {
        if (animal.equalsIgnoreCase(wantedAnimal)) {
            wantedAnimal = getRandAnimal();
            correct++;
            return true;
        } else {
            wrong++;
            return false;
        }
    }

    @Override
    public Set<ImageIcon> getAnimalsImg() {
        return Collections.unmodifiableSet(animalsImg);
    }

    @Override
    public int guessedRight() {
        return correct;
    }

    @Override
    public int guessedWrong() {
        return wrong;
    }
}
