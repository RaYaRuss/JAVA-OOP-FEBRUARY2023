package magicGame.models.region;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegionImpl implements Region{
    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizardsList = new ArrayList<>();
        List<Magician> blackWidowsList = new ArrayList<>();

        for (Magician magician : magicians) {
            if (magician.getClass().getSimpleName().equals("Wizard")) {
                wizardsList.add(magician);
            } else if (magician.getClass().getSimpleName().equals("BlackWidow")) {
                blackWidowsList.add(magician);
            }
        }
        while (!wizardsList.isEmpty() && !blackWidowsList.isEmpty()) {
            Wizard wizardToShoot = (Wizard) wizardsList.get(0);
            BlackWidow blackWidowToShoot = (BlackWidow) blackWidowsList.get(0);

            blackWidowToShoot.takeDamage(wizardToShoot.getMagic().fire());
            if (blackWidowToShoot.isAlive()) {
                wizardToShoot.takeDamage(blackWidowToShoot.getMagic().fire());
                if (!wizardToShoot.isAlive()) {
                    wizardsList.remove(wizardToShoot);
                }
            } else {
                blackWidowsList.remove(blackWidowToShoot);
            }
        }
        if (wizardsList.size() > blackWidowsList.size()) {
            return "Wizards win!";
        } else {
            return "Black widows win";
        }
    }
}
