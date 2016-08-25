# huffman
HuffmanTree (Implementation-Side)
This Class constructs HuffmanTree objects, which is way to compress files
for storage. This class constructs HuffmanTrees using a file of charaters
and is able to print encoded file using the write method. This class
is also capable of constructing an HuffmanTree from a previosly encoded 
file, and then is able to decode that file with the decode method.

HuffmanNode (Implementation-Side)
This Class is the HuffmanNode class which has the feilds of
frequency, ascii, left and right as well as a constructor that only
includes frequency and and ascii, and a constructor that includes
the values of left and right that define it in their parameters

Encode (Client-Side)
Encode prompts the user for an input file name, a code file name and the
name to use for the binary (encoded) output file.  It assumes that MakeCode
has been run to generate a Huffman tree appropriate for encoding the input
file.

Decode (Client-Side)
Decode prompts the user for the name of a binary (encoded) input file and a
code file name and produces as output a text file containing the original
file.  Assumes that the binary input file was creaed by Encode.

