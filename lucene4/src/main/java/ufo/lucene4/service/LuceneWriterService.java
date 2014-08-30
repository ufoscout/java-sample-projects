/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.lucene4.service;

import java.util.List;
import ufo.lucene4.domain.Book;

/**
 *
 * @author ufo
 */
public interface LuceneWriterService {
    
    /**
     * Add/Update new Books to the index
     * @param books 
     */
    void addToIndex(List<Book> books);
    
    /**
     * Remove all the doc in the index
     */
    void deleteAll();
    
}
