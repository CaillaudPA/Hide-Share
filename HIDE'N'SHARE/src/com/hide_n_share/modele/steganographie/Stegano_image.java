package com.hide_n_share.modele.steganographie;

import android.graphics.*;

import com.hide_n_share.android.utilitaire.Data;
import com.hide_n_share.modele.classeStatic.*;
import com.hide_n_share.modele.steganographie.Color;


//import javax.imageio.*;
//import java.awt.image.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Stegano_image extends Steganographie{


    private byte[] octetsReserver={100};
    private final int tailleReserverDebut=octetsReserver.length*8;
    private final int tailleBitsLongueurLettre=32;
    private final int tailleTotalReserver = tailleBitsLongueurLettre+tailleReserverDebut;

    private Set<String> formatCompatible = new HashSet<String>();
    private Set<String> formatConvertissable = new HashSet<String>();


    public Stegano_image(String lettre, String enveloppe){
        super(lettre,enveloppe);
        formatCompatible.add("bmp");
        formatCompatible.add("png");
        //formatCompatible.add("gif");
        //formatConvertissable.add("gif");
        formatConvertissable.add("jpg");
        formatConvertissable.add("jpeg");
    }

    //i le numeros de l'algo a utilisé
    //1= remplacer le bit de poids faible de chaque couleur
    public void dissimulerDonnee(String cheminEnveloppeModifier, boolean compresserLettre, String mdp){
        if ((this.verificationCompabilite(compresserLettre, mdp))) {
            try{
                Bitmap enveloppeA = BitmapFactory.decodeFile(this.enveloppe.getEnveloppe());
                Bitmap enveloppe = enveloppeA.copy(Bitmap.Config.ARGB_8888, true);


                byte[] lettre = GestionFichier.fichierEnFlux(this.lettre.getLettre());
                if(!(mdp.equals(""))){
                    lettre = Cryptage.chiffrement(lettre, mdp.getBytes());
                }

                if(compresserLettre){
                    System.out.println("avant compression :"+lettre.length+" octets");
                    lettre = Compresser.compression(lettre);
                    System.out.println("après compression : "+lettre.length+" octets");
                    octetsReserver[0]=101;
                }


                lettre = this.insererTableau(intEnQuatreOctets(lettre.length+(tailleTotalReserver/8)),lettre);
                lettre = this.insererTableau(octetsReserver,lettre);

                int[] lettreEnSuiteDeBits = this.bytesEnInt(lettre);

                int curseurBit = 0;
                for(int y = 0;y<enveloppe.getHeight();y++){
                    for (int x=0;x<enveloppe.getWidth();x++){
                        Color tmpCouleur = new Color(enveloppe.getPixel(x,y));

                        int red = this.decallerBit(tmpCouleur.getRed());
                        int green = this.decallerBit(tmpCouleur.getGreen());
                        int blue = this.decallerBit(tmpCouleur.getBlue());

                        red += this.recupererBit(lettreEnSuiteDeBits[curseurBit],0);

                        curseurBit++;
                        if(curseurBit==lettreEnSuiteDeBits.length){
                            Color newColor = new Color(red,tmpCouleur.getGreen(),tmpCouleur.getBlue());
                            enveloppe.setPixel(x,y,newColor.getRGB());
                            x = enveloppe.getWidth();
                            y = enveloppe.getHeight();
                            break;
                        }

                        green += this.recupererBit(lettreEnSuiteDeBits[curseurBit],0);

                        curseurBit++;
                        if(curseurBit==lettreEnSuiteDeBits.length){
                            Color newColor = new Color(red,green,tmpCouleur.getBlue());
                            enveloppe.setPixel(x,y,newColor.getRGB());
                            x = enveloppe.getWidth();
                            y = enveloppe.getHeight();
                            break;
                        }

                        blue += this.recupererBit(lettreEnSuiteDeBits[curseurBit],0);

                        Color newColor = new Color(red,green,blue);
                        enveloppe.setPixel(x,y,newColor.getRGB());
                        curseurBit++;

                        //Color testCouleur = new Color(enveloppe.getRGB(x,y));
                        //System.out.println(testCouleur.getRed()+"||"+testCouleur.getGreen()+"||"+testCouleur.getBlue()+"//"+(curseurBit));
                        if(curseurBit==lettreEnSuiteDeBits.length){
                            x = enveloppe.getWidth();
                            y = enveloppe.getHeight();
                        }
                    }
                }

                System.out.println("taille de la lettre en octets: "+(lettreEnSuiteDeBits.length-tailleTotalReserver)/8 +" + "+tailleTotalReserver/8);


                //on r�cup�re l'extension du nom de l'enveloppe
                //String[] tmpString = this.enveloppe.getEnveloppe().split("\\.");
                //tmpString[0] correspond au nom du fichier, tmpString[1] a l'extension du fichier
                File outputfile = new File(cheminEnveloppeModifier);
                //ImageIO.write(enveloppe, tmpString[1], outputfile);

                ecrireImage(outputfile, enveloppe);


            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public int recupererBit(int octet, int position){
        return (octet << 31-position)>>>31;
    }

    public int decallerBit(int octet){
        int tmp;
        tmp =octet>>>1;
        tmp =tmp<<1;
        return tmp;
    }

    //convertie un int en un tableau de 4 octets (= 32 bits)
    //byte[0] correspond aux bits de poid faible
    public byte[] intEnQuatreOctets(int a ){
        byte[] tmp = ByteBuffer.allocate(4).putInt(a).array();
        byte[] aRetourner = new byte[4];
        aRetourner[0] = tmp[3];
        aRetourner[1] = tmp[2];
        aRetourner[2] = tmp[1];
        aRetourner[3] = tmp[0];
        return aRetourner;
    }

    public byte[] insererTableau(byte[] debut,byte[] fin){
        ArrayList<Byte> tmp = new ArrayList<Byte>();
        for(int i = 0;i<debut.length;i++){
            tmp.add(debut[i]);
        }

        for(int j =0;j<fin.length;j++){
            tmp.add(fin[j]);
        }

        byte[] aRetourner= new byte[tmp.size()];
        for(int k =0;k<tmp.size();k++){
            aRetourner[k] = tmp.get(k);
        }
        return aRetourner;
    }

    //octets[0] correspond au bits de poid faible
    public int octetsEnInteger(byte[] octets){
        int aRetourner = 0;
        for(int i =octets.length-1;i>=0;i--){
            aRetourner = (aRetourner << 8)| (octets[i] & 0xFF);
        }
        return aRetourner;
    }

    public boolean verificationCompabilite(boolean compresser, String mdp){
        //test fichier existant
        if(!new File(lettre.getLettre()).exists()){
            System.out.println("lettre inéxistant");
            return false;
        }
        if (!new File(enveloppe.getEnveloppe()).exists()) {
            System.out.println("enveloppe inéxistant");
            return false;
        }

        //chiffrement et compression si il y a lieu
        byte[] lettreTmp = GestionFichier.fichierEnFlux(this.lettre.getLettre());//.length+tailleTotalReserver
        if(!(mdp.equals(""))){
            lettreTmp = Cryptage.chiffrement(lettreTmp, mdp.getBytes());
        }
        if(compresser){
            lettreTmp = Compresser.compression(lettreTmp);
        }


        String[] tmpStringA = this.enveloppe.getEnveloppe().split("/");
        String[] tmpString = tmpStringA[tmpStringA.length-1].split("\\.");
        if (tmpString.length ==2 ){
            //si le format d'image n'est pas compatible mais peut etre converti en bmp
            if(formatConvertissable.contains(tmpString[1])){
                System.out.println("le format de l'image n'est pas compatible, il sera donc converti en format png, (penser a changer l'extension de l'image modifier !)");
                File enveloppeTmp = new File(Data.imageConvertie);


                try{
                    Bitmap envTest = BitmapFactory.decodeFile(enveloppe.getEnveloppe());


                    //ImageIO.write(envTest,"bmp", enveloppeTmp);

                    ecrireImage(enveloppeTmp, envTest);

                    enveloppe.setEnveloppe(Data.imageConvertie);

                    while(!enveloppeTmp.exists()){System.out.println(enveloppeTmp.getPath());}

                    envTest = BitmapFactory.decodeFile(enveloppe.getEnveloppe());

                    int nbrPixel = envTest.getHeight()*envTest.getWidth();

                    if(lettreTmp.length > (nbrPixel*3-tailleBitsLongueurLettre)/8){
                        System.out.println("taille de la lettre trop grande, l'enveloppe ne peut que contenir au maximum: "+(nbrPixel*3-32)/8+" octets");
                        System.out.println("la lettre actuelle fait: "+lettreTmp.length+" octets");
                        return false;
                    }else{
                        return true;
                    }
                }catch(Exception e){
                    System.out.println("une erreur est parvenue lors de la convertion de l'image: ");
                    System.out.println(e);
                    return false;
                }
            }else if(formatCompatible.contains(tmpString[1])){
                try{
                    Bitmap envTest = BitmapFactory.decodeFile(enveloppe.getEnveloppe());

                    int nbrPixel = envTest.getHeight()*envTest.getWidth();

                    if(lettreTmp.length > (nbrPixel*3-tailleBitsLongueurLettre)/8){
                        System.out.println("taille de la lettre trop grande, l'enveloppe ne peut que contenir au maximum: "+(nbrPixel*3-32)/8+" octets");
                        System.out.println("la lettre actuelle fait: "+lettreTmp.length+" octets");
                        return false;
                    }else{
                        return true;
                    }

                }catch(Exception e){
                    System.out.println("une erreur est parvenue lors de l'ouverture de l'image: ");
                    System.out.println(e);
                    return false;
                }

            }else{
                System.out.println("le format de l'image n'est pas connue");
                return false;
            }
        }else{
            System.out.println("le nom de l'enveloppe doit contenir l'extension précédé d'un point");
            return false;
        }
    }


    public void devoilerDonnee(String cheminEnveloppe,String cheminLettre, String mdp) {
        boolean lettreCompresser = false;
        try{
            Bitmap enveloppe = BitmapFactory.decodeFile(cheminEnveloppe);


            Color tmpColor = null;
            int red = 0;
            int green = 0;
            int blue = 0;



            int curseurBits=0;
            int[] tailleLettreBit = new int[tailleBitsLongueurLettre];
            int[] partieInurilise = new int[tailleReserverDebut];
            for(int y = 0;y<enveloppe.getHeight();y++){
                for(int x = 0;x<enveloppe.getWidth();x++){
                    tmpColor = new Color(enveloppe.getPixel(x, y));
                    red = tmpColor.getRed();
                    green = tmpColor.getGreen();
                    blue = tmpColor.getBlue();

                    if(curseurBits<tailleReserverDebut){
                        partieInurilise[curseurBits] = this.recupererBit(red,0);
                        curseurBits++;
                    }else if(curseurBits>=tailleReserverDebut && curseurBits<tailleTotalReserver){
                        tailleLettreBit[curseurBits-tailleReserverDebut] = this.recupererBit(red,0);
                        curseurBits++;
                    }else{
                        x = enveloppe.getWidth();
                        y = enveloppe.getHeight();
                        break;
                    }

                    if(curseurBits<tailleReserverDebut){
                        partieInurilise[curseurBits] = this.recupererBit(green,0);
                        curseurBits++;
                    }else if(curseurBits>=tailleReserverDebut && curseurBits<tailleTotalReserver){
                        tailleLettreBit[curseurBits-tailleReserverDebut] = this.recupererBit(green,0);
                        curseurBits++;
                    }else{
                        x = enveloppe.getWidth();
                        y = enveloppe.getHeight();
                        break;
                    }

                    if(curseurBits<tailleReserverDebut){
                        partieInurilise[curseurBits] = this.recupererBit(blue,0);
                        curseurBits++;
                    }else if(curseurBits>=tailleReserverDebut && curseurBits<tailleTotalReserver){
                        tailleLettreBit[curseurBits-tailleReserverDebut] = this.recupererBit(blue,0);
                        curseurBits++;
                    }else{
                        x = enveloppe.getWidth();
                        y = enveloppe.getHeight();
                        break;
                    }
                }
            }
            int tailleLettre = (octetsEnInteger(intEnBytes(tailleLettreBit))-(tailleTotalReserver/8));
            int partieInutile = octetsEnInteger(intEnBytes(partieInurilise));
            System.out.println("partie inutilisée: "+partieInutile);
            if(partieInutile == 101){
                lettreCompresser = true;
            }else if(partieInutile != 100){
                throw new Exception("erreur, cette image ne comporte pas de données cachée");
            }


            System.out.println("taille de la lettre: "+tailleLettre);

            curseurBits=0;
            int[] fichierCacherBits = new int[tailleLettre*8];
            for(int y = 0;y<enveloppe.getHeight();y++){
                for(int x = 0;x<enveloppe.getWidth();x++){
                    tmpColor = new Color(enveloppe.getPixel(x, y));
                    red = tmpColor.getRed();
                    green = tmpColor.getGreen();
                    blue = tmpColor.getBlue();

                    if(curseurBits<tailleLettre*8+tailleTotalReserver){
                        if(curseurBits>=tailleTotalReserver){
                            fichierCacherBits[curseurBits-tailleTotalReserver] = this.recupererBit(red,0);
                        }
                        curseurBits++;
                    }else{
                        x = enveloppe.getWidth();
                        y = enveloppe.getHeight();
                        break;
                    }

                    if(curseurBits<tailleLettre*8+tailleTotalReserver){
                        if(curseurBits>=tailleTotalReserver){
                            fichierCacherBits[curseurBits-tailleTotalReserver] = this.recupererBit(green,0);
                        }
                        curseurBits++;
                    }else{
                        x = enveloppe.getWidth();
                        y = enveloppe.getHeight();
                        break;
                    }

                    if(curseurBits<tailleLettre*8+tailleTotalReserver){
                        if(curseurBits>=tailleTotalReserver){
                            fichierCacherBits[curseurBits-tailleTotalReserver] = this.recupererBit(blue,0);
                        }
                        curseurBits++;
                    }else{
                        x = enveloppe.getWidth();
                        y = enveloppe.getHeight();
                        break;
                    }
                }
            }
            byte[] fichierCacher = intEnBytes(fichierCacherBits);
            if(lettreCompresser){
                fichierCacher = Compresser.decompression(fichierCacher);
            }

            if(!(mdp.equals(""))){
                fichierCacher = Cryptage.deChiffrement(fichierCacher, mdp.getBytes());
            }

            System.out.println("taille total : "+(tailleLettre+tailleTotalReserver/8));
            GestionFichier.fluxEnFichier(cheminLettre, fichierCacher);
        }catch(Exception e){
            System.out.println(e);
        }
    }


    public int[] bytesEnInt(byte[] octets){
        int aRetourner[] = new int[octets.length*8];
        int curseurInt = 0;
        for(int i = 0;i<octets.length;i++){
            for(int j=0;j<8;j++){
                aRetourner[curseurInt] = recupererBit(octets[i],j);
                curseurInt++;
            }
        }
        return aRetourner;
    }

    public byte[] intEnBytes(int[] intable){
        byte[] aRetourner=new byte[intable.length/8];
        int curseurInt = 0;

        byte tmp = 0;
        for(int curseurOctet = 0;curseurOctet<aRetourner.length;curseurOctet++){
            for(int curseurBits=0;curseurBits<8;curseurBits++){
                tmp = (byte) (tmp + (byte) (intable[curseurInt]*Math.pow(2,curseurBits)));
                curseurInt++;
            }
            aRetourner[curseurOctet] = tmp;
            tmp=0;
        }
        return aRetourner;
    }

    public void ecrireImage(File file, Bitmap img){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            img.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}