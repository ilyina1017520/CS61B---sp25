import deque.ArrayDeque61B;

import deque.Deque61B;
import deque.LinkedListDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     /**This method is for test get and addFirst and addLast methods.*/
     public void get_test() {
         Deque61B<Integer> list = new ArrayDeque61B<>();
         Deque61B<String> list2 = new ArrayDeque61B<>();
         list.addFirst(2);
         list.addFirst(1);
         list.addLast(3);
         list.addLast(4);
         assertThat(list.get(0)).isEqualTo(1);
         assertThat(list.get(1)).isEqualTo(2);
         assertThat(list.get(2)).isEqualTo(3);
         assertThat(list.get(3)).isEqualTo(4);
         list2.addFirst("2");
         list2.addFirst("1");
         list2.addLast("3");
         list2.addLast("4");
         assertThat(list2.get(0)).isEqualTo("1");
         assertThat(list2.get(1)).isEqualTo("2");
         assertThat(list2.get(2)).isEqualTo("3");
         assertThat(list2.get(3)).isEqualTo("4");
         assertThat(list.get(4)).isNull();
         assertThat(list.get(5)).isNull();
     }

     @Test
     /**This test if for testing isEmpty().*/
     public void isEmptyAndSize_test() {
         Deque61B<String> list = new ArrayDeque61B<>();
         assertThat(list.isEmpty()).isTrue();
         assertThat(list.size()).isEqualTo(0);
         list.addFirst("2");
         list.addFirst("1");
         list.addLast("3");
         list.addLast("4");
         assertThat(list.isEmpty()).isFalse();
         assertThat(list.size()).isEqualTo(4);
     }

     @Test
    /** This test is for testing toList().*/
    public void toList_test() {
         Deque61B<Integer> list = new ArrayDeque61B<>();
         list.addFirst(2);
         list.addFirst(1);
         list.addLast(3);
         list.addLast(4);

         assertThat(list.toList()).containsExactly(1, 2, 3, 4).inOrder();
    }

    @Test
    /**This test is for testing removeFirst and removeLast*/
    public void remove_test() {
        Deque61B<Integer> list = new ArrayDeque61B<>();
        assertThat(list.removeFirst()).isNull();
        assertThat(list.removeLast()).isNull();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.addLast(4);
        list.removeFirst();
        list.removeLast();
        assertThat(list.toList()).containsExactly(2, 3).inOrder();
    }

    @Test
    /**This test is for testing resizingUp() and can resizingUp takes work when it is in
     * addFirst and addLast.
     */
    public void resizing_test() {
        ArrayDeque61B<Integer> list = new ArrayDeque61B<>();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        list.addLast(8);
        //检查addFirst和addLast中使用resizing的正确性
        //此时size==items.length,调整完后items.length翻倍;
        //用可视化看看结构
        list.addLast(9);
        assertThat(list.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
        assertThat(list.getItems().length).isEqualTo(16);
        ArrayDeque61B<Integer> list2 = new ArrayDeque61B<>();
        list2.addFirst(2);
        list2.addFirst(1);
        list2.resizing();
        assertThat(list2.getItems().length).isEqualTo(4);


    }

    @Test
    /**This test if for testing both ArrayDeque's and LinkedListDeque's Iterable.
     * Sorry for I put both them in ArrayDeque61BTest document , but i ganna do it.
      */
    public void Iterable_test() {
        ArrayDeque61B<Integer> list = new ArrayDeque61B<>();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.addLast(4);
        int j = 1;
        for(int x : list){
            assertThat(x).isEqualTo(j++);
        }

        LinkedListDeque61B<Integer> list2 = new LinkedListDeque61B<>();
        list2.addFirst(2);
        list2.addFirst(1);
        list2.addLast(3);
        list2.addLast(4);
        int q = 1;
        for(int x : list2){
            assertThat(x).isEqualTo(q++);
        }
    }

    @Test
    public void testEqualArrayDeques61B() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);

    }

    @Test
    public void testEqualLinkedListDeques61B() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testToString() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        lld2.addLast(1);
        lld2.addLast(2);
        lld2.addLast(3);

        assertThat(lld1.toString()).isEqualTo("[1, 2, 3]");
        assertThat(lld2.toString()).isEqualTo("[1, 2, 3]");
    }
}
