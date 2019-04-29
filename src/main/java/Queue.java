import static java.lang.System.arraycopy;

public class Queue {
    private int size;
    private int[] elements = new int[0];
    private int pushIndex;
    private int popIndex;

    public boolean isEmpty() {
        return size - popIndex == 0;
    }

    public void add(int i) {
        if (elements.length <= size - popIndex)
            grow();
        elements[pushIndex++ % elements.length] = i;
        size++;
    }

    public int remove() {
        if (popIndex >= size)
            throw new Underflow();
        return elements[popIndex++ % elements.length];
    }

    private void grow() {
        int[] newElements = new int[elements.length + 1];
        arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    public int getSize() {
        return size - popIndex;
    }

    int getMemory() {
        return elements.length;
    }
}
