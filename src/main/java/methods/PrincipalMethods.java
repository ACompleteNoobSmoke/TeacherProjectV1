package methods;

import gui.Menu;

public class PrincipalMethods {
	
	static Menu menu = new Menu();
	Methods teacherMethods = new Methods();
	
	public int principalChoice() {
		int pick = 0;
		while(pick <= 0 || pick > 6) {
			menu.principalMenuDisplay();
			pick = InputMethods.getInt();
		}
		System.out.println("");
		return pick;
	}
	
	public void principalOptions() {
		int optionPicked = principalChoice();
		switch(optionPicked) {
		case 1: teacherMethods.register();
		}
	}

}
