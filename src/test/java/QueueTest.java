import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private Queue queue;

    @BeforeEach
    void setUp() {
        queue = new Queue();
    }

    @Test
    void newQueue_isEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void afterAdd_isNotEmpty() {
        queue.add(0);
        assertFalse(queue.isEmpty());
    }

    @Test
    void afterAddAndRemove_isEmpty() {
        queue.add(0);
        queue.remove();
        assertTrue(queue.isEmpty());
    }

    @Test
    void after2AddsAndOneRemove_isNotEmpty() {
        queue.add(0);
        queue.add(0);
        queue.remove();
        assertFalse(queue.isEmpty());
    }

    @Test
    void afterAddingX_removesX() {
        queue.add(23);
        assertEquals(23, queue.remove());
    }

    @Test
    void afterAddingXAndY_removesXAndY() {
        queue.add(23);
        queue.add(42);
        assertEquals(23, queue.remove());
        assertEquals(42, queue.remove());
    }

    @Test
    void whenRemovingWithoutAdding_throwsUnderflowException() {
        assertThrows(Underflow.class, () -> queue.remove());
    }

    @Test
    void afterAddingXAndYAndZ_removesX() {
        queue.add(23);
        queue.add(42);
        queue.add(3);
        assertEquals(23, queue.remove());
    }

    @Test
    void tracksSize() {
        queue.add(23);
        assertEquals(1, queue.getSize());
        queue.add(42);
        assertEquals(2, queue.getSize());
        queue.remove();
        assertEquals(1, queue.getSize());
    }

    @Test
    void sizeAndMemoryConsumption() {
        queue.add(23);
        queue.add(42);
        queue.add(3);
        assertEquals(3, queue.getMemory());
        queue.remove();
        queue.add(13);
        assertEquals(3, queue.getMemory());
    }

    @Test
    void testWrap() {
        queue.add(23);
        queue.add(42);
        assertEquals(23, queue.remove());
        assertEquals(42, queue.remove());
        queue.add(3);
        assertEquals(3, queue.remove());
        queue.add(13);
        assertEquals(13, queue.remove());
        assertEquals(2, queue.getMemory());
    }
}
