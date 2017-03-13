
public class List {
	private int[] numbers;
	
	/**
	 * Copy constructor for the List object.
	 * @param copyList The list to be copied.
	 */
	public List(List copyList) {
		//first check if the copy item is null
		if (copyList == null) {
			//let the user know the issue
			System.out.println("Cannot create copy from null object!");
			//exit with a fatal error
			System.exit(0);
		}
		//using the setter
		//copy the number array from the copy item
		setNumbers(copyList.numbers);
	}
	
	/**
	 * Setter for the numbers array.
	 * @param numbers The number array to be set.
	 */
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}
	
	/**
	 * Overrides the default equals object method.
	 * Two lists are equal if the sum of numbers are the same.
	 * @param obj Object that is to be compared against.
	 */
	@Override
	public boolean equals(Object obj) {
		//first check if the obj is null
		if (obj == null) {
			return false;
		}
		//check if the comparing object is of the same type
		else if (obj.getClass() != this.getClass()) {
			return false;
		}
		//now sum the lists and compare;
		else {
			//get the list that was passed in
			List anotherList = (List)obj;\
			//could make this into one method to sum numbers but don't know if I am allowed
			//create variables that will sum numbers array
			//one for each list
			int sumList1 = 0;
			int sumList2 = 0;
			//iterate through each array and sum
			for (int number : this.numbers) {
				sumList1 += number;
			}
			for (int number : anotherList.numbers) {
				sumList2 += number;
			}
			//now compare the summed values
			if (sumList2 == sumList1) {
				//return true if they are equal
				return true;
			}
			else {
				//return false if they are not
				return false;
			}
		}
	}
}
