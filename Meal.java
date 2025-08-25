package oopMasterChallenge;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Meal {
	private LocalDateTime time;
	private int[] menu=new int[15];
	private ArrayList<Combo> combo=new ArrayList<>();
	private int totalPrice;
	private Price price=new Price();
	private Count count=new Count();

	public void printOrderDetail() {
		System.out.println("\n-----Your order-------------------------");
		for(int w=0;w<menu.length;w++) {
			if(menu[w]==0) {
				continue;
			}
			switch(w) {
			case 0->{if(menu[0]>0||menu[1]>0)
			for(Combo x:combo) {System.out.printf("%2d×%s",x.getQuantity(),x.getClass().getSimpleName());
			System.out.println(" ".repeat(32-x.getClass().getSimpleName().length())+x.getPrice()+"$");
			for(String y:x.getComboType()) {
			if(y==null) {break;}else if(y.equals(x.getComboType()[0])) {System.out.printf("%5d×%s",x.getQuantity(),y+" burger");System.out.printf(" ".repeat(18-y.length()) +"%3d$%n",x.getQuantity()*(y.equals("beef")?15:13));
			}else {int i;if(price.getFoodList().indexOf(y)>3&&price.getFoodList().indexOf(y)<9) {i=1;}else {i=(int)price.getMenuList().get(price.getFoodList().indexOf(y));};
			System.out.printf("%5d×%s",menu[w],y);System.out.printf("%s%3d$%n"," ".repeat(25-y.length()),x.getQuantity()*i);}
			}System.out.printf("%5d×combo discount          %4d$%n",x.getQuantity(),-x.getQuantity()*(x.getClass().getSimpleName().equals("Combo")?3:5));}
			}
			case 1->System.out.print("");
			default->{String i=(String)price.getFoodList().get(w-2);
			System.out.printf("%2d×%s",menu[w],i);
			int j=(int)price.getMenuList().get(w-2)*menu[w];
			System.out.printf("%s%5d$%n"," ".repeat(29-i.length()),j);}
			}
		}
		System.out.printf("----------------------------------------%nTotal%33d$%n%n",totalPrice);
	}
	public void setTime() {
		time=LocalDateTime.now();
		
	}
	public LocalDateTime getTime() {
		return time;
	}
	
	
	public void setPrice(int mealNum,int quantity,String burgerType,String sideType,String drinkType,String drinkSize,String topping1,String topping2,String topping3) {
		
		menu[mealNum-1]=switch(mealNum) {
		case 1-> {combo.add(new Combo(quantity,burgerType,sideType,drinkType,drinkSize));yield menu[0]=+quantity;}
		case 2-> {combo.add(new Delux(quantity,burgerType,sideType,drinkType,drinkSize,topping1,topping2,topping3));yield menu[0]=+quantity;}
        default->menu[mealNum-1]+quantity;
		};
		totalPrice=totalPrice+switch(mealNum) {
		case 1,2->combo.get(combo.size()-1).getPrice();
		default->(int)price.getMenuList().get(mealNum-3)*quantity;
		};
		
	}
	public Count getCount() {
		return count;
	}
	
	}
	class Combo{
		protected String[]comboType=new String[6];
		protected int price;
		private int quantity;
		public Combo(int quantity,String burgerType,String sideType,String drinkType,String drinkSize) {
			this.quantity=quantity;
			comboType[0]=burgerType;
			comboType[1]=sideType;
			comboType[2]=drinkType+(drinkSize.equals("small")?"":" "+drinkSize);
			int i=comboType[0].equals("fish")?-2:0;
			int j=comboType[2].equals("coke")?0:comboType[2].equals("coke large")?3:comboType[2].equals("beer")?5:8;
			price=22+i+j;
			
		}
		public String[] getComboType() {
			return comboType;
		}
		public int getPrice() {
			return price*quantity;
		}
		public int getQuantity() {
			return quantity;
		}
		
	}
	class Delux extends Combo{
		public Delux(int quantity,String burgerType,String sideType,String drinkType,String drinkSize,String topping1,String topping2,String topping3) {
			super(quantity,burgerType,sideType,drinkType,drinkSize);
			super.comboType[0]="delux "+burgerType+ " burger";
			super.comboType[3]=topping1;
			super.comboType[4]=topping2;
			super.comboType[5]=topping3;
			super.price=super.price+6;
		}
		
	}

class Price{
	private ArrayList<String> foodList=new ArrayList();
	private ArrayList<Integer>menuList=new ArrayList<>();
	public Price(){foodList.add("beef burger");foodList.add("fish burger");foodList.add("delux beef burger");foodList.add("delux fish burger");foodList.add("tomato");foodList.add("cheese");foodList.add("egg");foodList.add("pickle");foodList.add("onion");foodList.add("chips");foodList.add("salad");foodList.add("coke");foodList.add("coke large");foodList.add("beer");foodList.add("beer large");
	menuList.add(15);menuList.add(13);menuList.add(20);menuList.add(18);menuList.add(2);menuList.add(3);menuList.add(4);menuList.add(3);menuList.add(3);menuList.add(5);menuList.add(5);menuList.add(5);menuList.add(8);menuList.add(10);menuList.add(13);}
public ArrayList getMenuList(){
	return menuList;
}
public ArrayList getFoodList() {
	return foodList;
}
}
class Count{
	private int toppingCount;
	private int toppingCap;
	public int getToppingCount() {
		return toppingCount;
	}
	public int getToppingCap() {
		return toppingCap;
	}
	public void setToppingCount(int num) {
		toppingCount+=num;
	}
	public void setToppingCap(int num) {
		toppingCap+=num;
	}
	
}

