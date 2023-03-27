package org.example.hw2;

public interface MyList<E> {
    int size();
    void add(E element);
    void add(int index, E element);

    E get(int index);

    int indexOf(E element);

    void replace(int index, E element);

    void removeByIndex(int index); // по моему полезная функция

    void remove(E element);

    void clear();


//    MyList<E> subList(int fromIndex, int toIndex);
}
