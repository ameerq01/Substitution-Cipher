//importing Java.util to use Scanner
import java.util.*;
public class SubstitutionCipher {
 	     
	    //Name : Ameer Qamar 
    //Course : ICS3U1-01
//Date : November 17th, 2017

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Declaring and initializing Scanner Class
		Scanner input = new Scanner(System.in);

		//Declaring and initializing String variables
		String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//My variable called "key" is what you call modified alphabet//
		String key= "";
		String password ="";
		String toEncrypt, encryptedWord = "", toDecrypt, decryptedWord = "";
		//Declaring and initializing integer variables
		int shiftKey=400, choice =0 ;
		//Declaring and initializing boolean variable
		boolean valid= false;
		//Creating character object to use later
		Character letter ;

		//Outputting the user's choices
		System.out.println("SUBSTITUTION CIPHER");
		System.out.println("Please select one of the following options:\n");
		System.out.println("1-Encrypt a message");
		System.out.println("2-Decrypt a message");
		System.out.println("3-Brute force\n");
		//Repeatedly prompting the user to chose what they would like to do until they enter a valid value  and then storing their response
		do {
			System.out.printf("%-40s","Please enter your choice:");
			try {
				choice = input.nextInt();
				valid=true;
				if (choice <1 || choice > 3)
				{
					System.out.println("Please enter a valid choice (1 or 2 or 3) !");
					valid = false;
				}
				else
				{
					input.nextLine();
				}
			}
			catch(Exception ex)
			{
				System.out.println("Please enter a valid choice (1 or 2 or 3) !");
				input.nextLine();
			}

		}
		while(valid==false);
		//Reinitializing boolean variable
		valid = false;

