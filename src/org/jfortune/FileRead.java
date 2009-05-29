/*
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Library General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
 
package org.jfortune;


import java.io.*;
import java.util.Random;

/**
 * This class is used to read information from the .num files.
 * @author Rishabh Manocha
 * @version 0.6
 */
public class FileRead
{
	private BufferedReader myNumReader;
	private boolean flag = false;
	
	/**
	 * Constructor for FileRead.Here i check to see if a a .num file of the parsed file exists or not.If not...i then create it...print out
	 * that this is the first time the program was run on this file and exit.A cookie is not actually printed out the first time jfortune will be
	 * run on a given file
	 * @param input A String representing the filename whose .num file needs to be read.
	 */
	public FileRead(String input) throws IOException{
		try {
			this.myNumReader = new BufferedReader(new FileReader(input + ".num"));
		}
		catch (IOException e) {
			System.err.println("Please run jstrfile on " + input + " before running jfortune on it.");
			System.exit(1);
		}
	}

	
	/**
	 * This is the method which reads in the no. in the .num of the input file and returns a random integer.The random int is chosen from between
	 * 0 and the number of cookies in the file.If the file contains null...i then scold the user saying that he/she was told that the file he/she
	 * run jfortune on was not a fortune cookie file and that they should learn to interpret error messages and use google.
	 * @return int An integer containing the no. of the quote we need to display from this file.
	 */
	public int getNum() {
		Random temp = new Random();
		try{
		return temp.nextInt(Integer.parseInt(this.myNumReader.readLine()));}
		catch (IOException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			System.err.println("The File you have parsed me is not a fortune cookie file.Dont you get error messages.You were told that this file is not a fortune cookie file and yet you come and rerun this program on that file.You anger me.Please use google to search for a fortune cookie file or make the file you want this program to run on a fortune cookie file and rerun this program.");
		}
		return -1;
	}
}
