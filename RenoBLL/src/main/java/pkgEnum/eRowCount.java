package pkgEnum;

public enum eRowCount {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
	private int iRowCountItems;

	private eRowCount(int iRowCountItems) {
		this.iRowCountItems = iRowCountItems;
	}

	public int getiRowCountItems() {
		return iRowCountItems;
	}
	
}