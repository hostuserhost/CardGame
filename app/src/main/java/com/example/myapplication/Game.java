package com.example.myapplication;
import java.util.Arrays;
import java.util.Random;

public class Game {
    public static int round;
    public static int action;
    static Random random=new Random();


    public static void attack(int num, int dmg)
    {
        if (num == 1)
        {

            if (Player2.army==0)
            {
                if (Player2.armor >= dmg)
                {
                    Player2.armor -= dmg;
                }

                else
                {
                    dmg -= Player2.armor;
                    Player2.armor = 0;
                    Player2.hp -= dmg;
                }
            }
            else
            {
                if (dmg>=Player2.s_hp[0])
                {
                    for (int i=0;i<Player2.army-1;i++)
                    {
                        Player2.s_hp[i]=Player2.s_hp[i+1];
                    }
                }
                else
                {
                    Player2.s_hp[0]-=dmg;
                }
            }
        }
        else
        {

            if (Player1.army==0)
            {
                if (Player1.armor >= dmg)
                {
                    Player1.armor -= dmg;
                }

                else
                {
                    dmg -= Player1.armor;
                    Player1.armor = 0;
                    Player1.hp -= dmg;
                }
            }
            else
            {
                if (dmg>=Player1.s_hp[0])
                {
                    for (int i=0;i<Player1.army-1;i++)
                    {
                        Player1.s_hp[i]=Player1.s_hp[i+1];
                    }
                }
                else
                {
                    Player1.s_hp[0]-=dmg;
                }
            }
        }
    }

    public static void deff(int num, int armor)
    {
        if (num == 1)
        {
            Player1.armor += armor;
        }
        else
        {
            Player2.armor += armor;
        }
    }

    public static void meditation(int num, int card)
    {
        if (num == 1)
        {
            for (int i = 0; i < card; i++)
            {

            }
        }
        else
        {
            for (int i = 0; i < card; i++)
            {

            }
        }
    }

    public static void epiphany(int num, int card)
    {
        if (num == 1)
        {
            for (int i = 0; i < card; i++)
            {

            }
        }
        else
        {
            for (int i = 0; i < card; i++)
            {

            }
        }
    }

    public static void summon_attack(int num, int value)
    {
        if (num==1)
        {

        }
        else
        {

        }
    }

    public static void summon_deff(int num, int value)
    {
        if (num==1)
        {
            Player1.army++;
            Player1.s_hp[Player1.army]=value;
        }
        else
        {
            Player2.army++;
            Player2.s_hp[Player2.army]=value;
        }
    }

    public static void divine(int num, int value)
    {
        if (num == 1)
        {
            Player2.hp -= value;
        }
        else
        {
            Player1.hp += value;
        }
    }

    public static boolean EndGame()
    {
        if (Player1.hp <= 0)
        {

            return true;
        }
        else
        {
            if(Player2.hp <= 0)
            {

                return true;
            }
            else
            {
                return false;
            }
        }


    }

    public static void StartNewTurn()
    {
        round++;
        if (action < 7)
        {
            action++;
            Player1.hand[action]=null;
            Player2.hand[action]=null;
        }

        for (int i=0; i<=action; i++)
        {
            if (Player1.hand[i]==null)
            {
                int j=1;
                Player1.hand[i]=Player1.deck[0];
                while(Player1.deck[j-1]!=null)
                {
                    Player1.deck[j-1]=Player1.deck[j];
                    j++;
                }
            }
        }

        for (int i=0; i<=action; i++)
        {
            if (Player2.hand[i]==null)
            {
                int j=1;
                Player2.hand[i]=Player2.deck[0];
                while(Player1.deck[j-1]!=null)
                {
                    Player2.deck[j-1]=Player2.deck[j];
                    j++;
                }
            }
        }

        Arrays.sort(Player1.hand);
        Arrays.sort(Player2.hand);
    }

    public static void StartGame()

    {
        round = 1;
        action = 2;

        Player1.hp = 30;
        Player2.hp = 30;
        Player1.armor = 0;
        Player2.armor = 0;

        Player1.army = 0;
        Player2.army = 0;

        for (int i=0;i<3;i++)
        {
            int card=random.nextInt(5);
            Player1.hand[i]=new Card(Base.id[card],Base.spell[card],Base.value[card],Base.rarity[card]);
            card=random.nextInt(3);
            Player2.hand[i]=new Card(Base.id[card],Base.spell[card],Base.value[card],Base.rarity[card]);
        }

        for (int i=3;i<7;i++)
        {

            Player1.hand[i]=new Card(1,1,1,100);
            Player2.hand[i]=new Card(1,1,1,100);
        }

        for (int i=0;i<6;i++)
        {
            int card=random.nextInt(3);
            Player1.deck[i]=new Card(Base.id[card],Base.spell[card],Base.value[card],Base.rarity[card]);
            card=random.nextInt(3);
            Player2.deck[i]=new Card(Base.id[card],Base.spell[card],Base.value[card],Base.rarity[card]);
        }
        Player1.deck[6]=null;
        Player2.deck[6]=null;

        for (int i = 0; i < 3; i++)
        {
            Player1.s_hp[i] = 0;
            Player2.s_hp[i] = 0;
        }

        Arrays.sort(Player1.hand);
        Arrays.sort(Player2.hand);
    }


}
