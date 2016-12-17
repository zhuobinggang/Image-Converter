package Image;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by kobako on 2016/12/16.
 * Just a game
 */
public class PictureConverter {

    public static void convertAndOutput
            (String inUrl,String outUrl, Precision precision,
             ColorDepth colorDepth) throws IOException{
        myTry(inUrl,outUrl,precision.getValue(),colorDepth.getValue());
    }

    private static void myTry
            (String inPath,String outPath,int wei,int depth)
            throws IOException{
        BufferedImage src = ImageIO.read(new File(inPath));
        int cols = src.getWidth()/wei;
        int rows = src.getHeight()/wei;

        char[][] out = new char[rows][cols];
        int []rgb = new int[4];//儲存rgb
        int pixel;

        int minHeight,maxHeight,minWidth,maxWidth;
        for(int i=0;i<rows;i++){
            minHeight = i*wei;
            maxHeight = i*wei+wei-1;
            for(int j=0;j<cols;j++){
                minWidth = j*wei;
                maxWidth = j*wei+wei-1;

                //AABB運算
                rgb[1]=rgb[2]=rgb[3] = 0;
                for(int h=minHeight;h<=maxHeight;h++){
                    for(int w=minWidth;w<=maxWidth;w++){
                        pixel = src.getRGB(w,h);

//                        rgb[0] += (pixel & 0xFF000000) >> 24;
                        rgb[1] += (pixel & 0xFF0000) >> 16;
                        rgb[2] += (pixel & 0xFF00) >> 8;
                        rgb[3] += (pixel & 0xFF);
                    }
                }
                double gray = rgb[1]*0.299 + rgb[2]*0.587 + rgb[3]*0.114;

                out[i][j] = gray>(depth*wei*wei)?'0':'1';
            }
        }

        BufferedWriter bw = Files.newBufferedWriter(Paths.get(outPath),Charset.forName("UTF-8"));
        for(int i=0;i<rows;i++){
            bw.write(out[i]);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    @Test
    public void testIt(){
        String in = "C:\\Users\\zhuo\\Desktop\\test.jpg";
        String out = "C:\\Users\\zhuo\\Desktop\\test.txt";
        try {
            PictureConverter.convertAndOutput(in,out,Precision.One_Fifth,ColorDepth.Light);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