		//If the user chooses option 1 (Encryption)
		if (choice ==1)
		{
			//Prompting the user for their password then storing it
			System.out.printf("%-40s","Please enter your password:");
			password = input.nextLine();
			//checking each character in the password
			for(int i = 0; i <password.length();i++)
			{
				letter = password.charAt(i);
				//if it is a letter
				if (Character.isLetter(letter))
				{
					//if it is the letter at index 0; add it regardless
					if( i == 0)
					{
						key+= password.charAt(i);
					}
					//if it is not the first letter and is not already in the modified alphabet; add it to the modified alphabet
					else if ((key.contains(letter.toString()) == false))
					{
						key += password.charAt(i);
					}
				}
				//if it is not a letter; just don't add a new character to the new alphabet
				else
				{
					key+="";
				}
			}
			//reinitializing boolean value
			valid = false;
			//Repeatedly prompting the user for a key number until they enter a valid number between 0 and 25 and storing it
			do
			{
				System.out.printf("%-40s","Enter the key number (0-25):");
				try
				{
					shiftKey = input.nextInt();
					valid = true;
					if(shiftKey<0 || shiftKey >25)
					{
						valid = false;
						System.out.println("Please enter a number within the range (0-25)!");
					}
					else
					{
						input.nextLine();
					}
				}
				catch(Exception ex)
				{
					System.out.println("Please enter a valid number!");
					input.nextLine();
				}
			}
			while(valid == false);

			//Prompting the user for the message they want to encrypt and storing it
			System.out.printf("%-40s","Enter the message you want encrypted:");
			toEncrypt = input.nextLine();

			//checking if the modified alphabet does not already contain that letter from the alphabet; add it to the modified alphabet
			for (int i =0; i <26; i ++)
			{			
				letter = alphabet.charAt(i);
				if ((password.toUpperCase().contains(letter.toString())) == false)
				{
					key +=letter;
				}
			}	
			//Encrypting the word they gave us. (Checking each letter of the word with each letter of the alphabet and converting it) 
			for(int i = 0; i < toEncrypt.length(); i ++)
			{
				//if the character at this index is a space; add a space to our encrypted word
				if(Character.isWhitespace(toEncrypt.charAt(i) )) 
				{
					encryptedWord+=" ";
				}
				// otherwise, check if the character at index i of the word to encrypt is equal to the letter of  the alphabet at that counter; if it is then add the letter at the index (counter+ shift key) from the modified alphabet 
				else
				{
					for(int counter = 0; counter < 26; counter ++)
						//checking if the characters are equal
						if(toEncrypt.toLowerCase().charAt(i) == alphabet.toLowerCase().charAt(counter))
						{
							//if it is a capital letter
							if(Character.isUpperCase(toEncrypt.charAt(i) ))
							{
								{
									//if the sum of counter and shift key is more than 25
									if ((counter+ shiftKey) > 25)
									{
										//subtract 26 from their sum and add the character at that index of the modified alphabet
										encryptedWord += key.toUpperCase().charAt((counter+shiftKey)-26);
									}
									//if their sum is less than 25 just add the character at the index (counter + shift key) of the modified alphabet
									else 
									{
										encryptedWord += key.toUpperCase().charAt(counter+shiftKey);
									}
								}
							}
							//same as before but this time, it is for a lower case letter
							else
							{
								if ((counter+ shiftKey) > 25)
								{
									encryptedWord += key.toLowerCase().charAt((counter+shiftKey)-26);
								}
								else
								{
									encryptedWord +=key.toLowerCase().charAt(counter + shiftKey);
								}
							}
						}
				}
			}
			//Outputting the encrypted message
			System.out.printf("%-40s%-40s","Your encrypted message is:",encryptedWord);
		}
		//If they chose to decrpyt
		else if( choice == 2)
		{
			//prompting user for password and storing it
			System.out.printf("%-40s","Please enter your password:");
			password = input.nextLine();
			//checking each letter of our password to see whether or not to store it in  modified alphabet
			for(int i = 0; i <password.length();i++)
			{
				letter = password.charAt(i);
				//if it is a letter
				if(Character.isLetter(letter))
				{
					//if it is the first letter in the password, add it to modified alphabet regardless
					if( i == 0)
					{
						key+= password.charAt(i);
					}
					// if it is not the first letter but it is not already in the modified alphabet; add it to our modified alphabet
					else if ((key.contains(letter.toString()) == false))
					{
						key += password.charAt(i);
					}
				}
				//if it is not a letter just do not add anything to our alphabet
				else
				{
					key+="";
				}
			}

			//reinitializing boolean value
			valid = false;

			//Repeatedly prompting the user for a key number until they enter a valid number between 0 and 25 and storing it
			do
			{
				System.out.printf("%-40s","Enter the key number (0-25):");
				try
				{
					shiftKey = input.nextInt();
					valid = true;
					if(shiftKey<0 || shiftKey >25)
					{
						valid = false;
						System.out.println("Please enter a number within the range (0-25)!");
						input.nextLine();
					}
					else
					{
						input.nextLine();
					}

				}
				catch(Exception ex)
				{
					System.out.println("Please enter a valid number!");
					input.nextLine();
				}
			}	while(valid == false);

			//Prompting the user for the message they want to decrypt
			System.out.printf("%-40s","Enter the message you want decrypted:");
			toDecrypt = input.nextLine();

			//checking if the modified alphabet does not already contain that letter from the alphabet; add it to the modified alphabet
			for (int i =0; i <26; i ++)
			{			
				letter = alphabet.charAt(i);
				if ((password.toUpperCase().contains(letter.toString())) == false)
				{
					key +=letter;
				}
			}	 
			//decrypting the word. (Checking each letter of the word with each letter of the modified  alphabet and converting it) 
			for(int i = 0; i < toDecrypt.length(); i ++)
			{
				//if it is a space; add a space to the decrypted word 
				if(Character.isWhitespace(toDecrypt.charAt(i) )) 
				{
					decryptedWord+=" ";
				}
				//if it is not a space
				else
				{
					for(int counter = 0; counter < 26; counter ++)
						//checking if the letters are equal
						if(toDecrypt.toLowerCase().charAt(i) == key.toLowerCase().charAt(counter))
						{
							//if they were equal and it is an uppercase letter
							if(Character.isUpperCase(toDecrypt.charAt(i) ))
							{
								{
									// if the difference of the counter and shiftkey is less than 0; we need to go to the last index again so we add 26  and then add the letter of the alphabet at that index
									if ((counter - shiftKey) < 0)
									{
										decryptedWord += alphabet.toUpperCase().charAt((counter-shiftKey)+26);
									}
									//if the difference is >0 just add the letter in the alphabet at the index of the difference
									else 
									{
										decryptedWord += alphabet.toUpperCase().charAt(counter-shiftKey);
									}
								}
							}

							else
								//if they were equal and it is a lower case letter
							{
								//f the difference of the counter and shiftkey is less than 0; we need to go to the last index again so we add 26  and then add the letter of the alphabet at that index
								if ((counter - shiftKey) < 0)
								{
									decryptedWord += alphabet.toLowerCase().charAt((counter-shiftKey)+26);
								}
								//if the difference is >0 just add the letter in the alphabet at the index of the difference
								else
								{
									decryptedWord +=alphabet.toLowerCase().charAt(counter - shiftKey);
								}
							}
						}
				}
			}
			//outputting the decrypted message
			System.out.printf("%-40s%-40s\n","Your message is:" ,decryptedWord);
		}

