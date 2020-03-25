package section3;

import java.util.Scanner;

public class MyPolynomialApp {

	private Polynomial[] polynomials = new Polynomial[100];
	int n = 0; //다항식의 개수
	private Scanner kb = new Scanner(System.in);
	
	public MyPolynomialApp() {
		
	}

	public void processCommand() {
		while(true) {
			System.out.print("$ ");
			String command = kb.next();
			if(command.equals("create"))
				handleCreate();
			else if(command.equals("add"))
				handleAdd();
			else if(command.equals("calc"))
				handleCalc();
			else if(command.equals("print"))
				handlePrint();
			else if(command.equals("exit"))
				break;
				
		}
	}

	private void handleCreate() {
		String name = kb.next();
		Polynomial polynomial = new Polynomial(name);
		polynomials[n++] = polynomial;
//		insert(polynomial);
	}

//	private void insert(Polynomial polynomial) {
//		int index = find(polynomial.getName());
//		if(index > -1)
//			polynomials[index] = polynomial;
//		else {
//			if(polynomials.length <= n)
//				reallocate();
//			polynomials[n++] = polynomial;
//		}
//	}

	private void handleAdd() {
		String name = kb.next();
		int coef = kb.nextInt();
		int expo = kb.nextInt();
		
		int index = find(name);
		if(index < 0) {
			System.out.println("No such polynomial.");
			return;
		}
		polynomials[index].addTerm(coef, expo);
	}

	private int find(String name) {
		for (int i = 0; i < n; i++) {
			if(polynomials[i].name.equals(name))
				return i;
		}
		
		return -1;
	}

	private void handleCalc() {
		String name = kb.next();
		int x = kb.nextInt();

		int index = find(name);
		if(index < 0) {
			System.out.println("No such polynomial.");
			return;
		}
		System.out.println(polynomials[index].calc(x));
	}

	private void handlePrint() {
		String name = kb.next();
		int index = find(name);
		if(index < 0) {
			System.out.println("No such polynomial.");
			return;
		}
		System.out.println(polynomials[index].toString());
	}

	public static void main(String[] args) {
		MyPolynomialApp app = new MyPolynomialApp();
		app.processCommand();
	}

}
