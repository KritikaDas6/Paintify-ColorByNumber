package com.paintify.editor.color;

import java.awt.image.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import com.paintify.editor.DrawingConfig;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPalettePicker extends JPanel{
    HashMap<Color,Integer> palette;

    ColorPalettePicker(){
        super();
    }
     

    private void createPalette(BufferedImage image){
        palette = new HashMap<Color,Integer>();
        for(int x = 0; x< image.getWidth(); x ++){
           for(int y = 0; y < image.getHeight(); y++){
               Color c = new Color(image.getRGB(x, y));

               int roundOffBy = 20;

               int red = (c.getRed()/roundOffBy)*roundOffBy;
               int green = (c.getGreen()/roundOffBy)*roundOffBy;
               int blue = (c.getBlue()/roundOffBy)*roundOffBy;

               Color approxColor = new Color(red,green,blue);
               
                           if (palette.containsKey(approxColor)) {
                   Integer colorCount=palette.get(approxColor);
                   palette.put(approxColor, Integer.valueOf(colorCount.intValue()+1));
               }else
                    palette.put(approxColor, Integer.valueOf(1));
           }
       }
        int i = 1;
       for(Color c:palette.keySet()){
           ColorButton btn = new ColorButton();
           btn.setBackground(c);
           btn.setColor(c);
           i++;
           btn.setSize(new Dimension(50,50));
           btn.setPreferredSize(new Dimension(50,50));
           btn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ColorButton bt= (ColorButton)e.getSource();

                System.out.println(palette.get(bt.getColor()));

                DrawingConfig config=DrawingConfig.getInstance();
                config.setConfig("color.fg", bt.getColor());
            }
           });

           add(btn);
       }

       System.out.println("Pallette is : " + palette.size());
    }

    public ColorPalettePicker(BufferedImage image){
        setPreferredSize(new Dimension(200,200));        
        createPalette(image);
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createEtchedBorder());



        // createBetterPalette(image);
    }
    
}