		//option 3, brute force
		else if (choice ==3)
		{
			//prompting user for password
			System.out.printf("%-40s","Please enter your password:");
			password = input.nextLine();
			//adding letters of password to modified alphabet
			for(int i = 0; i <password.length();i++)
			{
				letter = password.charAt(i);
				//if the character at index i of password is a letter
				if (Character.isLetter(letter))
				{
					//if it is a letter at index 0; add it regardless
					if( i == 0)
					{
						key+= password.charAt(i);
					}
					//otherwise; check if the modified alphabet already contains it; if it does not, add it to the modified alphabet 
					else if ((key.contains(letter.toString()) == false))
					{
						key += password.charAt(i);
					}
				}
				// if it is not a letter; do not add it to the modified alphabet
				else
				{
					key+="";
				}
			}
			//prompting the user for the message they want to decrypt and storing it
			System.out.printf("%-40s","Enter the message you want decrypted:");
			toDecrypt = input.nextLine();

			//checking if the letters in the alphabet are not already in the modified alphabet. If they are not; we add them to our modified alphabet
			for (int i =0; i <26; i ++)
			{			
				letter = alphabet.charAt(i);
				if ((password.toUpperCase().contains(letter.toString())) == false)
				{
					key +=letter;
				}
			}	

			// making a loop to for all shift keys 
			for (int index = 0; index <26; index ++)
			{
				decryptedWord = "";
				//shift key is equal to the index
				shiftKey = index;

				//checking each character of the word to decrypt
				for(int i = 0; i < toDecrypt.length(); i ++)
				{	
					//if it is a space, add a space to decrypted word variable
					if(Character.isWhitespace(toDecrypt.charAt(i) )) 
					{
						decryptedWord+=" ";
					}
					else
						//if it is a letter
					{
						//compare it to each letter of modified alphabet
						for(int counter = 0; counter < 26; counter ++)
							//if they are equal
							if(toDecrypt.toLowerCase().charAt(i) == key.toLowerCase().charAt(counter))
							{
								//if the character is upper case
								if(Character.isUpperCase(toDecrypt.charAt(i) ))
								{
									//if the difference of the shift key and counter is less than 0, we have to add 26 and add the letter of the alphabet at index ((counter - shift key) +26)	
										if ((counter - shiftKey) < 0)
										{
											decryptedWord += alphabet.toUpperCase().charAt((counter-shiftKey)+26);
										}
										//if their difference is greater than 0 just add the letter of the alphabet at the index (counter - shiftkey)
										else 
										{
											decryptedWord += alphabet.toUpperCase().charAt(counter-shiftKey);
										}
									
								}

								else
									//if it is a lower case letter
								{
									//if the difference of the shift key and counter is less than 0, we have to add 26 and add the letter of the alphabet at index ((counter - shift key) +26)	
									if ((counter - shiftKey) < 0)
									{
										decryptedWord += alphabet.toLowerCase().charAt((counter-shiftKey)+26);
									}
									//if their difference is greater than 0 just add the letter of the alphabet at the index (counter - shiftkey)
									else
									{
										decryptedWord +=alphabet.toLowerCase().charAt(counter - shiftKey);
									}
								}
							}
					}
				}
				//Outputting the decrypted word at that (Shift)Key (index of the loop)
				System.out.printf("%-40s%-40s\n","Key Number "+ shiftKey+":", decryptedWord);
			}		
		}
		//Closing the input
		input.close();
	} 
}