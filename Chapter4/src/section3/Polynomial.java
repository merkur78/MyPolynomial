package section3;

import java.util.Iterator;

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
		
//		Node<Term> p = terms.head;
//		Node<Term> before = null;
//		
//		while(p != null && p.data.expo > expo) {
//			before = p;
//			p = p.next;
//		}
//		
//		if(p != null && p.data.expo == expo) {	//동일한 항이 존재
//			p.data.coef += coef;
//			if(p.data.coef == 0) {
//				if(before == null) {
//					terms.removeFirst();
//				} else {
//					terms.removeAfter(before);
//				}
//			}
//		} else {//신규항 추가
//			Term term = new Term(coef, expo);
//			if(before == null)
//				terms.addFirst(term);
//			else
//				terms.addAfter(before, term);
//		}
		Iterator<Term> p = terms.iterator();
		Iterator<Term> before = null;
		
		Term t = null;
		int index = 0;
		
		while(p.hasNext()) {
			if(before == null)
				before = terms.iterator();
			else
				before.next();
			
			t = p.next();
			index++;
			if(t.expo <= expo)
				break;
		}
	}

	public int calc(int x) {
		int result = 0;
//		Node<Term> p = terms.head;
//		
//		while(p != null) {
//			result += p.data.calc(x);
//			p = p.next;
//		}
		
//		for (int i = 0; i < terms.getSize(); i++) {
//			Term t = terms.get(i);
//			result += t.calc(x);
//		}

		Iterator<Term> iterator = terms.iterator();
		while(iterator.hasNext()) {
			Term t = iterator.next();
			result += t.calc(x);
		}
		
		return result;
	}

	@Override
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append(name + "(x) = ");
//		Node<Term> p = terms.head;
//		
//		while(p != null) {
//			sb.append(" + " + p.data.toString());
//			p = p.next;
//		}
		Iterator<Term> iterator = terms.iterator();
		while(iterator.hasNext()) {
			Term t = iterator.next();
			sb.append(" + " + t.toString());
		}
		
		return sb.toString();
	}
}
