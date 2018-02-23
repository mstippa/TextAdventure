// Brittany Ross, Morgan Stippa, Bryan Chester
// 4/24/16
// Assignment 4
// ArrayStack class

package Donald_Trump;

public class ArrayStack<T> 
{
	private int top;
	private T[] stack;
	// constructs a new empty ArrayStack with an initial size
	public ArrayStack(int initialCapacity)
	{
		top = 0;
		stack = (T[]) (new Object[initialCapacity]);
	}
	// returns the element from the back of the ArrayStack
	public T pop()
	{
		return stack[top--];
	}
	// adds an element to the top of an ArrayStack
	public void push(T i)
	{
		stack[++top] = i;
	}
	// checks to see if the ArrayStack has any elements
	public boolean isEmpty()
	{
		return (top == -1);
	}
}
