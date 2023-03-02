public class Game {

    private int hp;
    private int score;
    private int level;
    private boolean gameEnd;

    public Game(){
        gameEnd = false;
        hp = 3;
        score = 0;
        level = 1;
    }

    public int getHp(){
        return hp;
    }

    public void loseHp(){
        hp -= 1;
        if (hp <= 0){
            gameEnd = true;
        }
    }

    public int getScore(){
        return score;
    }

    public void incrementScore(){
        score+=1;
        level = (int)(score/5 + 1);

    }

    public boolean isGameEnd(){
        return gameEnd;
    }
}
