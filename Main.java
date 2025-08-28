package oopMasterChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

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
		ArrayList<Meal> order = new ArrayList<>();

		while (true) {
			while (true) {
				System.out.println();
				String scanner = Main2.isValid("Ready to order?(input \"y\" or \"n\")","yn");
				if (scanner.equals("y"))
					break;
			}
			order.add(new Meal());
			boolean isFinished = false;
			while (!isFinished) {
				isFinished = Main2.order(order);
			}
			;
			if(Arrays.stream(order.get(order.size()-1).getOrderArray()).filter(x->x==0).count()==15) {
				order.remove(order.get(order.size()-1));
				continue;
			}
			System.out.println("Thanks for you order");
			System.out.println("Order placed at " + order.get(order.size() - 1).getTime() + "\n");
		}
	}
}

class Main2 {
	public static boolean order(ArrayList order) {

		String scanner = isValid("Input a number(1～15) to order or 0 to finish", "0 1 2 3  4 5 6 7 8 9 10 11 12 13 14 15");
		if (scanner.equals("0")) {
			((Meal) order.get(order.size() - 1)).setTime();
			return true;

		}
		int i= isValid("How many would you like?",99);
		if (scanner.equals("1") || scanner.equals("2")) {
			String burgerType = isValid("Would you like fish or beef?", "1");
			String sideType = isValid("Would you like salad or chips?", "0");
			String drinkType = isValid("Would you like beer or coke?", "0");
			String drinkSize = isValid("Would you like your drink small or large?(input \"s\" or \"l\")", "sl");

			if (scanner.equals("1")) {
				((Meal) order.get(order.size() - 1)).setPrice(1, i, burgerType, sideType, drinkType, drinkSize, null,
						null, null);
				addToppings(order,3,i);
			} else {
				String topping1 = isValid("Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?",
						"0");
				String topping2 = isValid("Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?",
						"0");
				String topping3 = isValid("Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?",
						"0");
				((Meal) order.get(order.size() - 1)).setPrice(2, i, burgerType, sideType, drinkType, drinkSize,
						topping1, topping2, topping3);
				addToppings(order,2,i);
			}
		} else if (Integer.parseInt(scanner) > 6 && Integer.parseInt(scanner) < 12) {
			int toppingCap = ((Meal) order.get(order.size() - 1)).getCount().getToppingCap();
			int toppingCount = ((Meal) order.get(order.size() - 1)).getCount().getToppingCount();
			int burgerCount = 0;
			for (int orderNum = 0; orderNum < 6; orderNum++) {
				burgerCount += ((Meal) order.get(order.size() - 1)).getOrderArray()[orderNum];
			}
			if (burgerCount == 0) {
				System.out.println(
						"You need to order a burger, a delux burger, a combo, or a delux before you add toppings");
			} else if (toppingCap < toppingCount + i) {
				System.out.println(
						"You can't add more than 3 toppings on your each burger or 5 on your each delux burger");
			} else {
				((Meal) order.get(order.size() - 1)).setPrice(Integer.parseInt(scanner), i, null, null, null, null,
						null, null, null);
				((Meal) order.get(order.size() - 1)).getCount().setToppingCap(-i);
			}
		} else if (scanner.equals("3") || scanner.equals("4")) {
			((Meal) order.get(order.size() - 1)).setPrice(Integer.parseInt(scanner), i, null, null, null, null, null,
					null, null);

			addToppings(order,3,i);
		} else if (scanner.equals("5") || scanner.equals("6")) {
			((Meal) order.get(order.size() - 1)).setPrice(Integer.parseInt(scanner), i, null, null, null, null, null,
					null, null);

			addToppings(order,5,i);

		}else {
			((Meal) order.get(order.size() - 1)).setPrice(Integer.parseInt(scanner), i, null, null, null, null, null,
					null, null);
		}
		((Meal) order.get(order.size() - 1)).printOrderDetail();
		return false;

	}

	public static String isValid(String statement, String keyword) {
		while (true) {
			System.out.println(statement);
			String i = new Scanner(System.in).nextLine();
			if(keyword.equals("1")) {
				i=i+" burger";
			}
			if("01".contains(keyword)) {
				Price price=new Price();
				if(price.foodList.contains(i)) {
					return i;
				}
			}
			if (!(keyword.contains(i)) || i.isEmpty() || i.isBlank()) {
				System.out.println("Invalid input");
				continue;
			}
			return i;
		}
	}
	public static int isValid(String statement, int maxNum) {
		while (true) {
			System.out.println(statement);
			String i = new Scanner(System.in).nextLine();
			int j;
			try{j=Integer.parseInt(i);}catch(NumberFormatException k) {
				j=0;
			};
			if (!(j>0&&j<99)) {
				System.out.println("Invalid input");
				continue;
			}
			
			return j;
		}
	}
	public static void addToppings(ArrayList order,int maxNum,int quantity) {
		for (int toppingCount = 0; toppingCount <maxNum; toppingCount++) {
			String answer = isValid(
					(toppingCount == 0 ? "Would you like extra topping?(It's not included to the price, input \"y\" or \"n\")"
							: "Would you like more extra topping?(It's not included to the price, input \"y\" or \"n\")"),
					"yn");
			if (answer.equals("n")) {
				((Meal) order.get(order.size() - 1)).getCount().setToppingCap(quantity * (maxNum - toppingCount));
				break;
			} else {
				String topping = isValid(
						"Choose a topping from tomato,cheese,egg,pickle,or onion as a topping?",
						"0");
				int orderNum = switch (topping) {
				case "tomato" -> 7;
				case "cheese" -> 8;
				case "egg" -> 9;
				case "pickle" -> 10;
				default -> 11;
				};
				((Meal) order.get(order.size() - 1)).setPrice(orderNum, quantity, null, null, null, null, null, null,
						null);
			}
		}
	}

}
