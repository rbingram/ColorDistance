package org.ingram.color.util;

import java.util.ArrayList;

import org.hibernate.Session;
import org.ingram.color.Color;
import org.ingram.color.entity.ColorEntity;
import org.ingram.color.entity.ColorListEntity;
import org.ingram.color.space.RgbColorSpace;

public class ColorListGenerator {
	
	public static final void seedCrayolaList(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
		
			ColorListEntity list1 = (ColorListEntity)session.createQuery(
					"from ColorListEntity where id = ?")
					.setInteger(0, 1).uniqueResult();
		
			if(list1 == null){
				//No color lists exist. Generate a default Crayola list.
				ColorListEntity crayolas = generateCrayolaList();
				session.save(crayolas);
			}
		
			session.getTransaction().commit();
		}finally{
			session.close();
		}
	}
	
	private static final ColorListEntity generateCrayolaList(){
		
		ColorListEntity crayolaList = new ColorListEntity("Crayola");
		
		ArrayList<ColorEntity> colors = new ArrayList<ColorEntity>();
		
		colors.add( new Color( "Mahogany", new RgbColorSpace(205, 74, 74)) );
		colors.add( new Color( "Fuzzy Wuzzy Brown", new RgbColorSpace(204, 102, 102)) );
		colors.add( new Color( "Chestnut", new RgbColorSpace(188,93,88)) );
		colors.add( new Color( "Red Orange", new RgbColorSpace(255,83,73)) );
		colors.add( new Color( "Sunset Orange", new RgbColorSpace(253,94,83)) );
		colors.add( new Color( "Bittersweet", new RgbColorSpace(253,124,110)) );
		colors.add( new Color( "Melon", new RgbColorSpace(253,188,180)) );
		colors.add( new Color( "Outrageous Orange", new RgbColorSpace(255,110,74)) );
		colors.add( new Color( "Vivid Tangerine", new RgbColorSpace(255,160,137)) );
		colors.add( new Color( "Burnt Sienna", new RgbColorSpace(234,126,93)) );
		colors.add( new Color( "Brown", new RgbColorSpace(180,103,77)) );
		colors.add( new Color( "Sepia", new RgbColorSpace(165,105,79)) );
		colors.add( new Color( "Orange", new RgbColorSpace(255,117,56)) );
		colors.add( new Color( "Burnt Orange", new RgbColorSpace(255,127,73)) );
		colors.add( new Color( "Copper", new RgbColorSpace(221,148,117)) );
		colors.add( new Color( "Mango Tango", new RgbColorSpace(255,130,67)) );
		colors.add( new Color( "Atomic Tangerine", new RgbColorSpace(255,164,116)) );
		colors.add( new Color( "Beaver", new RgbColorSpace(159,129,112)) );
		colors.add( new Color( "Antique Brass", new RgbColorSpace(205,149,117)) );
		colors.add( new Color( "Desert Sand", new RgbColorSpace(239,205,184)) );
		colors.add( new Color( "Raw Sienna", new RgbColorSpace(214,138,89)) );
		colors.add( new Color( "Tumbleweed", new RgbColorSpace(222,170,136)) );
		colors.add( new Color( "Tan", new RgbColorSpace(250,167,108)) );
		colors.add( new Color( "Peach", new RgbColorSpace(255,207,171)) );
		colors.add( new Color( "Macaroni and Cheese", new RgbColorSpace(255,189,136)) );
		colors.add( new Color( "Apricot", new RgbColorSpace(253,217,181)) );
		colors.add( new Color( "Neon Carrot", new RgbColorSpace(255,163,67)) );
		colors.add( new Color( "Almond", new RgbColorSpace(239,219,197)) );
		colors.add( new Color( "Yellow Orange", new RgbColorSpace(255,182,83)) );
		colors.add( new Color( "Gold", new RgbColorSpace(231,198,151)) );
		colors.add( new Color( "Shadow", new RgbColorSpace(138,121,93)) );
		colors.add( new Color( "Banana Mania", new RgbColorSpace(250,231,181)) );
		colors.add( new Color( "Sunglow", new RgbColorSpace(255,207,72)) );
		colors.add( new Color( "Goldenrod", new RgbColorSpace(252,217,117)) );
		colors.add( new Color( "Dandelion", new RgbColorSpace(253,219,109)) );
		colors.add( new Color( "Yellow", new RgbColorSpace(252,232,131)) );
		colors.add( new Color( "Green Yellow", new RgbColorSpace(240,232,145)) );
		colors.add( new Color( "Spring Green", new RgbColorSpace(236,234,190)) );
		colors.add( new Color( "Olive Green", new RgbColorSpace(186,184,108)) );
		colors.add( new Color( "Laser Lemon", new RgbColorSpace(253,252,116)) );
		colors.add( new Color( "Unmellow Yellow", new RgbColorSpace(253,252,116)) );
		colors.add( new Color( "Canary", new RgbColorSpace(255,255,153)) );
		colors.add( new Color( "Yellow Green", new RgbColorSpace(197,227,132)) );
		colors.add( new Color( "Inch Worm", new RgbColorSpace(178,236,93)) );
		colors.add( new Color( "Asparagus", new RgbColorSpace(135,169,107)) );
		colors.add( new Color( "Granny Smith Apple", new RgbColorSpace(168,228,160)) );
		colors.add( new Color( "Electric Lime", new RgbColorSpace(29,249,20)) );
		colors.add( new Color( "Screamin Green", new RgbColorSpace(118,255,122)) );
		colors.add( new Color( "Fern", new RgbColorSpace(113,188,120)) );
		colors.add( new Color( "Forest Green", new RgbColorSpace(109,174,129)) );
		colors.add( new Color( "Sea Green", new RgbColorSpace(159,226,191)) );
		colors.add( new Color( "Green", new RgbColorSpace(28,172,120)) );
		colors.add( new Color( "Mountain Meadow", new RgbColorSpace(48,186,143)) );
		colors.add( new Color( "Shamrock", new RgbColorSpace(69,206,162)) );
		colors.add( new Color( "Jungle Green", new RgbColorSpace(59,176,143)) );
		colors.add( new Color( "Caribbean Green", new RgbColorSpace(28,211,162)) );
		colors.add( new Color( "Tropical Rain Forest", new RgbColorSpace(23,128,109)) );
		colors.add( new Color( "Pine Green", new RgbColorSpace(21,128,120)) );
		colors.add( new Color( "Robin Egg Blue", new RgbColorSpace(31,206,203)) );
		colors.add( new Color( "Aquamarine", new RgbColorSpace(120,219,226)) );
		colors.add( new Color( "Turquoise Blue", new RgbColorSpace(119,221,231)) );
		colors.add( new Color( "Sky Blue", new RgbColorSpace(128,218,235)) );
		colors.add( new Color( "Outer Space", new RgbColorSpace(65,74,76)) );
		colors.add( new Color( "Blue Green", new RgbColorSpace(25,158,189)) );
		colors.add( new Color( "Pacific Blue", new RgbColorSpace(28,169,201)) );
		colors.add( new Color( "Cerulean", new RgbColorSpace(29,172,214)) );
		colors.add( new Color( "Cornflower", new RgbColorSpace(154,206,235)) );
		colors.add( new Color( "Midnight Blue", new RgbColorSpace(26,72,118)) );
		colors.add( new Color( "Navy Blue", new RgbColorSpace(25,116,210)) );
		colors.add( new Color( "Denim", new RgbColorSpace(43,108,196)) );
		colors.add( new Color( "Blue", new RgbColorSpace(31,117,254)) );
		colors.add( new Color( "Periwinkle", new RgbColorSpace(197,208,230)) );
		colors.add( new Color( "Cadet Blue", new RgbColorSpace(176,183,198)) );
		colors.add( new Color( "Indigo", new RgbColorSpace(93,118,203)) );
		colors.add( new Color( "Wild Blue Yonder", new RgbColorSpace(162,173,208)) );
		colors.add( new Color( "Manatee", new RgbColorSpace(151,154,170)) );
		colors.add( new Color( "Blue Bell", new RgbColorSpace(173,173,214)) );
		colors.add( new Color( "Blue Violet", new RgbColorSpace(115,102,189)) );
		colors.add( new Color( "Purple Heart", new RgbColorSpace(116,66,200)) );
		colors.add( new Color( "Royal Purple", new RgbColorSpace(120,81,169)) );
		colors.add( new Color( "Purple Mountains Majesty", new RgbColorSpace(157,129,186)) );
		colors.add( new Color( "Violet Purple", new RgbColorSpace(146,110,174)) );
		colors.add( new Color( "Wisteria", new RgbColorSpace(205,164,222)) );
		colors.add( new Color( "Vivid Violet", new RgbColorSpace(143,80,157)) );
		colors.add( new Color( "Fuchsia", new RgbColorSpace(195,100,197)) );
		colors.add( new Color( "Shocking Pink", new RgbColorSpace(251,126,253)) );
		colors.add( new Color( "Pink Flamingo", new RgbColorSpace(252,116,253)) );
		colors.add( new Color( "Plum", new RgbColorSpace(142,69,133)) );
		colors.add( new Color( "Hot Magenta", new RgbColorSpace(255,29,206)) );
		colors.add( new Color( "Purple Pizzazz", new RgbColorSpace(255,29,206)) );
		colors.add( new Color( "Razzle Dazzle Rose", new RgbColorSpace(255,72,208)) );
		colors.add( new Color( "Orchid", new RgbColorSpace(230,168,215)) );
		colors.add( new Color( "Red Violet", new RgbColorSpace(192,68,143)) );
		colors.add( new Color( "Eggplant", new RgbColorSpace(110,81,96)) );
		colors.add( new Color( "Cerise", new RgbColorSpace(221,68,146)) );
		colors.add( new Color( "Wild Strawberry", new RgbColorSpace(255,67,164)) );
		colors.add( new Color( "Magenta", new RgbColorSpace(246,100,175)) );
		colors.add( new Color( "Lavender", new RgbColorSpace(252,180,213)) );
		colors.add( new Color( "Cotton Candy", new RgbColorSpace(255,188,217)) );
		colors.add( new Color( "Violet Red", new RgbColorSpace(247,83,148)) );
		colors.add( new Color( "Carnation Pink", new RgbColorSpace(255,170,204)) );
		colors.add( new Color( "Razzmatazz", new RgbColorSpace(227,37,107)) );
		colors.add( new Color( "Piggy Pink", new RgbColorSpace(253,215,228)) );
		colors.add( new Color( "Jazzberry Jam", new RgbColorSpace(202,55,103)) );
		colors.add( new Color( "Blush", new RgbColorSpace(222,93,131)) );
		colors.add( new Color( "Tickle Me Pink", new RgbColorSpace(252,137,172)) );
		colors.add( new Color( "Pink Sherbet", new RgbColorSpace(247,128,161)) );
		colors.add( new Color( "Maroon", new RgbColorSpace(200,56,90)) );
		colors.add( new Color( "Red", new RgbColorSpace(238,32,77)) );
		colors.add( new Color( "Radical Red", new RgbColorSpace(255,73,108)) );
		colors.add( new Color( "Mauvelous", new RgbColorSpace(239,152,170)) );
		colors.add( new Color( "Wild Watermelon", new RgbColorSpace(252,108,133)) );
		colors.add( new Color( "Scarlet", new RgbColorSpace(252,40,71)) );
		colors.add( new Color( "Salmon", new RgbColorSpace(255,155,170)) );
		colors.add( new Color( "Brick Red", new RgbColorSpace(203,65,84)) );
		colors.add( new Color( "White", new RgbColorSpace(237,237,237)) );
		colors.add( new Color( "Timberwolf", new RgbColorSpace(219,215,210)) );
		colors.add( new Color( "Silver", new RgbColorSpace(205,197,194)) );
		colors.add( new Color( "Gray", new RgbColorSpace(149,145,140)) );
		colors.add( new Color( "Black", new RgbColorSpace(35,35,35)) );	

		
		crayolaList.setColors(colors);
		
		return crayolaList;
	}

	
	
	
}
