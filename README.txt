=========================================DESApplication Info==============================================

DESApplication is a java application that implements the Data Encryption Standard (DES) algorithm.
The mode of operations that I used for the application was Electronic Codebook Model(ECB). The ECB is
implemented in the class with the same name. The keys for encoding and decrypting are in the KeyGenerator
class. BlockDecoder is the class to decode a block string while BlockEncoder is to encode a block string.
I got the DES tables and functions from https://en.wikipedia.org/wiki/DES_supplementary_material.

For Encryption the input can be a english string or a hexadecimal string with the following format 
#hexadecimal string (#0123adcf). The application will display the hexadecimal blocks. Finally it will 
display the ciphertext.

For Decryption the input must be a hexadecimal string like "0123adcf". The application will display the
hexadecimal blocks. Finally it will display the plaintext.

The secret key must be a hexadecimal string like "0123adcf".

The application can be stopped or run multiple times.

For DES test vectors I used the 7.86 Example in page 256 of the book and the examples in the website 
http://page.math.tu-berlin.de/~kant/teaching/hess/krypto-ws2006/des.htm.

Javadoc is located in \dist\javadoc folder. Open the dist\javadoc\index.html to go to the principal page of 
the java documentation.

=================================DESApplication Execution Windows===========================================
1.Unzip File.
2.Go to the \dist folder.
3.Click on the DESApplicationBat.bat file.
4.Follow the instructions on the screen.

============================DESApplication Execution Osx, Unix, Linux========================================
1.Unzip File.
2.Open command line.
3.Enter the following line: java -jar pathToApplication/DESApplication.jar.
4.Follow the instructions on the command line.

======================================DESApplication Java IDE================================================
1.Unzip File.
2.Import project.
3.Build project.
4.Run DESApplication file.
5.Follow the instrutions on the console.

=================================DESApplication Output Example==============================================
Main menu.
 1---To Encrypt Message
 2---To Decrypt Message.
1
Please enter message to encrypt.If message is in hex, please add # to the begining.
[input]
Please enter secret key. It must be a hexadecimal string.
[input]
Plaintext in hexadecimal blocks: [blocks]
The ciphertext is: [ciphertext]
C---To continue. S---To stop.
C
Main menu.
 1---To Encrypt Message
 2---To Decrypt Message.
2
Please enter message to decrypt. Message must be a hexadecimal string.
[input]
Please enter secret key. It must be a hexadecimal string.
[input]
Hexadecimal blocks: [blocks]
This is the plaintext: [plaintext]
C---To continue. S---To stop.
S
