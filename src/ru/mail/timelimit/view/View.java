package ru.mail.timelimit.view;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

public interface View 
{
    public JTree getBooksAndChapters();
    public JMenuItem getSaveBooksAndChapters();
    public JMenuItem getLoadBooksAndChapters();
    
    public JButton getAddBook();
    public JButton getUpdateBook();
    /* Book fields */
    public JTextField getBookAnnotation();
    public JTextField getBookId();
    public JTextField getBookTitle();
    public JTextField getBookIsbn();
    public JTextField getBookAuthor();

    public JButton getAddChapter();
    public JButton getUpdateChapter();
    /* Chapter fields */
    public JTextField getChapterId();
    public JTextField getBookIdOfChapter();
    public JTextField getChapterTitle();
    public JTextField getChapterText();
    
    public JButton getDeleteChapter();
    public JTextField getDeleteChapterId();
    
    public JButton getDeleteBook();
    public JTextField getDeleteBookId();
    
    public void showErrorMessage(String errorMessage);
}
