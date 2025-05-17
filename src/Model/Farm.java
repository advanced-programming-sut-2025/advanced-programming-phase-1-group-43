package Model;

import Model.enums.SeasonEnum;

import java.time.LocalTime;
import java.util.*;

public class Farm {
    private final List<Tile> cropTiles = new ArrayList<>();

    /**
     * Automatically water every crop tile on the farm.
     */
    public void autoIrrigate() {
        System.out.println("Auto-irrigating all crops...");
        for (Tile t : cropTiles) {
            t.water();
        }
    }

    /**
     * Randomly strike three tiles with lightning, destroying their crops.
     */
    public void strikeLightning() {
        System.out.println("Lightning storm! Striking random tiles...");
        if (cropTiles.isEmpty()) return;
        for (int i = 0; i < 3; i++) {
            int idx = (int)(Math.random() * cropTiles.size());
            Tile t = cropTiles.get(idx);
            t.destroyCrop();
        }
    }

    /**
     * Add a tile to be managed as a crop tile.
     */
    public void addCropTile(Tile tile) {
        cropTiles.add(tile);
    }

    /**
     * @return an unmodifiable view of crop tiles
     */
    public List<Tile> getCropTiles() {
        return Collections.unmodifiableList(new ArrayList<>(cropTiles));
    }

    //kole map ro dar 64 * 40 gereftam    40 ta satr     64 ta soton
    // Fixed Elements

    /*
    --------------------- x
    |
    |
    |
    y

     */
    public static Cottage cottage = new Cottage(54,7,59,12);
    public static Greenhouse greenHouse = new Greenhouse(38,3,45,9);
    public static StoneMining stoneMining = new StoneMining(5,13,11,9);
    public static Lake lake = new Lake(28,31,36,38);

    // Random Elements
    public static ArrayList<Tree> trees = new ArrayList<>();
    public static ArrayList<Plant> AllPlants = new ArrayList<>();
    public static ArrayList<GiantPlant> AllGiantPlant = new ArrayList<>();
    public static ArrayList<Stone> stones = new ArrayList<>();
    public static ArrayList<Foraging> foragings = new ArrayList<>();
    Random random = new Random();

    public static ArrayList<Integer> xLocations = new ArrayList<>(Arrays.asList(40,50,40,50));
    public static ArrayList<Integer> yLocations = new ArrayList<>(Arrays.asList(60, 10, 15, 17));


