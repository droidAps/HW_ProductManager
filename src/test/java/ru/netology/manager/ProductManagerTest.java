package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ProductManagerTest {
    @Mock
    private ProductRepository repository;
    @InjectMocks
    private ProductManager manager = new ProductManager();

    Product one = new Book(1, "Картошка и ее тайная жизнь", 199, "Васильков И.И.");
    Product two = new Book(2, "Samsung навсегда", 99, "Графоманов О.О.");
    Product three = new Book(3, "Жизнь за МКАД", 399, "Графоманов О.О.");
    Product four = new Smartphone(4, "Note 8", 21999, "Samsung");
    Product five = new Smartphone(5, "10", 15999, "Honor");
    Product six = new Book(6, "Жизнь успешных людей", 499, "Переделкин Ж.Ж.");
    Product seven = new Smartphone(7, "Картошкафон", 13100, "Noname");
    Product eight = new Smartphone(8, "12", 7399, "Apple");
    Product nine = new Smartphone(9, "10 Lite", 26999, "Honor");
    Product ten = new Book(10, "10 отверженных", 259, "Пупышкин Е.А.");

    @Test
    void shouldSearchByName() {
        Product[] returned = {one, two, three, four, five, six, seven, eight, nine, ten};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[] {one, seven};
        Product[] actual = manager.searchBy("Картошка");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAuthor() {
        Product[] returned = {one, two, three, four, five, six, seven, eight, nine, ten};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[] {two, three};
        Product[] actual = manager.searchBy("Графоманов О.О.");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAll() {
        Product[] returned = {one, two, three, four, five, six, seven, eight, nine, ten};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[] {two, four};
        Product[] actual = manager.searchBy("Samsung");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByNone() {
        Product[] returned = {one, two, three, four, five, six, seven, eight, nine, ten};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[] {};
        Product[] actual = manager.searchBy("LG");

        assertArrayEquals(expected, actual);
    }
}