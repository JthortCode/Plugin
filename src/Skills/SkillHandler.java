/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Skills;

import Main.Main;

/**
 *
 * @author User
 */
public class SkillHandler {
    
    Main plugin;
    Agility agility;
    Alchemy alchemy;
    Attack attack;
    Crafting crafting;
    Defence defence;
    Farming farming;
    Mining mining;
    Overall overall;
    Range range;
    Smithing smithing;
    Woodcutting woodcutting;
    
    public SkillHandler(Main plugin){
        this.plugin = plugin;
        mining = new Mining(plugin);
        woodcutting = new Woodcutting(plugin);
        range = new Range(plugin);
        smithing = new Smithing(plugin);
    }
    public Agility getAgilityHandler(){
        return agility;
    }
    public Alchemy getAlchemyHandler(){
        return alchemy;
    }
    public Attack getAttackHandler(){
        return attack;
    }
    public Crafting getCraftingHandler(){
        return crafting;
    }
    public Defence getDefenceHandler(){
        return defence;
    }
    public Farming getFramingHandler(){
        return farming;
    }
    public Mining getMiningHandler(){
        return mining;
    }
    public Overall getOverallHandler(){
        return overall;
    }
    public Range getRangeHandler(){
        return range;
    }
    public Smithing getSmithingHandler(){
        return smithing;
    }
    public Woodcutting getWoodcuttingHandler(){
        return woodcutting;
    }
}
