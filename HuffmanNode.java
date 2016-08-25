// Vikram R. Sringari
// 3/10/2016
// CSE143
// TA: Chloe Lathe
// Assignment #8: HuffmanTree
// This Class is the HuffmanNode class which has the feilds of
// frequency, ascii, left and right as well as a constructor that only
// includes frequency and and ascii, and a constructor that includes
// the values of left and right that define it in their parameters

public class HuffmanNode implements Comparable<HuffmanNode>{

   public int frequency;// frequency of character
   
   public int ascii;// integer ascii (character) value
   
   public HuffmanNode left; //links to the left path from the previous HuffmanNode
   
   public HuffmanNode right;//links to the right path from the previous HuffmanNode
    
   //pre: Takes frequency and ascii value of a character and uses it contruct HuffmanNode
   //post: Constructs the HuffmanNode;
   public HuffmanNode(int frequency, int ascii){
      this(frequency, ascii, null, null);
   }
   
   
   //pre: Takes the left and the right HuffmanNodes as well as the frequency and 
   //     ascii value of a character to construct the HuffmanNode;
   //post: Constructs the HuffmanNode; 
   public HuffmanNode(int frequency, int ascii, HuffmanNode left, HuffmanNode right){
      this.frequency = frequency;
      this.ascii = ascii;
      this.left = left;
      this.right = right;
   }
   
   //pre: Takes another Huffman Node to compare huffman nodes 
   //post: returns positive value for this HuffmanNode being greater
   //      negative value for other HuffmanNode bieng greater,
   //      and zero if they are the same.
   public int compareTo(HuffmanNode other){
      return this.frequency - other.frequency;// defined by frequencey.
   } 
}