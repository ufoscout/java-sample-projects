/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.lucene4.domain;

/**
 *
 * @author ufo
 */
public class Book {

    public Long id;
    public String title;
    public String text;
    public String author;
    public Genre genre;

    public Book() {
    }

    public Book(Long id, String title, String text, String author, Genre genre) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.author = author;
		this.genre = genre;
    }

}