    public Farm() {
        //Add Trees
        for (int i = 0; i < random.nextInt(20) + 20; i++) {
            int x = random.nextInt(64);
            int y = random.nextInt(40);
            while (xLocations.contains(x) && yLocations.contains(y)) {
                x = random.nextInt(64);
                y = random.nextInt(40);
            }
            xLocations.add(x);
            yLocations.add(y);
            int randomTreeType = random.nextInt(15);
            switch (randomTreeType) {
                case 0:
                    trees.add(new Tree("Apricot Tree", x, y, true));
                    break;
                case 1:
                    trees.add(new Tree("Cherry Tree", x, y, true));
                    break;
                case 2:
                    trees.add(new Tree("Banana Tree", x, y, true));
                    break;
                case 3:
                    trees.add(new Tree("Mango Tree", x, y, true));
                    break;
                case 4:
                    trees.add(new Tree("Orange Tree", x, y, true));
                    break;
                case 5:
                    trees.add(new Tree("Peach Tree", x, y, true));
                    break;
                case 6:
                    trees.add(new Tree("Apple Tree", x, y, true));
                    break;
                case 7:
                    trees.add(new Tree("Pomegranate Tree", x, y, true));
                    break;
                case 8:
                    trees.add(new Tree("Oak Tree", x, y, false));
                    break;
                case 9:
                    trees.add(new Tree("Maple Tree", x, y, false));
                    break;
                case 10:
                    trees.add(new Tree("Pine Tree", x, y, false));
                    break;
                case 11:
                    trees.add(new Tree("Mahogany Tree", x, y, true));
                    break;
                case 12:
                    trees.add(new Tree("Mushroom Tree", x, y, true));
                    break;
                case 13:
                    trees.add(new Tree("Mystic Tree", x, y, true));
                    break;
                default:
                    System.out.println("Error: Tree type not recognized");
                    break;
            }
        }


        //Add Stones
        for (int i = 0; i < random.nextInt(20) + 20; i++) {
            int x = random.nextInt(64);
            int y = random.nextInt(40);
            while (xLocations.contains(x) && yLocations.contains(y)) {
                x = random.nextInt(64);
                y = random.nextInt(40);
            }
            xLocations.add(x);
            yLocations.add(y);
            stones.add(new Stone(x, y));
        }

        // add foragings
        if(Spring){
//            All.Daffodil
//            All.Dandelion
//            All.Leek
//            All.Morel
//            All.Salmonberry
//            All.SpringOnion
//            All.WildHorseradish

            Random rand = new Random();
            int numOfForaging = rand.nextInt(8) + 7;

            for (int k = 0; k < numOfForaging; k++) {
                int RandomType = rand.nextInt(7);
                int x = rand.nextInt(64);
                int y = rand.nextInt(40);
                while (xLocations.contains(x) && yLocations.contains(y)) {
                    x = rand.nextInt(64);
                    y = rand.nextInt(40);
                }

                if (RandomType == 0) {
                    foragings.add(new Foraging(x, y, "Daffodil"));
                } else if (RandomType == 1) {
                    foragings.add(new Foraging(x, y, "Dandelion"));
                } else if (RandomType == 2) {
                    foragings.add(new Foraging(x, y, "Leek"));
                } else if (RandomType == 3) {
                    foragings.add(new Foraging(x, y, "Morel"));
                } else if (RandomType == 4) {
                    foragings.add(new Foraging(x, y, "Salmonberry"));
                } else if (RandomType == 5) {
                    foragings.add(new Foraging(x, y, "SpringOnion"));
                } else if (RandomType == 6) {
                    foragings.add(new Foraging(x, y, "WildHorseradish"));
                }

                xLocations.add(x);
                yLocations.add(y);
            }

        } else if(Summer){
    /*
    FiddleheadFern
    Grape
    RedMushroom
    SpiceBerry
    SweetPea
    */
            Random rand = new Random();
            int numOfForaging = rand.nextInt(8) + 7;

            for (int k = 0; k < numOfForaging; k++) {
                int RandomType = rand.nextInt(5);
                int x = rand.nextInt(64);
                int y = rand.nextInt(40);
                while (xLocations.contains(x) && yLocations.contains(y)) {
                    x = rand.nextInt(64);
                    y = rand.nextInt(40);
                }

                if (RandomType == 0) {
                    foragings.add(new Foraging(x, y, "FiddleheadFern"));
                } else if (RandomType == 1) {
                    foragings.add(new Foraging(x, y, "Grape"));
                } else if (RandomType == 2) {
                    foragings.add(new Foraging(x, y, "RedMushroom"));
                } else if (RandomType == 3) {
                    foragings.add(new Foraging(x, y, "SpiceBerry"));
                } else if (RandomType == 4) {
                    foragings.add(new Foraging(x, y, "SweetPea"));
                }

                xLocations.add(x);
                yLocations.add(y);
            }

        }else if(Fall){
                /*
                Blackberry
                Chanterelle
                Hazelnut
                PurpleMushroom
                WildPlum
                */
            Random rand = new Random();
            int numOfForaging = rand.nextInt(8) + 7;

            for (int k = 0; k < numOfForaging; k++) {
                int RandomType = rand.nextInt(5);
                int x = rand.nextInt(64);
                int y = rand.nextInt(40);
                while (xLocations.contains(x) && yLocations.contains(y)) {
                    x = rand.nextInt(64);
                    y = rand.nextInt(40);
                }

                if (RandomType == 0) {
                    foragings.add(new Foraging(x, y, "Blackberry"));
                } else if (RandomType == 1) {
                    foragings.add(new Foraging(x, y, "Chanterelle"));
                } else if (RandomType == 2) {
                    foragings.add(new Foraging(x, y, "Hazelnut"));
                } else if (RandomType == 3) {
                    foragings.add(new Foraging(x, y, "PurpleMushroom"));
                } else if (RandomType == 4) {
                    foragings.add(new Foraging(x, y, "WildPlum"));
                }

                xLocations.add(x);
                yLocations.add(y);
            }
        }else if(Winter){
                /*
                Crocus
                CrystalFruit
                Holly
                SnowYam
                WinterRoot
                */
            Random rand = new Random();
            int numOfForaging = rand.nextInt(8) + 7;

            for (int k = 0; k < numOfForaging; k++) {
                int RandomType = rand.nextInt(5);  // 5 item
                int x = rand.nextInt(64);
                int y = rand.nextInt(40);
                while (xLocations.contains(x) && yLocations.contains(y)) {
                    x = rand.nextInt(64);
                    y = rand.nextInt(40);
                }

                if (RandomType == 0) {
                    foragings.add(new Foraging(x, y, "Crocus"));
                } else if (RandomType == 1) {
                    foragings.add(new Foraging(x, y, "CrystalFruit"));
                } else if (RandomType == 2) {
                    foragings.add(new Foraging(x, y, "Holly"));
                } else if (RandomType == 3) {
                    foragings.add(new Foraging(x, y, "SnowYam"));
                } else if (RandomType == 4) {
                    foragings.add(new Foraging(x, y, "WinterRoot"));
                }

                xLocations.add(x);
                yLocations.add(y);
            }
        }

        //dane ha
        //Baraye Mahsolate Ghol Peykar
        Plant pp = null;
        Plant pp2 = null;
        Plant pp3 = null;
        for(Plant plant:App.AllPlants){
            for(Plant plant1:App.AllPlants){
                if(plant1.XPlanting == plant.XPlanting && plant1.YPlanting == plant.YPlanting-1 && plant1.plantType == plant.plantType) {
                    for (Plant plant2 : App.AllPlants) {
                        if (plant2.XPlanting == plant.XPlanting-1 && plant2.YPlanting == plant.YPlanting && plant2.plantType == plant.plantType) {
                            for (Plant plant3 : App.AllPlants) {
                                if (plant3.XPlanting == plant.XPlanting-1 && plant3.YPlanting == plant.YPlanting-1 && plant3.plantType == plant.plantType) {
                                    pp = plant;
                                    pp2 = plant2;
                                    pp3 = plant3;
                                    AllGiantPlant.add(new GiantPlant(plant.plantType,plant.XPlanting-1,plant.YPlanting-1,plant.XPlanting,plant.YPlanting));
                                }
                            }
                        }
                    }
                }
            }
        }
        AllPlants.remove(pp);
        AllPlants.remove(pp2);
        AllPlants.remove(pp3);
    }

    public boolean isAdjacentToMachine(String name) {
        return false;
    }

    public Inventory getInventory() {
    }

    public SeasonEnum getSeason() {
    }

    public boolean isAdjacentToBin() {
    }

    public LocalTime getCurrentTime() {
    }
}
