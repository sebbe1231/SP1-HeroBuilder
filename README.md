# SP1-HeroBuilder
SP1 opgave

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
    "other": ArrayList <Item>,
}
```