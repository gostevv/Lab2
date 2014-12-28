package ru.mail.timelimit.model;

import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.swing.event.SwingPropertyChangeSupport;
import ru.mail.timelimit.exceptions.BeanAlreadyExistsException;
import ru.mail.timelimit.exceptions.BeanNotFoundException;
import ru.mail.timelimit.model.javabeans.Book;
import ru.mail.timelimit.model.javabeans.Chapter;

public class SimpleModel implements Model
{

    @Override
    public void addBook(int bookId, String title, String author, String isbn, String annotation) throws BeanAlreadyExistsException
    {
        if (idToBook.get(bookId) != null)
        {
            throw new BeanAlreadyExistsException("Книга с Id " + bookId + " уже существует");
        }
        
        Book book = new Book(bookId, title, author, isbn, annotation);
        idToBook.put(bookId, book);
        propertyChangeCaller.firePropertyChange("AddBook", null, book);
    }

    @Override
    public void addChapter(int chapterId, int bookId, String title, String chapterText) throws BeanAlreadyExistsException, BeanNotFoundException
    {
        if (idToChapter.get(chapterId) != null)
        {
            throw new BeanAlreadyExistsException("Глава с Id " + chapterId + " уже существует");
        }
        
        if (idToBook.get(bookId) == null)
        {
            throw new BeanNotFoundException("Попытка вставить главу ссылающуюся на несуществующую книгу с Id " + bookId);
        }
        
        Chapter chapter = new Chapter(chapterId, bookId, title, chapterText);
        idToChapter.put(chapterId, chapter);
        
        Book book = idToBook.get(bookId);
        Collection<Chapter> bookChapters = book.getChapters();
        bookChapters.add(chapter);
        book.setChapters(bookChapters);
        
        propertyChangeCaller.firePropertyChange("AddChapter", null, chapter);
    } 
    
    @Override
    public void updateBook(int bookId, String title, String author, String isbn, String annotation) throws BeanNotFoundException
    {
        deleteBook(bookId);
        try
        {
            addBook(bookId, title, author, isbn, annotation);
        }
        catch (BeanAlreadyExistsException exception)
        {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void updateChapter(int chapterId, int bookId, String title, String chapterText) throws BeanNotFoundException
    {
        deleteChapter(chapterId);
        try
        {
            addChapter(chapterId, bookId, title, chapterText);
        }
        catch (BeanAlreadyExistsException exception)
        {
            throw new RuntimeException(exception);
        }
    } 
    
    @Override 
    public void deleteBook(int bookId) throws BeanNotFoundException
    {
        if (idToBook.get(bookId) == null)
        {
            throw new BeanNotFoundException("Книги с Id " + bookId + " не существует");
        }
        
        for (Chapter chapter : idToBook.get(bookId).getChapters())
        {
            deleteChapter(chapter.getChapterId()); 
        }
        
        Book book = idToBook.remove(bookId);
        
        propertyChangeCaller.firePropertyChange("DeleteBook", book, null);
    }
    
    @Override 
    public void deleteChapter(int chapterId) throws BeanNotFoundException
    {
        if (idToChapter.get(chapterId) == null)
        {
            throw new BeanNotFoundException("Главы с Id " + chapterId + " не существует");
        }
        
        Chapter chapter = idToChapter.remove(chapterId);
        
        Book book = idToBook.get(chapter.getBookId());
        Collection<Chapter> bookChapters = book.getChapters();
        bookChapters.remove(chapter); 
        book.setChapters(bookChapters);
        
        propertyChangeCaller.firePropertyChange("DeleteChapter", chapter, null);
    }
    
    @Override
    public void addListener(PropertyChangeListener propertyChangeListener)
    { 
        propertyChangeCaller = new SwingPropertyChangeSupport(this);
        propertyChangeCaller.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void destroyModel()
    {
        Collection <Integer> idsToDelete = new HashSet(idToBook.keySet());
        for (int bookId : idsToDelete)
        {
            try 
            {
                deleteBook(bookId);
            } 
            catch (BeanNotFoundException exception)
            {
                throw new RuntimeException(exception);
            }
        }
    }
    
    public void loadModel()
    {
        for (Book book : idToBook.values())
        {
            propertyChangeCaller.firePropertyChange("AddBook", null, book);
        }
        for (Chapter chapter : idToChapter.values())
        {
            propertyChangeCaller.firePropertyChange("AddChapter", null, chapter);
        }
    }
    
    private Map<Integer, Book> idToBook = new HashMap<>();
    private Map<Integer, Chapter> idToChapter = new HashMap<>();
    private transient SwingPropertyChangeSupport propertyChangeCaller = new SwingPropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
}
