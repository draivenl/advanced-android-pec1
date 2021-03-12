package com.draiven.pec1application.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookModel {

    public static final List<BookItem> ITEMS = new ArrayList<BookItem>();

    public static final Map<String, BookItem> ITEM_MAP = new HashMap<String, BookItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createBookItem(i));
//        }

        BookItem book1 = new BookItem(0, "Title1", "Author1", new Date (), "Description", null);
        BookItem book2 = new BookItem(1, "Title2", "Author2", new Date (), "Description2", null);
        ITEMS.add (book1);
        ITEMS.add (book2);

        ITEM_MAP.put(String.valueOf(book1.identificador), book1);
        ITEM_MAP.put(String.valueOf(book2.identificador), book2);

    }

    private static void addItem(BookItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.identificador), item);
    }

    private static BookItem createBookItem(int position) {
        return new BookItem(position, "Item " + position, "autor", new Date(), makeDetails(position), null);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Book: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class BookItem {
        public final int identificador;
        public final String titulo;
        public final String autor;
        public final Date fechaPublicacion;
        public final String descripcion;
        public final String url;


        public BookItem(int identificador, String titulo, String autor, Date fechaPublicacion, String descripcion, String url) {
            this.identificador = identificador;
            this.titulo = titulo;
            this.autor = autor;
            this.fechaPublicacion = fechaPublicacion;
            this.descripcion = descripcion;

            this.url = url;
        }

        @Override
        public String toString() {
            return titulo;
        }
    }
}