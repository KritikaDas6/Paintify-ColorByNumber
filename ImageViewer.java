package com.paintify.editor;

import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.util.HashMap;
import java.awt.Cursor;
import java.awt.Toolkit;


public class ImageViewer extends JScrollPane {
    ImageEditor editor=null;
    DrawingController currentController=null;
    HashMap<String,DrawingController> allControllers=new HashMap<String,DrawingController>();

    public ImageEditor getEditor(){

        return editor;
    }
    public ImageViewer(){
        setPreferredSize(new Dimension(1024, 768));
        editor = new ColorCompareEditor();
        editor.loadImage("/images/ball.png");

        setViewportView(editor);
    }
    public void addBrushController(String key, DrawingController controller){
        allControllers.put(key, controller);

        setController(key);
    }

    public void setController(String key){
        if (currentController!=null){
            editor.removeMouseListener(currentController);
            editor.removeMouseMotionListener(currentController);
        }
        currentController=allControllers.get(key);
        editor.addMouseMotionListener(currentController);
        editor.addMouseListener(currentController);
    }

    public void setCurrentX(int x){
        editor.setCurrentX(x);
    }

    public void setCurrentY(int y){
        editor.setCurrentY(y);
    }
}

