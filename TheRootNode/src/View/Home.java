/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.PostController;
import Controller.UserController;
import Utility.Database;
import Model.Post;
import Utility.LiveClock;
import Utility.Session;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author user
 */
public class Home extends javax.swing.JFrame {
    
    private Database database;
    private PostController postController;
    private UserController userController;

    /**
     * Creates new form Home
     */
    public Home(Database database) {
        this.database = database;
        this.postController = new PostController(database);
        this.userController = new UserController(Session.getCurrentUser(), database);
        initComponents();
        displayPosts();
        runClock();
    }
    
    private void runClock() {
        jPanel3.removeAll();
        
        JLabel clockLabel = new JLabel();
        clockLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(clockLabel, BorderLayout.NORTH);
        getContentPane().add(jPanel3, BorderLayout.NORTH);

        Thread clockThread = new Thread(new LiveClock(clockLabel));
        clockThread.start();
        
        jPanel1.revalidate();
        jPanel1.repaint();       
    }
    
    private void displayPosts() {
        jPanel1.removeAll();
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        ArrayList<Post> posts = postController.getAllPosts();

        for (Post post : posts) {         
            JLabel userLabel = new JLabel("Anonymous " + post.getUser().getID());
            userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            userLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

            JLabel dateLabel = new JLabel(post.getFormattedDateTime());
            dateLabel.setFont(new Font("Segoe UI", Font.ITALIC, 10));
            dateLabel.setForeground(Color.GRAY);

            JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            headerPanel.setBackground(Color.WHITE);
            headerPanel.add(userLabel);
            headerPanel.add(dateLabel);
            
            JLabel titleLabel = new JLabel("" + post.getTitle());
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

            int likeCount = postController.getLikeCount(post.getID());
            JLabel likesLabel = new JLabel("" + likeCount);

            JButton likeButton = new JButton("Like");
            likeButton.setPreferredSize(new Dimension(60, 25));

            if (postController.hasUserLikedPost(Session.getCurrentUser().getID(), post.getID())) {
                likeButton.setText("Unlike");
            }

            likeButton.addActionListener(e -> {
                if (postController.toggleLike(Session.getCurrentUser().getID(), post.getID())) {
                    if (postController.hasUserLikedPost(Session.getCurrentUser().getID(), post.getID())) {
                        likeButton.setText("Unlike");
                    } else {
                        likeButton.setText("Like");
                    }

                    int newLikeCount = postController.getLikeCount(post.getID());
                    likesLabel.setText("" + newLikeCount);
                }
            });
            
            JButton reportButton = new JButton("Report");
            reportButton.setPreferredSize(new Dimension(65, 25));
            
            reportButton.addActionListener(e -> {
                if (post.isReported() != true) {
                    postController.reportPost(post.getID());
                    JOptionPane.showMessageDialog(this, "Post reported.");
                }
            });
            
            JButton deleteButton = new JButton("Delete");
            deleteButton.setPreferredSize(new Dimension(65, 25));
            
            deleteButton.addActionListener(e -> {
                postController.deletePost(post.getID());
                JOptionPane.showMessageDialog(this, "Post deleted.");
                displayPosts();
            });

            JTextArea contentLabel = new JTextArea(post.getContent());
            contentLabel.setLineWrap(true);
            contentLabel.setWrapStyleWord(true);
            contentLabel.setEditable(false);
            contentLabel.setOpaque(false);
            contentLabel.setBorder(null);
            
            int fixedWidth = 420;
            contentLabel.setSize(new Dimension(fixedWidth, Short.MAX_VALUE));
            Dimension preferredSize = contentLabel.getPreferredSize();

            contentLabel.setPreferredSize(new Dimension(fixedWidth, preferredSize.height));
            contentLabel.setMaximumSize(new Dimension(fixedWidth, preferredSize.height));

            JPanel likePanel = new JPanel();
            likePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
            likePanel.add(likesLabel);
            likePanel.add(likeButton);

            likePanel.setBackground(java.awt.Color.white);
            
            if ("administrator".equals(userController.getUserRole(Session.getCurrentUser().getID()))) {
                deleteButton.setVisible(true);
                likePanel.add(deleteButton);
            } else {
                deleteButton.setVisible(false);
                likePanel.add(reportButton);
            }
            
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(headerPanel, BorderLayout.NORTH);
            contentPanel.add(titleLabel, BorderLayout.CENTER);
            contentPanel.add(contentLabel, BorderLayout.SOUTH);
            contentPanel.setBackground(java.awt.Color.white);
            contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BorderLayout());
            postPanel.add(contentPanel, BorderLayout.NORTH);
            postPanel.add(likePanel, BorderLayout.SOUTH);

            int padding = 10;
            postPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
            postPanel.setBackground(java.awt.Color.white);

            jPanel1.add(postPanel);
            
            jPanel1.add(Box.createVerticalStrut(10));
        }

        jPanel1.revalidate();
        jPanel1.repaint();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TheRootNode");
        jLabel2.setAlignmentY(0.0F);

        jButton1.setText("Post");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        jButton2.setText("Report");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jPanel3.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(337, 337, 337)
                                .addComponent(jLabel2)))
                        .addGap(0, 205, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(276, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PostPage post = new PostPage();
        post.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if ("moderator".equals(userController.getUserRole(Session.getCurrentUser().getID())) ||
            "administrator".equals(userController.getUserRole(Session.getCurrentUser().getID()))) {
            ReportPage report = new ReportPage();
            report.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Access Denied.");
        }
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Database database = new Database();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home(database).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
