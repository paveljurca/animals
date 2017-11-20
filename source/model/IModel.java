package model;

import java.util.Set;
import javax.swing.ImageIcon;

/**
 *
 * @author xjurp20
 */
public interface IModel {

    String getWantedAnimal();

    boolean guessAnimal(String animal);

    Set<ImageIcon> getAnimalsImg();

    int guessedRight();

    int guessedWrong();
}
