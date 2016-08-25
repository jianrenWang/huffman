// Vikram R. Sringari
// 3/10/2016
// CSE143
// TA: Chloe Lathe
// Assignment #8: HuffmanTree
// This Class constructs HuffmanTree objects, which is way to compress files
// for storage. This class constructs HuffmanTrees using a file of charaters
// and is able to print encoded file using the write method. This class
// is also capable of constructing an HuffmanTree from a previosly encoded 
// file, and then is able to decode that file with the decode method.
import java.util.*;
import java.io.*;
public class HuffmanTree{
   
   private HuffmanNode overallRoot;// over all root of the Huffman Tree
   
   //pre: Takes an array of count, which is the frequencies of each character
   //     and creates an initial HuffmanTree.
   //post: Uses the number occurances of each character from int[] count to 
   //      construct a HuffmanTree, these characters are from a file 
   //      (probably .txt) 
   public HuffmanTree(int[] count){
      PriorityQueue<HuffmanNode> nodes = new PriorityQueue<HuffmanNode>();
      for(int i = 0; i < count.length; i++){
         if(count[i] > 0){
            nodes.add(new HuffmanNode(count[i], i));
         }
      }
      HuffmanNode eof = new HuffmanNode(1, count.length); //pseudo-eof
      nodes.add(eof); 
      while(nodes.size() > 1){
         HuffmanNode leftNode = nodes.remove();
         HuffmanNode rightNode = nodes.remove();
         HuffmanNode tree = new HuffmanNode(leftNode.frequency + rightNode.frequency,
                                            -1, leftNode, rightNode);     
         nodes.add(tree);
      }
      overallRoot = nodes.remove();
   }
   
   //pre: This takes a printstream output to print out the HuffmanTree to
   //post: This uses the ouptut to print out the HuffmanTree. The HuffmanTree
   //      is printed out Standard format. This format is just binary and ascii
   //      int characters.
   public void write(PrintStream output){
      if(overallRoot != null){
         writeHelp(output, overallRoot, "");
      }
   }
   
   //pre: This takes in the ouput stream from write methode, a HuffmanNode root, and
   //     the a string which will produce binary. In the write method binary starts 
   //     as a blank string, but adds ones and or zeros based on the way writeHelp 
   //     recurses down the HuffmanTree to access get to he leafNode which represents
   //     the character. If the writeHelp recurses left to get to a character it adds 
   //     a zero, and it adds a one if it goes right.
   //post: This helper method prints the ascii value of the character in the HuffmanTree
   //      and prints the binary value of that charater derived from the path of recursion
   //      for that character. This ouptut (to a file) is reffered to as standard form.
   private void writeHelp(PrintStream output, HuffmanNode root, String binary){
      if(root.left == null && root.right == null){
         output.println(root.ascii);
         output.println(binary);
      }
      else{
         writeHelp(output, root.left, binary + "0");
         writeHelp(output, root.right, binary + "1");
      }
   }
   
   //pre: Takes in an encoded input (using scanner) and reproduces a HuffmanTree from
   //     from the encoded file
   //post: Uses the input (from an encoded file) to reproduces a HuffmanTree, the scanner 
   //      contains the tree strored in standard format. It uses this to reproduce the
   //      HuffmanTree.
   public HuffmanTree(Scanner input){
      while(input.hasNextLine()){
         int ascii = Integer.parseInt(input.nextLine());
         String binary = input.nextLine();
         overallRoot = recreate(overallRoot, binary, ascii);  
      }
   }
   
   //pre: This takes Huffman root, an integer ascii value and a binary value of that 
   //     character from the input in the constructor with parameter scanner input.
   //     It uses the root to create the HuffmanTree through by taking each ascii
   //     and binary value for each character and restoring them into tree
   //     This method uses recursion through the binary (using substring)
   //     to produce the HuffmanTree.
   //post: This returns a root that will equall to overallRoot of the HuffmanTree
   //      to produce the tree. It uses the ascii and binary values from the 
   //      encoded file to reproduce the exact form of the HuffmanTree. It
   //      will specifically create new leafNodes using the path produced
   //      by the order of the binary bits for that character.
   private HuffmanNode recreate(HuffmanNode root, String binary, int ascii){    
      if(root == null){
         root = new HuffmanNode(0, -1);
      }
      if(binary.length() == 0){
         return new HuffmanNode(0, ascii);
      }else if(binary.charAt(0) == '1'){
         root.right = recreate(root.right, binary.substring(1), ascii);
      }else{ // == '0'
         root.left = recreate(root.left, binary.substring(1), ascii);
      }
      return root;
   }
   
   //pre: This takes an input of bits from the file, takes output printstream, and an eof
   //     It uses these parameters by taking the input of file (BitInputStream and
   //     outputs the characters in appropriote form. The method ends printing to another 
   //     file, when it encounters eof value (end of file).
   //post: This takes a bitinputstream from an previously encoded file and prints out 
   //      the exact output of the corresponding characters into printstream to a file,
   //      basically producing the characters in the form demonstrated before the file 
   //      was encoded. This printing stops when end of file value is encountered.
   public void decode(BitInputStream input, PrintStream output, int eof){
      HuffmanNode root = overallRoot;
      while(root.ascii != eof){
         if(root.right == null && root.left == null){
            output.write(root.ascii);
            root = overallRoot;
         }else if(input.readBit() == 1){
            root = root.right;
         }else{ // == 0
            root = root.left;
         }   
      } 
   }
}