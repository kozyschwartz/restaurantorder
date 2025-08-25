package oopMasterChallenge;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
public static void main(String[]args) {
	
		
	
	System.out.println("""
Welcome to BillyPot!
-----Menu-----
1Combo                    2Delux                     Others
Total             22$     Total             28$      3beef burger       15$ 
---------------------     ---------------------      4fish burger       13$
beef burger       15$     delux beef burger 20$      5delux beef burger 20$
chips              5$     chips              5$      6delux fish burger 18$
coke               5$     coke               5$      7tomato topping     2$
combo discount    -3$     3 toppings         3$      8cheese topping     3$
                          combo discount    -5$      9egg topping        4$
                                                    10pickle topping     3$
                                                    11onion topping      3$
                                                    12chips              5$
                                                    13salad              5$
                                                    14coke               5$
                                                    15coke large         8$
                                                    16beer              10$
                                                    17beer large        13$
""");
	ArrayList <Meal>order=new ArrayList<>();
	
	while(true) {while(true) {System.out.println("Ready to order?");
	String scanner=new Scanner(System.in).nextLine();
	if(scanner.equals("yes"))
		break;
	}
	order.add(new Meal());
	boolean isFinished=false;
	while(!isFinished) {isFinished=Main2.order(order);
	};
	System.out.println("Thanks for you order");
	System.out.println("Order placed at "+ order.get(order.size()-1).getTime()+"\n");
	}
}
}
class Main2{
	public static boolean order(ArrayList order) {
		
		String scanner=isValid("Input a number to order or 0 to finish","0123456789101112131415");
		if(scanner.equals("0")) {
			((Meal) order.get(order.size()-1)).setTime();
			return true;
			
		}
		int i=Integer.parseInt(isValid("How many would you like?","123456789"));
		if(scanner.equals("1")||scanner.equals("2")) {
			String burgerType=isValid("Would you like fish or beef?","fishbeef");
			String sideType=isValid("Would you like salad or chips?","saladchips");
			String drinkType=isValid("Would you like beer or coke?","beercoke");
			String drinkSize=isValid("Would you like your drink small or large?","smallarge");
			
			if(scanner.equals("1")){((Meal) order.get(order.size()-1)).setPrice(1,i,burgerType,sideType,drinkType,drinkSize,null,null,null);
				for(int toppingCount=0;toppingCount<4;toppingCount++) {
					String answer=isValid((toppingCount==0?"Would you like extra topping?(It's not included to the price)":"Would you like more extra topping?(It's not included to the price)"),"yesno");
					if(answer.equals("no")){
						((Meal) order.get(order.size()-1)).getCount().setToppingCap(3-toppingCount);
						break;
					}else {
							String topping=isValid("Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?","tomatocheeseggpickleonion");
						int orderNum=switch(topping) {case "tomato"->7;case "cheese"->8;case "egg"->9;case "pickle"->10;default->11;};
						((Meal) order.get(order.size()-1)).setPrice(orderNum,i,null,null,null,null,null,null,null);
					}
				}
			}else{	String topping1=isValid("Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?","tomatocheeseggpickleonion");
				String topping2=isValid("Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?","tomatocheeseggpickleonion");
				String topping3=isValid("Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?","tomatocheeseggpickleonion");
				((Meal) order.get(order.size()-1)).setPrice(2,i,burgerType,sideType,drinkType,drinkSize,topping1,topping2,topping3);
				for(int toppingCount=0;toppingCount<3;toppingCount++) {
					String answer=isValid((toppingCount==0?"Would you like extra topping?(It's not included to the price)":"Would you like more extra topping?(It's not included to the price)"),"yesno");
					if(answer.equals("no")){
						((Meal) order.get(order.size()-1)).getCount().setToppingCap(2-toppingCount);
						break;
					}else {
							String topping=isValid("Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?","tomatocheeseggpickleonion");
						int orderNum=switch(topping) {case "tomato"->7;case "cheese"->8;case "egg"->9;case "pickle"->10;default->11;};
						((Meal) order.get(order.size()-1)).setPrice(orderNum,i,null,null,null,null,null,null,null);
					}
				}
			}
		}else if(Integer.parseInt(scanner)>6&&Integer.parseInt(scanner)<12) {
			int toppingCap=((Meal) order.get(order.size()-1)).getCount().getToppingCap();
			int toppingCount=((Meal) order.get(order.size()-1)).getCount().getToppingCount();
			if(toppingCap==0){
				System.out.println("You need to order a burger, a delux burger, a combo, or a delux before you add toppings");
			}else if(toppingCap<toppingCount+i) {
				System.out.println("You can't add more than 3 toppings on your each burger or 5 on your each delux burger");
			}else {
				((Meal) order.get(order.size()-1)).setPrice(Integer.parseInt(scanner),i,null,null,null,null,null,null,null);
				((Meal) order.get(order.size()-1)).getCount().setToppingCap(-i);
			}
		}else if(scanner.equals("3")||scanner.equals("4")){
			((Meal) order.get(order.size()-1)).setPrice(Integer.parseInt(scanner),i,null,null,null,null,null,null,null);
			
			for(int toppingCount=0;toppingCount<(scanner.equals("3")?4:6);toppingCount++) {
				String answer=isValid(toppingCount==0?"Would you like extra topping?(It's not included to the price)":"Would you like more extra topping?(It's not included to the price)","yesno");
				if(answer.equals("no")){
					((Meal) order.get(order.size()-1)).getCount().setToppingCap(scanner.equals("3")?3:5-toppingCount);
					break;
				}else {String topping=isValid("Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?","tomatocheeseggpickleonion");
					int orderNum=switch(topping) {case "tomato"->7;case "cheese"->8;case "egg"->9;case "pickle"->10;default->11;
				};
				((Meal) order.get(order.size()-1)).setPrice(orderNum,i,null,null,null,null,null,null,null);
			}
		}
			
		}
		((Meal) order.get(order.size()-1)).printOrderDetail();
		return false;
		
		
		}
		public static String isValid(String statement,String keyword) {
			while(true) {System.out.println(statement);
			String i=new Scanner(System.in).nextLine();
			if(!(keyword.contains(i))||i.isEmpty()||i.isBlank()) {
				System.out.println("Invalid input");
				continue;
			}
			return i;
			}
		}
			
	}


