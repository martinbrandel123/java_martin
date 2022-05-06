package Game;
import Hero.Personnage;
import Hero.Weapon.Spell;
import Hero.Weapon.Weapon;
import Menu.Menu;
import Hero.Guerrier;
import Hero.Magicien;

public class Game {
    private Menu menu;
    private String classe;
    private String pseudo;
    private int position ;

    private int nbCase ;

    public Game(){
        this.menu = new Menu();
        position = 1;
        nbCase = 64;
    }
    public void startGame(){
        this.position = 1;
        initializeTheGame();
        changeInfos();
        createTheHero();
        createMainMenu();
    }
    public void restartGame(){
        while(true){
            String playAgainChoice = menu.askIfPlayAgain();
            if(playAgainChoice.equals("Oui")){
                startGame();
            }else if(playAgainChoice.equals("Non")) {
                System.exit(0);
            }
            playAgainChoice = "";
          }
        }

    public void initializeTheGame() {
        this.classe = menu.chooseTheHero();
        this.pseudo = menu.choosePseudo();
    }
    public void createMainMenu(){
        int menuChoice = menu.askForMenuChoice();
        switch(menuChoice){
            case 1:
                playGame();
                break;
        }
    }
    public void createTheHero(){
        if(classe.equalsIgnoreCase("Guerrier")){
            Weapon arme = new Weapon();
            Personnage personnage = new Guerrier(classe, pseudo, 10, 10, arme);
            this.menu.displayPersonnageInfo(personnage);
        }else if (classe.equalsIgnoreCase("Magicien")){
            Personnage personnage = new Magicien(classe, pseudo, 10, 10);
            this.menu.displayPersonnageInfo(personnage);
        }
    }

    public void changeInfos(){
        Boolean haveToChangeInfos = menu.isInfoToChange();
        System.out.println(haveToChangeInfos);
        while(haveToChangeInfos){
            if(haveToChangeInfos){
                String InfoToChange = menu.chooseInfoToChange();
                if(InfoToChange.equals("Classe")){
                    this.classe = menu.chooseTheHero();
                }else if(InfoToChange.equals("Pseudo")){
                    this.pseudo = menu.choosePseudo();
                }else if(InfoToChange.equals("Restart")){
                    this.classe = menu.chooseTheHero();
                    this.pseudo = menu.choosePseudo();
                }
            }
            haveToChangeInfos = menu.isInfoToChange();
        }
    }
    public void playGame(){
        int dice = 0;
        while(isGameInProcesse()){
            Boolean isEnter = menu.askForEnter();
            if(isEnter){
                dice = (int) Math.ceil(Math.random() * 6);
                menu.displayPosition(this.position);
                this.position += dice;
            }
        }
        if(!isGameInProcesse()){
            menu.displayEndGame();
        }
    }
    public boolean isGameInProcesse(){
        boolean res = this.position < nbCase;
        return res;
    }
}
