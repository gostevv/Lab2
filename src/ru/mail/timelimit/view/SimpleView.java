package ru.mail.timelimit.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import ru.mail.timelimit.view.utils.SpringUtilities;

public class SimpleView implements View
{
    
    @Override
    public JTree getBooksAndChapters() 
    {
        return booksAndChapters;
    }

    @Override
    public JMenuItem getSaveBooksAndChapters() 
    {
        return saveBooksAndChapters;
    }

    @Override
    public JMenuItem getLoadBooksAndChapters() 
    {
        return loadBooksAndChapters;
    }

    @Override
    public JButton getAddBook() 
    {
        return bookForm.addBook;
    }

    @Override
    public JTextField getBookAnnotation() 
    {
        return bookForm.bookAnnotation;
    }

    @Override
    public JTextField getBookId() 
    {
        return bookForm.bookId;
    }

    @Override
    public JTextField getBookTitle() 
    {
        return bookForm.bookTitle;
    }

    @Override
    public JTextField getBookAuthor() 
    {
        return bookForm.bookAuthor;
    }
    
    @Override
    public JTextField getBookIsbn()
    {
        return bookForm.bookIsbn;
    }

    @Override
    public JButton getAddChapter() 
    {
        return chapterForm.addChapter;
    }

    @Override
    public JTextField getChapterId() 
    {
        return chapterForm.chapterId;
    }

    @Override
    public JTextField getBookIdOfChapter() 
    {
        return chapterForm.bookIdOfChapter;
    }

    @Override
    public JTextField getChapterTitle() 
    {
        return chapterForm.chapterTitle;
    }

    @Override
    public JTextField getChapterText() 
    {
        return chapterForm.chapterText;
    }

    @Override
    public JButton getUpdateBook() 
    {
        return bookForm.updateBook;
    }

    @Override
    public JButton getDeleteChapter()
    {
        return deleteChapterForm.delete;
    }
            
    @Override
    public JTextField getDeleteChapterId()
    {
        return deleteChapterForm.chapterIdOrBookId;
    }
    
    @Override
    public JButton getDeleteBook()
    {
        return deleteBookForm.delete;
    }
    
    @Override
    public JTextField getDeleteBookId()
    {
        return deleteBookForm.chapterIdOrBookId;
    }
    
    @Override
    public JButton getUpdateChapter() 
    {
        return chapterForm.updateChapter;
    }
    
    @Override
    public void showErrorMessage(String errorMessage)
    {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }
    
