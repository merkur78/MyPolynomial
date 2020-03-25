package section3;

import section1.MySingleLinkedList;
import section1.Node;

public class Polynomial {
	public String name;
	public MySingleLinkedList<Term> terms;

	public Polynomial(String name) {
		this.name = name;
		terms = new MySingleLinkedList<Term>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MySingleLinkedList<Term> getTerms() {
		return terms;
	}

	public void setTerms(MySingleLinkedList<Term> terms) {
		this.terms = terms;
	}

	public void addTerm(int coef, int expo) {
		if(coef == 0)
			return;
		
		Node<Term> p = terms.head;
		Node<Term> before = null;
		
		while(p != null && p.data.expo > expo) {
			before = p;
			p = p.next;
		}
		
		if(p != null && p.data.expo == expo) {	//동일한 항이 존재
			p.data.coef += coef;
			if(p.data.coef == 0) {
				if(before == null) {
					terms.removeFirst();
				} else {
					terms.removeAfter(before);
				}
			}
		} else {//신규항 추가
			Term term = new Term(coef, expo);
			if(before == null)
				terms.addFirst(term);
			else
				terms.addAfter(before, term);
		}
	}

	public int calc(int x) {
		int result = 0;
		Node<Term> p = terms.head;
		
		while(p != null) {
			result += p.data.calc(x);
			p = p.next;
		}
		
		return result;
	}

	@Override
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append(name + "(x) = ");
		Node<Term> p = terms.head;
		
		while(p != null) {
			sb.append(" + " + p.data.toString());
			p = p.next;
		}
		return sb.toString();
	}
}
