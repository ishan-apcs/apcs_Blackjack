/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjack;
import java.util.*;
/**
 *
 * @author dot
 */
public class Player {
    private ArrayList<Card> hand;
    private int score;
    
    public Player() {
        hand = new ArrayList<Card>();
        score = 0;
    }
    
    public void addCard(Card inCard) {
        hand.add(inCard);
    }
    
    public int getScore() {
        ArrayList<Integer> aces = new ArrayList<>();

        score = 0;

        for(int i = 0; i < hand.size(); i++)
        {
            if (hand.get(i).getValue() != 11) {
                score += hand.get(i).getValue();
            } else {
                aces.add(i);
            }
        }

        while (aces.size() > 0) {
            if (score > (10 - aces.size() + 1)) {
                hand.get(aces.get(0)).changeValue(1);
            }
            
            score += hand.get(aces.get(0)).getValue();
            aces.remove(0);
        }

        return score;
    }
    
    public ArrayList<Card> getHand() {
        return hand;
    }
}
