/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strath.view;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JFileChooser;
import static org.apache.commons.io.FileUtils.listFiles;

/**
 *
 * @author npb11143
 */
public class MainGUI extends javax.swing.JFrame {

    private boolean isProjectSet = false;

    private Collection<File> selectedProject;

    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        projectSelect = new javax.swing.JButton();
        genClassDiagram = new javax.swing.JButton();
        interactGen = new javax.swing.JButton();
        metrics = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        projectSelect.setText("Select Project");
        projectSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                projectSelectMouseReleased(evt);
            }
        });

        genClassDiagram.setText("Generate Class Diagram");
        genClassDiagram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                genClassDiagramMouseReleased(evt);
            }
        });

        interactGen.setText("Generate Interaction Diagram");
        interactGen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                interactGenMouseReleased(evt);
            }
        });
        interactGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interactGenActionPerformed(evt);
            }
        });

        metrics.setText("View Metrics");
        metrics.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                metricsMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(genClassDiagram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(projectSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(interactGen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(metrics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(projectSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genClassDiagram)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(interactGen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(metrics)
                .addContainerGap(171, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genClassDiagramMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genClassDiagramMouseReleased
        // TODO add your handling code here:
        if (selectedProject != null) {
            try {
                FileWriter fw = new FileWriter("classdiagram.txt");
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write("");
                }
            } catch (Exception e) {
            }
            selectedProject.stream().forEach((File file) -> {
                try {
                    FileInputStream in = new FileInputStream(file);
                    CompilationUnit cu;
                    cu = JavaParser.parse(in);
                    new ClassDiagramVisitor().visit(cu, null);
                } catch (FileNotFoundException | ParseException e) {
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            });
        }
    }//GEN-LAST:event_genClassDiagramMouseReleased

    private void projectSelectMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_projectSelectMouseReleased
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File directory = chooser.getSelectedFile();
            String[] extensions = new String[1];
            extensions[0] = "java";
            selectedProject = listFiles(directory, extensions, true);
            isProjectSet = true;
        }
    }//GEN-LAST:event_projectSelectMouseReleased

    private void interactGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interactGenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_interactGenActionPerformed

    private void interactGenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_interactGenMouseReleased
        // TODO add your handling code here:
        if (selectedProject != null) {
            try {
                FileWriter fw = new FileWriter("interaction_diagram.txt");
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write("");
                }
            } catch (Exception e) {
            }
            selectedProject.stream().forEach((File file) -> {
                try {
                    FileInputStream in = new FileInputStream(file);
                    CompilationUnit cu;
                    cu = JavaParser.parse(in);
                    new InteractionDiagramVisitor().visit(cu, null);
                } catch (FileNotFoundException | ParseException e) {
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            });
        }
    }//GEN-LAST:event_interactGenMouseReleased

    private void metricsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_metricsMouseReleased
        if (selectedProject != null) {
            try {
                FileWriter fw = new FileWriter("metrics.txt");
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write("");
                }
            } catch (Exception e) {
            }
            selectedProject.stream().forEach((File file) -> {
                try {
                    FileInputStream in = new FileInputStream(file);
                    CompilationUnit cu;
                    cu = JavaParser.parse(in);
                    new MetricsVisitor().visit(cu, null);
                } catch (FileNotFoundException | ParseException e) {
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            });
        }
    }//GEN-LAST:event_metricsMouseReleased

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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton genClassDiagram;
    private javax.swing.JButton interactGen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton metrics;
    private javax.swing.JButton projectSelect;
    // End of variables declaration//GEN-END:variables

    /**
     * Simple visitor implementation for extracting class information
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {

        private final ArrayList<Clazz> classes = new ArrayList<>();

        private Clazz clazz;

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Object arg) {
            clazz = new Clazz(n.getName());
            if (n.getImplements() != null) {
                String implementsString = "Implements: ";
                for (ClassOrInterfaceType coi : n.getImplements()) {
                    implementsString = implementsString + coi.getName() + ", ";
                }
                clazz.setImplementsString(implementsString.substring(0, implementsString.length() - 2));
            }
            if (n.getExtends() != null) {
                String extendsString = "Extends: ";
                for (ClassOrInterfaceType coi : n.getExtends()) {
                    extendsString = extendsString + coi.getName() + ", ";
                }
                clazz.setExtendsString(extendsString.substring(0, extendsString.length() - 2));
            }
            super.visit(n, arg);
            classes.add(clazz);
            String fullFile = "";
            for (Clazz clazzz : classes) {
                String className = "---------------------------------\n"
                        + "| " + clazzz.getName() + "\n";
                if (clazzz.getImplementsString() != null) {
                    className = className + "| " + clazzz.getImplementsString() + "\n";
                }
                if (clazzz.getExtendsString() != null) {
                    className = className + "| " + clazzz.getExtendsString() + "\n";
                }
                className = className + "|--------------------------------";

                String fieldList = "";
                String methodList = "";
                if(clazzz.getFields() != null) {
                    for (Feeld field : clazzz.getFields()) {
                        fieldList = fieldList + "\n| -" + field.getName()
                                + " : " + field.getType();
                    }
                }
                if(clazzz.getMethods() != null) {
                    for (Methud method : clazzz.getMethods()) {
                        methodList = methodList + "\n| +" + method.getName();
                    }
                }
                fullFile = fullFile + className + fieldList
                        + "\n|---------------------------------" + methodList
                        + "\n------------------------------\n\n";
            }
            try {
                File file = new File("classdiagram.txt");
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(fullFile);
                }
            } catch (IOException e) {
            }
        }

        @Override
        public void visit(FieldDeclaration n, Object arg) {
            ArrayList<Feeld> tempArray;
            if (clazz.getFields() != null) {
                tempArray = clazz.getFields();
            } else {
                tempArray = new ArrayList<>();
            }
            n.getVariables().stream().map((var) -> new Feeld(var.getId().getName())).map((feeld) -> {
                feeld.setType(n.getType());
                return feeld;
            }).forEach((feeld) -> {
                tempArray.add(feeld);
            });
            clazz.setFields(tempArray);
        }

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            Methud methud = new Methud(n.getName());
            BlockStmt methodBody = n.getBody();
            ArrayList<Methud> tempArray;
            if (clazz.getMethods() != null) {
                tempArray = clazz.getMethods();
            } else {
                tempArray = new ArrayList<>();
            }
            tempArray.add(methud);
            clazz.setMethods(tempArray);
        }
    }
    
    private static class MetricsVisitor extends VoidVisitorAdapter {
        
        private int weightedMethodsPerClass = 0;
        
        private Clazz clazz;
        
        private final ArrayList<Clazz> classes = new ArrayList<>();
        
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Object arg) {
            clazz = new Clazz(n.getName());
            if (n.getImplements() != null) {
                String implementsString = "Implements: ";
                for (ClassOrInterfaceType coi : n.getImplements()) {
                    ArrayList<String> temp;
                    if(clazz.getExtendsNames() != null) {
                        temp = clazz.getImplementsNames();
                    } else {
                        temp = new ArrayList<>();
                    }
                    temp.add(coi.getName());
                    clazz.setImplementsNames(temp);
                    implementsString = implementsString + coi.getName() + ", ";
                }
                clazz.setImplementsString(implementsString.substring(0, implementsString.length() - 2));
            }
            if (n.getExtends() != null) {
                String extendsString = "Extends: ";
                for (ClassOrInterfaceType coi : n.getExtends()) {
                    ArrayList<String> temp;
                    if(clazz.getExtendsNames() != null) {  
                        temp = clazz.getExtendsNames();
                    } else {
                        temp = new ArrayList<>();
                    }
                    temp.add(coi.getName());
                    clazz.setExtendsNames(temp);
                    extendsString = extendsString + coi.getName() + ", ";
                }
                clazz.setExtendsString(extendsString.substring(0, extendsString.length() - 2));
            }            
            super.visit(n, arg);
            clazz.setWmc(weightedMethodsPerClass);
            classes.add(clazz);
            ArrayList<String> allExtends = new ArrayList<>();
            for (Clazz clazzz : classes) {
                if(clazzz.getExtendsNames() != null) {
                    allExtends.addAll(clazzz.getExtendsNames());
                }
            }
            for(Clazz clazzz : classes) {
                int noc = 0;
                for(String extend : allExtends) {
                    if(extend.equals(clazzz.getName())) {
                        noc++;
                    }
                }
                clazzz.setNoc(noc);
            }
            System.out.println(allExtends);
            for(Clazz clazzz : classes) {
                System.out.println("Class Name: " + clazzz.getName());
                System.out.println("WMC: " + clazzz.getWmc());
                System.out.println("NOC: " + clazzz.getNoc());
            }
        }
        
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            weightedMethodsPerClass++;
        }
        
        @Override
        public void visit(ConstructorDeclaration n, Object arg) {
            weightedMethodsPerClass++;
        }
    }
    
    private static class InteractionDiagramVisitor extends VoidVisitorAdapter {
        
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Object arg) {
            System.out.println("Class Name: " + n.getName());
            super.visit(n, arg);
        }
        
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            System.out.println("Method name: " + n.getName());
            n.getBody().accept(this, arg); // process the body to pick up object creation
        }

        public void visit(ObjectCreationExpr p, Object arg) {
            System.out.println("New class instance");
            System.out.println("Type: " + p.getType());
        }
        
        @Override
        public void visit(MethodCallExpr n, Object arg) {
            System.out.println("Method Call Expression: " + n.getName());
            System.out.println("Method Call Expression Args: " + n.getArgs());
            System.out.println("Method Call Expression Child Nodes: " + n.getChildrenNodes());
            System.out.println("Method Call Expression Name Expr: " + n.getNameExpr());
            System.out.println("Method Call Expression Parent Node: " + n.getParentNode());
            System.out.println("Method Call Expression Scope: " + n.getScope());
            System.out.println("Method Call Expression Type Args: " + n.getTypeArgs());
            System.out.println("Method Call Expression Data: " + n.getData());
        }
    }
}
