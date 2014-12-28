package ru.mail.timelimit.model;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import ru.mail.timelimit.exceptions.BeanAlreadyExistsException;
import ru.mail.timelimit.exceptions.BeanNotFoundException;

public interface Model extends Serializable
{
    public void addBook(int bookId, String title, String author, String isbn, String annotation) throws BeanAlreadyExistsException;
    public void addChapter(int chapterId, int bookId, String title, String chapterText) throws BeanAlreadyExistsException, BeanNotFoundException;        
    public void updateBook(int bookId, String title, String author, String isbn, String annotation) throws BeanNotFoundException;
    public void updateChapter(int chapterId, int bookId, String title, String chapterText) throws BeanNotFoundException;
    public void deleteBook(int bookId) throws BeanNotFoundException;
    public void deleteChapter(int chapterId) throws BeanNotFoundException;
    public void addListener(PropertyChangeListener propertyChangeListener);
}
