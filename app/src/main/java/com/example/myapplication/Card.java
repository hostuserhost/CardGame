package com.example.myapplication;

public class Card implements Comparable<Card>{
    public int id;
    public int spell;
    public int value;
    public int rarity;

    public Card(int id, int spell, int value, int rarity)
    {
        this.id=id;
        this.spell=spell;
        this.value=value;
        this.rarity=rarity;
    }

    public void PlayCard(int num)
    {
        int spell=this.spell;
        int value=this.value;
        switch(spell)
        {
            case 1:
                Game.attack(num, value);
                break;
            case 2:
                Game.deff(num, value);
                break;
            case 3:
                Game.meditation(num, value);
                break;
            case 4:
                Game.epiphany(num, value);
                break;
            case 5:
                Game.summon_attack(num, value);
                break;
            case 6:
                Game.summon_deff(num, value);
                break;
            case 7:
                Game.divine(num, value);
                break;

        }

    }

    @Override
    public int compareTo(Card card) {
        return this.rarity-card.rarity;
    }

}