    public SimpleView()
    {
        DefaultMutableTreeNode treeTop = new DefaultMutableTreeNode("Книги");
        booksAndChapters = new JTree(treeTop);
        addBook = new JButton("Добавить книгу");
        addChapter = new JButton("Добавить главу");
        deleteBook = new JButton("Удалить книгу");
        deleteChapter = new JButton("Удалить главу");
        editBook = new JButton("Редактировать книгу");
        editChapter = new JButton("Редактировать главу");

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Операции");
        
        saveBooksAndChapters = new JMenuItem("Сохранить книги и главы в файл");
        loadBooksAndChapters = new JMenuItem("Загрузить книги и главы из файла");
        
        menu.add(saveBooksAndChapters);
        menu.add(loadBooksAndChapters);
        
        menuBar.add(menu);
        
        JPanel booksAndChaptersPanel = new JPanel();
        booksAndChaptersPanel.add(booksAndChapters);
        booksAndChaptersPanel.setPreferredSize(new Dimension(640,480));
        
        JPanel booksAndChaptersOperationsPanel = new JPanel();
        booksAndChaptersOperationsPanel.add(addBook);
        booksAndChaptersOperationsPanel.add(addChapter);
        booksAndChaptersOperationsPanel.add(editBook);
        booksAndChaptersOperationsPanel.add(editChapter);
        booksAndChaptersOperationsPanel.add(deleteBook);
        booksAndChaptersOperationsPanel.add(deleteChapter);
        
        frame = new JFrame("Lab 2 - desktop application");
        frame.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        
        frame.add(booksAndChaptersPanel, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        frame.add(booksAndChaptersOperationsPanel, c);
        
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        chapterForm = new ChapterForm();
        bookForm = new BookForm();
        deleteChapterForm = new DeleteForm();
        deleteBookForm = new DeleteForm();
        
        addBook.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                bookForm.show();
            }
        }); 
         
        addChapter.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                chapterForm.show();
            }
        });
        
        deleteChapter.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                deleteChapterForm.show();
            }
        });
        
        deleteBook.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                deleteBookForm.show();
            }
        });
        
        editBook.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                bookForm.show();
            }
        }); 
        
        editChapter.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                chapterForm.show();
            }
        });
    }
    
    private class BookForm 
    {

        public BookForm()
        {
            frame = new JFrame("Книга");
            JPanel panel = new JPanel(new SpringLayout());
            
            JLabel labelForBookId = new JLabel("ID: ", JLabel.TRAILING);
            panel.add(labelForBookId);
            bookId = new JTextField(10);
            labelForBookId.setLabelFor(bookId);
            panel.add(bookId);
            
            JLabel labelForBookTitle = new JLabel("Название: ", JLabel.TRAILING);
            panel.add(labelForBookTitle);
            bookTitle = new JTextField(10);
            labelForBookTitle.setLabelFor(bookTitle);
            panel.add(bookTitle);
            
            JLabel labelForBookAuthor = new JLabel("Автор: ", JLabel.TRAILING);
            panel.add(labelForBookAuthor);
            bookAuthor = new JTextField(10);
            labelForBookAuthor.setLabelFor(bookAuthor);
            panel.add(bookAuthor);
            
            JLabel labelForIsbn = new JLabel("ISBN: ", JLabel.TRAILING);
            panel.add(labelForIsbn);
            bookIsbn = new JTextField(10);
            labelForIsbn.setLabelFor(bookIsbn);
            panel.add(bookIsbn);
            
            JLabel labelForAnnotation = new JLabel("Аннотация: ", JLabel.TRAILING);
            panel.add(labelForAnnotation);
            bookAnnotation = new JTextField(10);
            labelForAnnotation.setLabelFor(bookAnnotation);
            panel.add(bookAnnotation);
            
            updateBook = new JButton("Обновить книгу");
            addBook = new JButton("Добавить книгу");
            panel.add(updateBook);
            panel.add(addBook);
            
            JButton discard = new JButton("Отменить");
            panel.add(discard);
            
            panel.add(Box.createHorizontalBox());
            
            SpringUtilities.makeCompactGrid(panel,
                    7, 2,
                    6, 6,        
                    6, 6);       
            
            frame.setContentPane(panel);
            
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.pack();
            discard.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent event) 
                {
                    frame.setVisible(false);
                }
            });  
        }
        
        public void show()
        {
            if (!frame.isVisible())
            {
                frame.setVisible(true);
                bookId.setText(EMPTY_STRING);
                bookTitle.setText(EMPTY_STRING);
                bookAuthor.setText(EMPTY_STRING);
                bookIsbn.setText(EMPTY_STRING);
                bookAnnotation.setText(EMPTY_STRING);
            }
        }
        
        final JTextField bookId;
        final JTextField bookTitle;
        final JTextField bookAuthor;
        final JTextField bookIsbn;
        final JTextField bookAnnotation;   
        final JButton updateBook;
        final JButton addBook;
        final JFrame frame;
        private static final String EMPTY_STRING = "";
    }
    
    private class ChapterForm
    {
        public ChapterForm()
        {
            frame = new JFrame("Глава книги");
            JPanel panel = new JPanel(new SpringLayout());
                        
            JLabel labelForChapterId = new JLabel("ID: ", JLabel.TRAILING);
            panel.add(labelForChapterId);
            chapterId = new JTextField(10);
            labelForChapterId.setLabelFor(chapterId);
            panel.add(chapterId);
            
            JLabel labelForBookId = new JLabel("ID книги:", JLabel.TRAILING);
            panel.add(labelForBookId);
            bookIdOfChapter = new JTextField(10);
            labelForBookId.setLabelFor(bookIdOfChapter);
            panel.add(bookIdOfChapter);
            
            JLabel labelForChapterTitle = new JLabel("Название: ", JLabel.TRAILING);
            panel.add(labelForChapterTitle);
            chapterTitle = new JTextField(10);
            labelForChapterTitle.setLabelFor(chapterTitle);
            panel.add(chapterTitle);
            
            JLabel labelForChapterText = new JLabel("Текст главы: ", JLabel.TRAILING);
            panel.add(labelForChapterText);
            chapterText = new JTextField(10);
            labelForChapterText.setLabelFor(chapterText);
            panel.add(chapterText);
            
            updateChapter = new JButton("Обновить главу");
            addChapter = new JButton("Добавить главу");
            panel.add(updateChapter);
            panel.add(addChapter);
            
            JButton discard = new JButton("Отменить");
            panel.add(discard);
            panel.add(Box.createHorizontalBox());
            
            SpringUtilities.makeCompactGrid(panel,
                    6, 2,
                    6, 6,        
                    6, 6);  
            
            frame.setContentPane(panel);
            
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.pack();
            discard.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent event) 
                {
                    frame.setVisible(false);
                }
            });  
        }
        
        public void show()
        {
            if (!frame.isVisible())
            {
                frame.setVisible(true);
                chapterId.setText(EMPTY_STRING);
                bookIdOfChapter.setText(EMPTY_STRING);
                chapterTitle.setText(EMPTY_STRING);
                chapterText.setText(EMPTY_STRING);
            }
        }
        
        final JButton updateChapter;
        final JButton addChapter;
        final JTextField chapterId;
        final JTextField bookIdOfChapter;
        final JTextField chapterTitle;
        final JTextField chapterText;
        final JFrame frame;
        private static final String EMPTY_STRING = "";
    }
    
    private class DeleteForm
    {
        public DeleteForm()
        {
            frame = new JFrame("Удаление");
            JPanel panel = new JPanel(new SpringLayout());
                        
            JLabel labelForChapterIdOrBookId = new JLabel("ID: ", JLabel.TRAILING);
            panel.add(labelForChapterIdOrBookId);
            chapterIdOrBookId = new JTextField(10);
            labelForChapterIdOrBookId.setLabelFor(chapterIdOrBookId);
            panel.add(chapterIdOrBookId);
            
            delete = new JButton("Удалить");
            JButton discard = new JButton("Отменить");
            panel.add(delete);
            panel.add(discard);
            
            SpringUtilities.makeCompactGrid(panel,
                    2, 2,
                    6, 6,        
                    6, 6);  
            
            frame.setContentPane(panel);
            
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.pack();
            discard.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent event) 
                {
                    frame.setVisible(false);
                }
            });  
        }
        
        public void show()
        {
            if (!frame.isVisible())
            {
                frame.setVisible(true);
                chapterIdOrBookId.setText(EMPTY_STRING);
            }
        }
        
        final JButton delete;
        final JTextField chapterIdOrBookId;
        final JFrame frame;
        private static final String EMPTY_STRING = "";
    }
    
    private final JTree booksAndChapters;
    private final JButton addBook;
    private final JButton addChapter;
    private final JButton deleteBook;
    private final JButton deleteChapter;
    private final JButton editBook;
    private final JButton editChapter;
    private final JMenuItem saveBooksAndChapters;
    private final JMenuItem loadBooksAndChapters;
    private TreeNode selected;
    private final JFrame frame;
    private final ChapterForm chapterForm;
    private final BookForm bookForm;
    private final DeleteForm deleteChapterForm;
    private final DeleteForm deleteBookForm;
}
