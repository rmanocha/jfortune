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
 
package org.jstrfile;

import java.io.*;

/**
 * This class creates the .num files which are later read by FileRead to give
 * a random quote no.
 * @author Rishabh Manocha
 * @version 0.1
 */
public class jstrfile {
	
	/**
	 *Main method. This takes in the list of files as arguments and 
	 * calls makeIndex to make the .num files.
	 * @param args An array of strings representing the various files.
	 */
	public static void main(String[] args) {
		try{
			jstrfile strfile = new jstrfile();
			for(int i = 0; i < args.length; i++) {
				File file = new File(args[i] + ".num");
				if(!file.exists()) {
					if(strfile.makeIndex(args[i]))
		            System.out.println(args[i] + ": This is the first time jfortune is being run on this file.A file named " + args[i] + ".num has been created in the source direcrory of the input file."+"\n"+"If you ever change the source file please do remove the .num file created and run jfortune on it again so that jfortune does not have a wrong belief to the number of quotes in the source file.");
				}
				else
					System.out.println("A file called " + args[i] + ".num already exists. If you have made changes to the source fortune cookies file, then please remove the corresponding .num file and then run jstrfile on it again.");
			}
		}catch (IOException e) {
			System.err.println("You do not have write permissions to this directory");
			e.printStackTrace();
		}
	}

	/**
	 * This method makes counts the no. of quotes in the fortune cookie file
	 * and creates a .num file with that no.
	 * @param infile path/name of the fortune cookie file.
	 * @return boolean to tell if the file was a fortune cookie file or not.
	 * @throws  IOException for any I/O exceptions.
	 */
	public boolean makeIndex(String infile) throws IOException {
	      BufferedReader myNumReader = new BufferedReader(new FileReader(infile));
		File file = new File(infile);
	      String input="";
	      int index = 0;
	      while((input = myNumReader.readLine()) != null)
	         if(input.equals("%"))
            	index++;
		if(index == 0) {
		      System.out.println("To the best of my knowledge,the file you have provided (" + infile + ") is not a fortune cookie file.Please use your googling skills to find one and then come back and run this program.");
		      return false;
		}
		BufferedWriter myOut = new BufferedWriter(new FileWriter(infile + ".num"));
		myOut.write(Integer.toString(index) + "\n");
		myOut.write(Long.toString(file.length()));
		myOut.close();
		return true;
	}
}
