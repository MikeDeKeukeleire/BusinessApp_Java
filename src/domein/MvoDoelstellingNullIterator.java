package domein;

import java.util.Iterator;

public class MvoDoelstellingNullIterator implements Iterator<MvoDoelstellingComponent> {

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public MvoDoelstellingComponent next() {
		return null;
	}

}