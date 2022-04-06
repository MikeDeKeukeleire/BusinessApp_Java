package domein;

import java.util.Iterator;
import java.util.Stack;

public class MvoDoelstellingIterator implements Iterator<MvoDoelstellingComponent> {
	private Stack<Iterator<MvoDoelstellingComponent>> iteratorStack = new Stack<>();

	public MvoDoelstellingIterator(Iterator<MvoDoelstellingComponent> iterator) {
		iteratorStack.push(iterator);
	}

	@Override
	public boolean hasNext() {
		if (iteratorStack.isEmpty())
			return false;

		Iterator<MvoDoelstellingComponent> iterator = iteratorStack.peek();
		if (iterator.hasNext())
			return true;

		iteratorStack.pop();
		return hasNext();
	}

	@Override
	public MvoDoelstellingComponent next() {
		if (!hasNext())
			return null;

		Iterator<MvoDoelstellingComponent> iterator = iteratorStack.peek();
		MvoDoelstellingComponent component = iterator.next();
		if (component instanceof MvoDoelstelling)
			iteratorStack.push(component.createIterator());

		return component;
	}

}