# SP1-HeroBuilder
SP1 opgave Hero Builder

## Iteration 1
### Added
- Added Hero.java class, used as Hero object in Main.java
- Made basic user input to create own Hero class

## Iteration 2.1
### Added
- Added Enemy.java, Weapon.java, Armor.java, Potion.java, Item.java
- Working on battle mechanic

### Problems / to-do
- Item subclasses are hard to access when in Item array
- Might have to rework my Item system
- Finish battle mechanic

## Iteration 2.2
### Added
- Battle functionality
- New inventory system
- Completely redid how item objects are made and function

### Removed
- Weapon.java
- Potion.java
- Armor.java

Removed due to complications, all item objects are now made in Item.java alone.

### to-do
- Make code look better
- Clean up
- Finish general battle mechanics
- Make XP system
- Count items in inventory
- Make text output look better
- Add choices

### Notes
#### New inventory system
The inventory system is now a HashMap. I have done this to easier get items, handle battle mechanics, use correct weapon and more.
I define the keys in the HashMap as "mainHand", "offHand", "potions", "armor", "other" (this one being for items not in use).
This is how the HashMap looks.

```
inventory = {
    "mainHand": ArrayList <Item>,
    "offHand": ArrayList <Item>,
    "potions": ArrayList <Item>,
    "armor": ArrayList <Item>,
    "other": ArrayList <Item>
}
```

## Iteration 2.3
### Added
- Finished battle mechanic as it is right now
- Made ChoiceMaker.java
- Made battle choice to be only Attack, Block, Heal and Potion, the individual hand choice was not needed
- Added levelUp() function in hero class
- Added function to get inventory as ArrayList, since getting all values of a HashMap fills a lot
- Made function takeDamage() have a boolean "blocking" argument, if it is true, damage done will be 'damage - defense'

### Notes
- Tried to make code more readable, tho still needs a lot of work
- Tried to clean up code, but also needs a lot of work
- XP cap to level up is currently 1000, but i will change that later
- ChoiceMaker.java is a class that displays all given options as a list, and lets the user choose one of them and returns that choice
- Making a Inventory.java class could be an idea to clean code and make it more readable
- I should probably make armor actually use able

#### ChoiceMaker.java
ChoiceMaker prints in this format:
```
1) choice1
2) choice2
3) choice3
...

return int
```

It returns the choice as the choice option number int, so if I chose number 2 in this case, it would return 2.

#### Battle.java
This is the battle mechanic, I run it as an object to avoid having to write it all every time.
The function fight() starts the fight and returns true if hero won and false if hero died.

The hero gets 4 choices as of right now Attack, Block, Heal and Potion. Durability is not implemented yet, but will be soon.
The enemy simply only attacks for now.

Enemy items (Loot) gets added to hero "other" key in inventory HashMap if hero wins, as well as gold and XP gets added to hero.