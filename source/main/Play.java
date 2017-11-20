//DU2 GUI
//Vytvořte jednoduchou aplikaci pro poznávání obrázků pro děti.
//Aplikace zobrazí na hlavním okně panel s devíti obrázky –
//použijte GridLayout (3,3). Stiskne-li uživatel tlačítko Hádej,
//aplikace zobrazí v labelu náhodně vybraný název toho, co je na obrázku.
//Pokud uživatel klikne myší na správném obrázku, aplikace mu to oznámí a
//připočítá mu bod do správných odpovědí. Pokud klikne na jiném obrázku,
//připočítá mu bod ke špatným odpovědím. Pokuste se logiku hry oddělit od
//uživatelského rozhraní, tj. mít ji v samostatné třídě.
package main;

import model.Model;
import ui.GUI;

/**
 *
 * @author xjurp20
 */
public class Play {

    private Play() {
    }

    public static void main(String[] args) {
        GUI gui = new GUI(new Model());
        gui.show();
    }
}
